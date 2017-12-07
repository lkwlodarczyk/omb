package utils.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UrlBuilder {

    private final String UNTAPPD_ADDRESS = "https://api.untappd.com/v4/";

    public URL buildGetBeerUrl(Integer beerId) throws MalformedURLException {
        String methodName = "beer/info/" + beerId;
        return new URL(getBasicUrl(methodName));
    }

    public URL buildSearchBeersUrl(String name) throws MalformedURLException {
        String methodName = "search/beer";
        Map<String, Object> params = new HashMap<>();
        params.put("q", name);

        return build(methodName, params);
    }

    public URL buildCheckinUrl() throws MalformedURLException {
        String methodName = "checkin/add";

        return new URL(getBasicUrl(methodName));
    }

    private URL build(String methodName, Map<String, Object> params) throws MalformedURLException {
        String url = getBasicUrl(methodName);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            url += "&" + entry.getKey() + "=" + entry.getValue();
        }
        return new URL(url);
    }

    private String getBasicUrl(String methodName) {
        return UNTAPPD_ADDRESS + methodName + "?" + getAccessToken();
    }

    private String getAccessToken() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("AccessToken");
        String token = new BufferedReader(new InputStreamReader(resourceAsStream))
                .lines().collect(Collectors.joining("\n"));
        return "access_token=" + token + "&compact=true";
    }
}
