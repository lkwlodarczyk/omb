package untappd;

import models.SenderResponse;
import models.Untappd.UntappdBeer;
import utils.connection.MessageSender;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class UntappdService {

    private MessageSender messageSender;

    @Inject
    public UntappdService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public UntappdBeer getBeerByUntappdId(Integer untappdBeerId) throws IOException {
        String untappdMethod = "beer/info/" + untappdBeerId + "?" + getAccessToken();
        String httpMethod = "GET";
        SenderResponse response = messageSender.send(untappdMethod, httpMethod);

        return response.getResponse().getBeer();
    }

    public List<UntappdBeer> getBeersByName(String name) throws IOException {
        String untappdMethod = "search/beer?q=" + name + "&" + getAccessToken();
        String httpMethod = "GET";
        SenderResponse response = messageSender.send(untappdMethod, httpMethod);

        return response.getResponse().getAllBeers();
    }

    public Boolean checkin() throws IOException {
        String untappdMethod = "checkin/add?" + getAccessToken() + "&gmt_offset=-1&timezone=EST&bid=1721679";
        String httpMethod = "POST";
        // SenderResponse response = messageSender.send(untappdMethod, httpMethod);
        messageSender.post();


        return true;
    }

    private String getAccessToken() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("AccessToken");
        String token = new BufferedReader(new InputStreamReader(resourceAsStream))
                .lines().collect(Collectors.joining("\n"));
        return "access_token=" + token + "&compact=true";
    }


}
