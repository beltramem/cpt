package ear.service;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;

import ear.entity.Utilisateur;
import ear.session.UtilisateurLocal;

import java.util.Date;

@WebService
public class UtilisateurService {
    @EJB
    private  UtilisateurLocal user;

    public void creationCompte (
            @WebParam(name="identifiant")String identifiant,
            @WebParam(name="mdp") String mdp,
            @WebParam(name="nom")String nom,
            @WebParam(name="prenom")String prenom,
            @WebParam(name="taille")float taille,
            @WebParam(name="poids")float poids,
            @WebParam(name="date_naissance")Date date_naissance) throws Exception {
        user.creationCompte(identifiant,mdp,nom,prenom,taille,poids,date_naissance);
    }

    public Utilisateur connexion(@WebParam(name="identifiant")String identifiant, @WebParam(name = "mdp")String mdp) throws Exception {
        return user.connexion(identifiant,mdp);
    }

    public void updateCompte(Utilisateur pUser) throws Exception
    {
        user.updateCompte(pUser);
    }

}
