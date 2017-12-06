package models.Untappd;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UntappdBrewery {

    @JsonProperty("brewery_id")
    private Integer id;
    @JsonProperty("brewery_name")
    private String name;
    @JsonProperty("country_name")
    private String country;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
