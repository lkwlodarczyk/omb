package services;

import entities.BeerEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BeerService {

    @PersistenceContext(unitName = "omb")
    Session session;

    public BeerEntity createOrUpdate(BeerEntity beer) {
        session.saveOrUpdate(beer);
        return beer;
    }

    public void removeBeer(Integer beerId) {
        BeerEntity beerEntity = (BeerEntity) session.get(BeerEntity.class, beerId);
        session.delete(beerEntity);
    }

    public List<BeerEntity> getBeersBySession(Integer sessionId) {
        Query query = session.createQuery("FROM BeerEntity b WHERE b.sessionId = :sessionId");
        query.setParameter("sessionId", sessionId);
        List list = query.list();
        return list;
    }

    public List<BeerEntity> getBeers() {
        return session.createCriteria(BeerEntity.class).list();
    }
}
