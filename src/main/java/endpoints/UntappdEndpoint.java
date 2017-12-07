package endpoints;

import models.requests.CheckinRequest;
import models.untappd.UntappdBeer;
import services.UntappdService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("untappd")
public class UntappdEndpoint {

    @Inject
    private UntappdService untappdService;

    @GET
    @Path("{untappdId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UntappdBeer getBeerById(@PathParam("untappdId") Integer untappdId) throws IOException {
        return untappdService.getBeerByUntappdId(untappdId);
    }

    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UntappdBeer> getBeersByName(@PathParam("name") String name) throws IOException {
        return untappdService.getBeersByName(name);
    }

    @POST
    @Path("checkin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean checkin(CheckinRequest checkinRequest) throws IOException {
        return untappdService.checkin(checkinRequest);
    }

}
