package endpoints;

import entities.BreweryEntity;
import services.BreweryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("brewery")
public class BreweryEndpoint {

    @Inject
    private BreweryService breweryService;

    @PUT
    @Path("{breweryUntappdId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BreweryEntity editBrewery(BreweryEntity brewery) {
        return breweryService.createOrUpdate(brewery);
    }

    @POST
    @Path("createOrUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BreweryEntity addBrewery(BreweryEntity brewery) throws IOException {
        return breweryService.createOrUpdate(brewery);
    }

    @DELETE
    @Path("{untappdBreweryId}")
    public void removeBrewery(@PathParam("untappdBreweryId") Integer untappdBreweryId) {
        breweryService.removeBrewery(untappdBreweryId);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BreweryEntity> getBeersBySession() {
        return breweryService.getBreweries();
    }
}
