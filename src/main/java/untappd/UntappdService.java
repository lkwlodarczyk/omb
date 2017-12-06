package untappd;

import models.SenderResponse;
import models.Untappd.UntappdBeer;
import utils.connection.AccessToken;
import utils.connection.MessageSender;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UntappdService {

    private MessageSender messageSender;

    @Inject
    public UntappdService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public UntappdBeer getBeerByUntappdId(Integer untappdBeerId) throws IOException {
        //https://api.untappd.com/v4/beer/info/1326951?access_token=F88C71C7C3C6E9A9F2A032AEE048C138AB0FE6BE&compact=true
        String untappdMethod = "beer/info/" + untappdBeerId + "?" + AccessToken.getAccessToken();
        String httpMethod = "GET";
        SenderResponse response = messageSender.send(untappdMethod, httpMethod);

        return response.getResponse().getBeer();
    }

    public List<UntappdBeer> getBeersByName(String name) throws IOException {
        //https://api.untappd.com/v4/search/beer?q=modern&access_token=F88C71C7C3C6E9A9F2A032AEE048C138AB0FE6BE&compact=true
        String untappdMethod = "search/beer?q=" + name + "&" + AccessToken.getAccessToken();
        String httpMethod = "GET";
        SenderResponse response = messageSender.send(untappdMethod, httpMethod);

        return response.getResponse().getAllBeers();
    }

    public Boolean checkin() throws IOException {
        String untappdMethod = "checkin/add?" + AccessToken.getAccessToken() + "&gmt_offset=-1&timezone=EST&bid=1721679";
        String httpMethod = "POST";
       // SenderResponse response = messageSender.send(untappdMethod, httpMethod);
        messageSender.post();


        return true;
    }



}
