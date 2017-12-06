package endpoints;

import models.Untappd.UntappdBeer;
import untappd.UntappdService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("beer")
public class Endpoint {

    @Inject
    private UntappdService untappdService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UntappdBeer getBeerById(@PathParam("id") Integer id) throws IOException {
        return untappdService.getBeerByUntappdId(id);
    }

    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UntappdBeer> getBeersByName(@PathParam("name") String name) throws IOException {
        return untappdService.getBeersByName(name);
    }

    @GET
    @Path("checkin")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean checkin() throws IOException {
        return untappdService.checkin();
    }

}
