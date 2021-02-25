package ear.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activite_libre")
public class Activite_libre extends Type_activite {

    public Activite_libre(String id, String nom, String description, double duree) {
        super(id, nom, description);
    }

    public Activite_libre() {}
}
