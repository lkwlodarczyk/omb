package utils.connection;

import models.SenderResponse;
import utils.json.JsonUtils;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageSender {

    private final String UNTAPPD_ADDRESS = "https://api.untappd.com/v4/";
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


    public void post() throws IOException {
        URL url = new URL(UNTAPPD_ADDRESS + "checkin/add");

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("access_token", "F88C71C7C3C6E9A9F2A032AEE048C138AB0FE6BE");
        params.put("gmt_offset", "-2");
        params.put("timezone", "EST");
        params.put("bid", "1721679");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        SenderResponse deserialize = jsonUtils.deserialize(getResponseBody(conn), SenderResponse.class);
        String a = "";
    }
}
