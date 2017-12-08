package endpoints;

import entities.CheckinEntity;
import services.CheckinService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("checkin")
public class CheckinEndpoint {

    @Inject
    private CheckinService checkinService;

    @POST
    @Path("createOrUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean addCheckin(CheckinEntity checkinEntity) throws IOException {
        return checkinService.add(checkinEntity);
    }
}
