package untappd;

import models.SenderResponse;
import models.Untappd.UntappdBeer;
import utils.connection.MessageSender;
import utils.connection.UrlBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class UntappdService {

    private MessageSender messageSender;
    private UrlBuilder urlBuilder;

    @Inject
    public UntappdService(MessageSender messageSender, UrlBuilder urlBuilder) {
        this.messageSender = messageSender;
        this.urlBuilder = urlBuilder;
    }

    public UntappdBeer getBeerByUntappdId(Integer untappdBeerId) throws IOException {
        SenderResponse response = messageSender.send(urlBuilder.buildGetBeerUrl(untappdBeerId), "GET");
        return response.getResponse().getBeer();
    }

    public List<UntappdBeer> getBeersByName(String name) throws IOException {
        SenderResponse response = messageSender.send(urlBuilder.buildSearchBeers(name), "GET");
        return response.getResponse().getAllBeers();
    }

    public Boolean checkin() throws IOException {
        SenderResponse response = messageSender.send(urlBuilder.buildCheckin(1721679), "POST");
        return true;
    }


}
