package ear.session;

import ear.entity.Utilisateur;
import static ear.entity.Utilisateur.QN.FIND_COMPTE;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Queue;

@Stateless
@Local(UtilisateurLocal.class)
@Remote(UtilisateurRemote.class)
public class UtilisateurBean  implements  UtilisateurLocal, UtilisateurRemote{
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void creationCompte(String identifiant, String mdp, String nom, String prenom, double taille, double poids, Date date_naissance) throws Exception {
        Query query = em.createNamedQuery(FIND_COMPTE);
        query.setParameter("ID", identifiant);
        List<Utilisateur> uExist = query.getResultList();
        if (uExist.size() == 0) {
            Utilisateur u = new Utilisateur(identifiant, mdp, nom, prenom, taille, poids, date_naissance);
            em.persist(u);
        }
        else throw new RuntimeException("compte Existant");
    }

    public void updateCompte(Utilisateur user) throws Exception
    {
        Query query = em.createNamedQuery(FIND_COMPTE);
        query.setParameter("IO",user.getIdentifiant());
        List<Utilisateur> utilisateur = query.getResultList();
        if (utilisateur.size() == 0) {
            throw new RuntimeException("compte introuvable");
        }
        else {
            utilisateur.get(0).setMdp(user.getMdp());
            utilisateur.get(0).setNom(user.getNom());
            utilisateur.get(0).setPrenom(user.getPrenom());
            utilisateur.get(0).setPoids(user.getPoids());
            utilisateur.get(0).setTaille(user.getTaille());
        }
    }

    public Utilisateur connexion(String id, String mdp) throws Exception {
        Query query = em.createNamedQuery(FIND_COMPTE);
        query.setParameter("ID", id);
        List<Utilisateur> utilisateur = query.getResultList();
        if (utilisateur.size() == 0) {
            throw new RuntimeException("compte introuvable");
        }
        if (utilisateur.get(0).getMdp().equals(mdp))
        {
            return utilisateur.get(0);
        }
        else
        {
            throw new RuntimeException("mdp non valide");
        }

    }

}
