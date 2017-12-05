package untappd;

import models.UntappdBeer;
import util.connection.connection.MessageSender;
import util.connection.json.JsonUtils;

import javax.inject.Inject;
import java.io.IOException;

public class UntappdService {

    private MessageSender messageSender;
    private JsonUtils jsonUtils;

    @Inject
    public UntappdService(MessageSender messageSender, JsonUtils jsonUtils) {
        this.messageSender = messageSender;
        this.jsonUtils = jsonUtils;
    }

    public UntappdBeer findBeer(Integer untappdBeerId) throws IOException {
        String untappdMethod = "beer/info/BID";
        String httpMethod = "GET";
        MessageSender.SenderResponse response = messageSender.send(untappdMethod, httpMethod);
        return jsonUtils.deserialize(response.getBody(), UntappdBeer.class);
    }
}
