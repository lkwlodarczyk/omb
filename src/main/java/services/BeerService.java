package services;

import entities.BeerEntity;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@Stateless
public class BeerService {

    @PersistenceContext(unitName = "omb")
    Session session;

    public BeerEntity add(BeerEntity beer) {
        session.save(beer);
        return beer;
    }

    public void removeBeer(Integer beerId) {
        BeerEntity beerEntity = session.get(BeerEntity.class, beerId);
        session.delete(beerEntity);
    }
}
