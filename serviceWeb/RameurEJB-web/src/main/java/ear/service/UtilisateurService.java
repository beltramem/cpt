package ear.service;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ear.entity.Utilisateur;
import ear.session.UtilisateurLocal;

import java.util.Date;

@Path("/UtilisateurService")
public class UtilisateurService {
    @EJB
    private  UtilisateurLocal user;

    @GET
    @Path("/creationCompte/{identifiant}/{mdp}/{nom}/{prenom}/{taille}/{poids}/{date_naissance}")
    @Produces(MediaType.APPLICATION_JSON)
    public void creationCompte (
            @PathParam(value="identifiant") String identifiant,
            @PathParam(value="mdp") String mdp,
            @PathParam(value="nom")String nom,
            @PathParam(value="prenom")String prenom,
            @PathParam(value="taille")float taille,
            @PathParam(value="poids")float poids,
            @PathParam(value="date_naissance")Date date_naissance) throws Exception {
        user.creationCompte(identifiant,mdp,nom,prenom,taille,poids,date_naissance);
    }

    @GET
    @Path("/connexion/{identifiant}/{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur connexion(@PathParam(value="identifiant") String identifiant, @PathParam(value="mdp") String mdp) throws Exception {
        return user.connexion(identifiant,mdp);
    }


    public void updateCompte(Utilisateur pUser) throws Exception
    {
        user.updateCompte(pUser);
    }

}
