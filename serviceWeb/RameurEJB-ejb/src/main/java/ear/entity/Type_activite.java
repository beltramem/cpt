package ear.entity;



import static ear.entity.Type_activite.QN.ALL_ACTIVITES;

import static ear.entity.Type_activite.QN.PAR_ID;



import java.io.Serializable;

import java.util.Date;

import java.util.ArrayList;

import java.util.Collection;



import javax.persistence.*;



@Entity
@Table(name = "type_activite")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name=ALL_ACTIVITES, query="select o FROM Type_activite o"),
        @NamedQuery(name=PAR_ID, query="select o FROM Type_activite o WHERE o.id = :ID"),})
public class Type_activite implements Serializable {



    public static interface QN {

        /**

         * Search all authors.

         */

        String ALL_ACTIVITES = "type_activite.allActivites";



        /**

         * Search a named author.

         */

        String PAR_ID = "type_activite.parID";

    }



    //private static final long serialVersionUID = 0l;

    @Id
    private String id;

    private String nom;

    private String description;



    public String getId() {

        return id;

    }



    public String getNom() {

        return nom;

    }



    public String getDescription() {

        return description;

    }



    public void setId(String id) {

        this.id = id;

    }



    public void setNom(String nom) {

        this.nom = nom;

    }



    public void setDescription(String description) {

        this.description = description;

    }



    public Type_activite(String id, String nom, String description) {

        this.id = id;

        this.nom = nom;

        this.description = description;

    }



    public Type_activite() {

    }







}