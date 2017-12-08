package endpoints;

import entities.BeerEntity;
import services.BeerService;
import services.BreweryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("beer")
public class BeerEndpoint {

    @Inject
    private BeerService beerService;
    @Inject
    private BreweryService breweryService;

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BeerEntity addBeer(BeerEntity beer) throws IOException {
        breweryService.createOrUpdate(beer.getBrewery());
        return beerService.createOrUpdate(beer);
    }

    @PUT
    @Path("{untappdId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BeerEntity editBeer(BeerEntity beer) throws IOException {
        breweryService.createOrUpdate(beer.getBrewery());
        return beerService.createOrUpdate(beer);
    }

    @DELETE
    @Path("{untappdBeerId}")
    public void removeBeer(@PathParam("untappdBeerId") Integer untappdBeerId) {
        beerService.removeBeer(untappdBeerId);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerEntity> getBeers() {
        return beerService.getBeers();
    }

    @GET
    @Path("list/{sessionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerEntity> getBeersBySession(@PathParam("sessionId") Integer sessionId) {
        return beerService.getBeersBySession(sessionId);
    }
}
