package ear.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activite_distance")
public class Activite_distance extends Type_activite {
    @Column
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Activite_distance(String id, String nom, String description, double distance) {
        super(id, nom, description);
        this.distance = distance;
    }

    public Activite_distance(double distance) {
        this.distance = distance;
    }
}
