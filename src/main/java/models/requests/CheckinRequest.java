package models.requests;

public class CheckinRequest {
    private Integer untappdBeerId;
    private float rating;
    private String comment;

    public CheckinRequest(Integer untappdBeerId, float rating, String comment){
        this.untappdBeerId = untappdBeerId;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getUntappdBeerId() {
        return untappdBeerId;
    }

    public void setUntappdBeerId(Integer untappdBeerId) {
        this.untappdBeerId = untappdBeerId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
