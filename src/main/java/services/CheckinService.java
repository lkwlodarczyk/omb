package services;

import entities.CheckinEntity;
import models.untappd.UntappdResponse;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import java.io.IOException;

@Stateless
public class CheckinService {

    @PersistenceContext(unitName = "omb")
    Session session;

    @Inject
    private UntappdService untappdService;

    public Boolean add(CheckinEntity checkinEntity) throws IOException {
        checkinEntity.setUsername("user");
        UntappdResponse response = untappdService.checkin(checkinEntity.getCheckinRequest());
        checkinEntity.setStoredInUntappd(response.getStatus());
        checkinEntity.setUntappdId(response.getCheckinId());
        session.save(checkinEntity);
        return response.getStatus();
    }
}
