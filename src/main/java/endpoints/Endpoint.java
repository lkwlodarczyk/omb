package endpoints;

import models.UntappdBeer;
import untappd.UntappdService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("beer")
public class Endpoint {

    @Inject
    private UntappdService untappdService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UntappdBeer getBeer() throws IOException {
        Integer id = 1326951;
        UntappdBeer beer = untappdService.findBeer(id);
        return beer;
    }

}
