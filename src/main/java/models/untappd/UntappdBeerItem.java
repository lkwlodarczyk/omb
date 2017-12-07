package models.untappd;

public class UntappdBeerItem {
    private UntappdBeer beer;
    private UntappdBrewery brewery;

    public UntappdBeer getBeer() {
        return beer;
    }

    public void setBeer(UntappdBeer beer) {
        this.beer = beer;
    }


    public UntappdBrewery getBrewery() {
        return brewery;
    }

    public void setBrewery(UntappdBrewery brewery) {
        this.brewery = brewery;
    }
}
