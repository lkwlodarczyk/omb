package util.connection.connection;

import javax.inject.Singleton;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Singleton
public class MessageSender {

    private final String UNTAPPD_ADDRESS = "https://api.untappd.com/v4/";
    private final String ACCESS_TOKEN = "?access_token=F88C71C7C3C6E9A9F2A032AEE048C138AB0FE6BE";

    public SenderResponse send(String untappdMethod, String httpMethod) throws IOException {
        URL url = new URL(UNTAPPD_ADDRESS + untappdMethod + ACCESS_TOKEN);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(httpMethod);

        return createResponse(connection);
    }

    private SenderResponse createResponse(HttpsURLConnection connection) throws IOException {

        SenderResponse response = new SenderResponse(connection.getResponseCode(), getResponseBody(connection));
        return response;
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

    public static class SenderResponse {

        private final Integer code;

        private final String body;

        public SenderResponse(Integer code, String body) {
            this.code = code;
            this.body = body;
        }


        public Integer getCode() {
            return code;
        }

        public String getBody() {
            return body;
        }
    }
}
