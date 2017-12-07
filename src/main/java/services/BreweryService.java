package services;

import entities.BreweryEntity;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
public class BreweryService {

    @PersistenceContext(unitName = "omb")
    Session session;

    public void createOrUpdate(BreweryEntity brewery) {
        session.saveOrUpdate(brewery);
    }
}
