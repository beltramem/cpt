package ear.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activite_calories")
public class Activite_calories extends Type_activite {
    @Column
    private double calorie_brulee;

    public double getDistance() {
        return calorie_brulee;
    }

    public void setDistance(double calorie_brulee) {
        this.calorie_brulee = calorie_brulee;
    }

    public Activite_calories(String id, String nom, String description, double calorie_brulee) {
        super(id, nom, description);
        this.calorie_brulee = calorie_brulee;
    }

    public Activite_calories(double calorie_brulee) {
        this.calorie_brulee = calorie_brulee;
    }
}
