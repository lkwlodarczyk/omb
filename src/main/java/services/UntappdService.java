package services;

import models.SenderResponse;
import models.requests.CheckinRequest;
import models.untappd.UntappdBeer;
import models.untappd.UntappdResponse;
import utils.connection.MessageSender;
import utils.connection.ParamsBuilder;
import utils.connection.UrlBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class UntappdService {

    private MessageSender messageSender;
    private UrlBuilder urlBuilder;
    private ParamsBuilder paramsBuilder;

    @Inject
    public UntappdService(MessageSender messageSender, UrlBuilder urlBuilder, ParamsBuilder paramsBuilder) {
        this.messageSender = messageSender;
        this.urlBuilder = urlBuilder;
        this.paramsBuilder = paramsBuilder;
    }

    public UntappdBeer getBeerByUntappdId(Integer untappdBeerId) throws IOException {
        SenderResponse response = messageSender.send(urlBuilder.buildGetBeerUrl(untappdBeerId), "GET");
        return response.getResponse().getBeer();
    }

    public List<UntappdBeer> getBeersByName(String name) throws IOException {
        SenderResponse response = messageSender.send(urlBuilder.buildSearchBeersUrl(name), "GET");
        return response.getResponse().getAllBeers();
    }

    public UntappdResponse checkin(CheckinRequest checkinRequest) throws IOException {
        SenderResponse post = messageSender.send(urlBuilder.buildCheckinUrl(), "POST", paramsBuilder.buildCheckinParams(checkinRequest));
        return post.getResponse();
    }
}