package models.untappd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class UntappdResponse {

    private UntappdBeer beer;
    private UntappdBeers beers;
    private UntappdBeers homebrew;
    private String result;
    @JsonProperty("checkin_id")
    private Integer checkinId;


    public UntappdBeer getBeer() {
        return beer;
    }

    public void setBeer(UntappdBeer beer) {
        this.beer = beer;
    }


    public UntappdBeers getBeers() {
        return beers;
    }

    public void setBeers(UntappdBeers beers) {
        this.beers = beers;
    }

    public UntappdBeers getHomebrew() {
        return homebrew;
    }

    public void setHomebrew(UntappdBeers homebrew) {
        this.homebrew = homebrew;
    }


    public List<UntappdBeer> getAllBeers() {
        List<UntappdBeer> untappdBeers = getBeers().getItems().stream().map(item -> {
            UntappdBeer untappdBeer = item.getBeer();
            untappdBeer.setBrewery(item.getBrewery());
            return untappdBeer;
        }).collect(Collectors.toList());

        untappdBeers.addAll(getHomebrew().getItems().stream().map(item -> {
            UntappdBeer untappdBeer = item.getBeer();
            untappdBeer.setBrewery(item.getBrewery());
            return untappdBeer;
        }).collect(Collectors.toList()));

        return untappdBeers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Integer checkinId) {
        this.checkinId = checkinId;
    }
}

