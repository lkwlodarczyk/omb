package utils.connection;

import models.SenderResponse;
import utils.json.JsonUtils;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MessageSender {

    private JsonUtils jsonUtils;

    @Inject
    MessageSender(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public SenderResponse send(URL url, String httpMethod) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(httpMethod);

        return jsonUtils.deserialize(getResponseBody(connection), SenderResponse.class);
    }

    public SenderResponse send(URL url, String httpMethod, Map<String, Object> params) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(getBytesFromParams(params));

        return jsonUtils.deserialize(getResponseBody(conn), SenderResponse.class);
    }

    private String getResponseBody(HttpURLConnection connection) throws IOException {
        if (connection.getResponseCode() < 300) {
            return parseResponse(connection.getInputStream());
        }

        return parseResponse(connection.getErrorStream());
    }

    private String parseResponse(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }

        return result.toString();
    }

    private byte[] getBytesFromParams(Map<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return postData.toString().getBytes("UTF-8");
    }
}
