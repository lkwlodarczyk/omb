package entities;

import models.requests.CheckinRequest;

import javax.persistence.*;

@Entity
@Table(name = "checkin")
public class CheckinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer untappdId;
    private Integer untappdBeerId;
    private float rating;
    private String comment;
    private String username;
    private Boolean storedInUntappd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStoredInUntappd() {
        return storedInUntappd;
    }

    public void setStoredInUntappd(Boolean storedInUntappd) {
        this.storedInUntappd = storedInUntappd;
    }

    public Integer getUntappdId() {
        return untappdId;
    }

    public void setUntappdId(Integer untappdId) {
        this.untappdId = untappdId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUntappdBeerId() {
        return untappdBeerId;
    }

    public void setUntappdBeerId(Integer untappdBeerId) {
        this.untappdBeerId = untappdBeerId;
    }

    public CheckinRequest getCheckinRequest() {
        return new CheckinRequest(this.untappdBeerId, this.rating, this.comment);
    }
}
