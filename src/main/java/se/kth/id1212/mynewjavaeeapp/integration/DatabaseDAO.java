package se.kth.id1212.mynewjavaeeapp.integration;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mynewjavaeeapp.model.Application;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceProfile;
import se.kth.id1212.mynewjavaeeapp.model.User;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;


@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class DatabaseDAO {
    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    
    public void registerUser(User person) throws EntityExistsException{
        em.persist(person);
    }

    
    public UserDTO findUser(String email) throws NoResultException {
       return em.find(User.class, email);
    }

    public List<Competence> findAllCompetences() {
        return em.createNamedQuery("findAllCompetences", Competence.class).
                getResultList();
    }

    public void addCompetence(CompetenceProfile competenceProfile) {
        em.persist(competenceProfile);
    }

    public void addApplication(Application application) {
        em.persist(application);
    }
}
