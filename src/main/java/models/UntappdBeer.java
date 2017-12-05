package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UntappdBeer {
    @JsonProperty("bid")
    public Integer id;
    @JsonProperty("beer_name")
    public String name;
    @JsonProperty("rating_score")
    public Float rating;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
