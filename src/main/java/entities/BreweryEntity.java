package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brewery")
public class BreweryEntity {

    @Id
    private Integer untappdId;
    @NotNull
    private String name;
    private Integer position;

    public Integer getUntappdId() {
        return untappdId;
    }

    public void setUntappdId(Integer untappdId) {
        this.untappdId = untappdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
