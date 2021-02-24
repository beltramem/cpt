package com.M2BDIA;

import java.io.Serializable;
import java.util.Date;

public class Mesure implements Serializable {
    private int id;
    private String identifiant_utilisateur;
    private Date date;
    private float vitesse;
    private float distance_parcourue;
    private int calories_brulees;
    private float puissance_developpe;
    private int rythme_cardiaque;
    private int identifiant_course;
    private int getIdentifiant_entrainement;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant_utilisateur() {
        return identifiant_utilisateur;
    }

    public void setIdentifiant_utilisateur(String identifiant_utilisateur) {
        this.identifiant_utilisateur = identifiant_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    public float getDistance_parcourue() {
        return distance_parcourue;
    }

    public void setDistance_parcourue(float distance_parcourue) {
        this.distance_parcourue = distance_parcourue;
    }

    public int getCalories_brulees() {
        return calories_brulees;
    }

    public void setCalories_brulees(int calories_brulees) {
        this.calories_brulees = calories_brulees;
    }

    public float getPuissance_developpe() {
        return puissance_developpe;
    }

    public void setPuissance_developpe(float puissance_developpe) {
        this.puissance_developpe = puissance_developpe;
    }

    public int getRythme_cardiaque() {
        return rythme_cardiaque;
    }

    public void setRythme_cardiaque(int rythme_cardiaque) {
        this.rythme_cardiaque = rythme_cardiaque;
    }

    public int getIdentifiant_course() {
        return identifiant_course;
    }

    public void setIdentifiant_course(int identifiant_course) {
        this.identifiant_course = identifiant_course;
    }

    public int getGetIdentifiant_entrainement() {
        return getIdentifiant_entrainement;
    }

    public void setGetIdentifiant_entrainement(int getIdentifiant_entrainement) {
        this.getIdentifiant_entrainement = getIdentifiant_entrainement;
    }

    public Mesure(int id, String identifiant_utilisateur, Date date, float vitesse, float distance_parcourue, int calories_brulees, float puissance_developpe, int rythme_cardiaque, int identifiant_course, int identifiant_entrainement) {
        this.id = id;
        this.identifiant_utilisateur = identifiant_utilisateur;
        this.identifiant_course = identifiant_course;
        this.identifiant_utilisateur = identifiant_utilisateur;
        this.date = date;
        this.vitesse = vitesse;
        this.distance_parcourue = distance_parcourue;
        this.calories_brulees = calories_brulees;
        this.puissance_developpe = puissance_developpe;
        this.rythme_cardiaque = rythme_cardiaque;
    }


}
