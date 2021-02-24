package ear.session;

import ear.entity.Utilisateur;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface UtilisateurLocal {
    public void creationCompte(String identifiant, String mdp, String nom, String prenom, double taille, double poids, Date date_naissance) throws Exception;
    public Utilisateur connexion(String utilisateur, String mdp) throws  Exception;
    public void updateCompte(Utilisateur user) throws  Exception;
}
