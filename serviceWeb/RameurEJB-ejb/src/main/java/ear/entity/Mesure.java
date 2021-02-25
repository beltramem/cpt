package ear.entity;



import static ear.entity.Mesure.QN.ALL_MESURES;
import static ear.entity.Mesure.QN.PAR_UTILISATEUR;
import static ear.entity.Mesure.QN.PAR_ENTRAINEMENT;
import static ear.entity.Mesure.QN.PAR_COURSE;



import java.io.Serializable;

import java.util.Date;

import java.util.ArrayList;

import java.util.Collection;



import javax.persistence.*;



@Entity

@Table(name = "mesure")

@NamedQueries({@NamedQuery(name=ALL_MESURES, query="select o FROM Mesure o"),

        @NamedQuery(name=PAR_UTILISATEUR, query="select o FROM Mesure o WHERE o.identifiant_utilisateur = :ID"),

        @NamedQuery(name=PAR_ENTRAINEMENT, query="select o FROM Mesure o WHERE o.id_entrainement = :ID"),

        @NamedQuery(name=PAR_COURSE, query="select o FROM Mesure o WHERE o.id_course = :ID")})



public class Mesure implements Serializable {



    public static interface QN {

        /**

         * Search all authors.

         */

        String ALL_MESURES = "mesure.allMesures";



        /**

         * Search a named author.

         */

        String PAR_UTILISATEUR = "mesure.parUtilisateur";



        /**

         * Search a named author.

         */

        String PAR_ENTRAINEMENT = "mesure.parEntrainement";



        /**

         * Search a named author.

         */

        String PAR_COURSE = "mesure.parCourse";

    }



    //private static final long serialVersionUID = 0l;

    @Id
    private String id;

    private String identifiant_utilisateur;

    private String id_course;

    private String id_entrainement;

    private Date date;

    private double vitesse;

    private double distance_parcourue;

    private double calories_brulees;

    private double puissance_developpe;

    private double rythme_cardiaque;









    public String getId() {

        return id;

    }



    public void setId(String identifiant) {

        this.id = identifiant;

    }



    public String getIdentifiant_utilisateur() {

        return identifiant_utilisateur;

    }



    public void setIdentifiant_utilisateur(String identifiant) {

        this.identifiant_utilisateur = identifiant;

    }



    public String getId_course() {

        return id_course;

    }



    public void setId_course(String identifiant) {

        this.id_course = identifiant;

    }



    public String getId_entrainement() {

        return id_entrainement;

    }



    public void setId_entrainement(String identifiant) {

        this.id_entrainement = identifiant;

    }



    public Date getDate() {

        return date;

    }



    public double getVitesse() {

        return vitesse;

    }



    public double getDistance_parcourue() {

        return distance_parcourue;

    }



    public double getCalories_brulees() {

        return calories_brulees;

    }



    public double getPuissance_developpe() {

        return puissance_developpe;

    }



    public double getRythme_cardiaque() {

        return rythme_cardiaque;

    }



    public void setDate(Date date) {

        this.date = date;

    }



    public void setVitesse(double vitesse) {

        this.vitesse = vitesse;

    }



    public void setDistance_parcourue(double distance_parcourue) {

        this.distance_parcourue = distance_parcourue;

    }



    public void setCalories_brulees(double calories_brulees) {

        this.calories_brulees = calories_brulees;

    }



    public void setPuissance_developpe(double puissance_develloppe) {

        this.puissance_developpe = puissance_develloppe;

    }



    public void setRythme_cardiaque(double rythme_cardiaque) {

        this.rythme_cardiaque = rythme_cardiaque;

    }



    public Mesure(String id, String identifiant_utilisateur, String id_course, String id_entrainement, Date date, double vitesse, double distance_parcourue, double calories_brulees, double puissance_develloppe) {

        this.id = id;

        this.identifiant_utilisateur = identifiant_utilisateur;

        this.id_course = id_course;

        this.id_entrainement = id_entrainement;

        this.date = date;

        this.vitesse = vitesse;

        this.distance_parcourue = distance_parcourue;

        this.calories_brulees = calories_brulees;

        this.puissance_developpe = puissance_develloppe;

    }











    public Mesure(String id, String identifiant_utilisateur, String id_course, String id_entrainement, Date date, double vitesse, double distance_parcourue, double calories_brulees, double puissance_develloppe, double rythme_cardiaque) {

        this.id = id;

        this.identifiant_utilisateur = identifiant_utilisateur;

        this.id_course = id_course;

        this.id_entrainement = id_entrainement;

        this.date = date;

        this.vitesse = vitesse;

        this.distance_parcourue = distance_parcourue;

        this.calories_brulees = calories_brulees;

        this.puissance_developpe = puissance_develloppe;

        this.rythme_cardiaque = rythme_cardiaque;

    }







    public Mesure()

    {

    }

}