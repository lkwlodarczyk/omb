package services;

import entities.BeerEntity;
import entities.BreweryEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Stateless
public class BreweryService {

    @PersistenceContext(unitName = "omb")
    Session session;

    public BreweryEntity createOrUpdate(BreweryEntity brewery) {
         session.saveOrUpdate(brewery);
         return brewery;
    }

    public void removeBrewery(Integer untappdBreweryId) {
        BreweryEntity breweryEntity = (BreweryEntity) session.get(BreweryEntity.class, untappdBreweryId);
        session.delete(breweryEntity);
    }

    public List<BreweryEntity> getBreweries() {
        return session.createCriteria(BreweryEntity.class).list();
    }
}
