package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "beer")
public class BeerEntity {

    @Id
    private Integer untappdId;
    @NotNull
    private String name;
    private Float rating;
    @ManyToOne(fetch = FetchType.EAGER)
    private BreweryEntity brewery;
    private Integer sessionId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public BreweryEntity getBrewery() {
        return brewery;
    }

    public void setBrewery(BreweryEntity brewery) {
        this.brewery = brewery;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUntappdId() {
        return untappdId;
    }

    public void setUntappdId(Integer beerUntappdId) {
        this.untappdId = beerUntappdId;
    }
}
