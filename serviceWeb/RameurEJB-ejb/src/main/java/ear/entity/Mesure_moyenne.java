package ear.entity;

import javax.persistence.*;


import java.io.Serializable;

import static ear.entity.Mesure_moyenne.QN.PAR_UTILISATEUR;
import static ear.entity.Mesure_moyenne.QN.PAR_UTILISATEUR_TYPE_ENTRAINEMENT;
import static ear.entity.Mesure_moyenne.QN.PAR_TYPE_ENTRAINEMENT;


@Entity
@Table(name = "mesure_moyenne")
@NamedQueries({@NamedQuery(name=PAR_UTILISATEUR, query="select o FROM Mesure_moyenne o WHERE o.utilisateur = :ID"),
        @NamedQuery(name=PAR_UTILISATEUR_TYPE_ENTRAINEMENT, query="select o FROM Mesure_moyenne o WHERE o.type_activite = :IDTYPE and o.utilisateur = :IDUSER"),
        @NamedQuery(name=PAR_TYPE_ENTRAINEMENT, query="select o FROM Mesure_moyenne o WHERE o.type_activite = :ID")
})
public class Mesure_moyenne implements Serializable {

    public static interface QN {

        String PAR_UTILISATEUR = "mesure_moyenne.parUtilisateur";

        String PAR_UTILISATEUR_TYPE_ENTRAINEMENT = "mesure_moyenne.parUtilisateurTypeEntrainement";

        String PAR_TYPE_ENTRAINEMENT = "mesure_moyenne.parTypeEntrainement";
    }

    @Id
    @ManyToOne
    @JoinColumn(name="identifiant_utilisateur",referencedColumnName = "identifiant")
    private Utilisateur utilisateur;
    @Id
    @ManyToOne
    @JoinColumn(name="type_activite",referencedColumnName = "id")
    private Type_activite type_activite;
    private double vitesse_moyenne;
    private double distance_parcourue_totale;
    private double total_calories_brulees;
    private double puissance_developpe_moyenne;
    private double rythme_cardiaque_moyen;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Type_activite getType_activite() {
        return type_activite;
    }

    public void setType_activite(Type_activite type_activite) {
        this.type_activite = type_activite;
    }

    public double getVitesse_moyenne() {
        return vitesse_moyenne;
    }

    public void setVitesse_moyenne(double vitesse_moyenne) {
        this.vitesse_moyenne = vitesse_moyenne;
    }

    public double getDistance_parcourue_totale() {
        return distance_parcourue_totale;
    }

    public void setDistance_parcourue_totale(double distance_parcourue_totale) {
        this.distance_parcourue_totale = distance_parcourue_totale;
    }

    public double getTotal_calories_brulees() {
        return total_calories_brulees;
    }

    public void setTotal_calories_brulees(double total_calories_brulees) {
        this.total_calories_brulees = total_calories_brulees;
    }

    public double getPuissance_developpe_moyenne() {
        return puissance_developpe_moyenne;
    }

    public void setPuissance_developpe_moyenne(double puissance_developpe_moyenne) {
        this.puissance_developpe_moyenne = puissance_developpe_moyenne;
    }

    public double getRythme_cardiaque_moyen() {
        return rythme_cardiaque_moyen;
    }

    public void setRythme_cardiaque_moyen(double rythme_cardiaque_moyen) {
        this.rythme_cardiaque_moyen = rythme_cardiaque_moyen;
    }

    public Mesure_moyenne() {
    }

    public Mesure_moyenne(Utilisateur utilisateur, Type_activite type_activite, double vitesse_moyenne, double distance_parcourue_totale, double total_calories_brulees, double puissance_developpe_moyenne, double rythme_cardiaque_moyen) {
        this.utilisateur = utilisateur;
        this.type_activite = type_activite;
        this.vitesse_moyenne = vitesse_moyenne;
        this.distance_parcourue_totale = distance_parcourue_totale;
        this.total_calories_brulees = total_calories_brulees;
        this.puissance_developpe_moyenne = puissance_developpe_moyenne;
        this.rythme_cardiaque_moyen = rythme_cardiaque_moyen;
    }
}
