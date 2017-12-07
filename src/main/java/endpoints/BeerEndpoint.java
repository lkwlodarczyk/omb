package endpoints;

import entities.BeerEntity;
import entities.BreweryEntity;
import services.BeerService;
import services.BreweryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

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
        return beerService.add(beer);
    }

    @DELETE
    @Path("{untappdBeerId}")
    public void removeBeer(@PathParam("untappdBeerId") Integer untappdBeerId) {
        beerService.removeBeer(untappdBeerId);
    }
}
