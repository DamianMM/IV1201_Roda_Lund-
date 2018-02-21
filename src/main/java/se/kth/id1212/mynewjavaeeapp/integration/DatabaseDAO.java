package se.kth.id1212.mynewjavaeeapp.integration;
//HALLÅ
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
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
        UserDTO user = em.createNamedQuery("findUserByEmail", User.class).
                setParameter("searchedEmail", email).
                getSingleResult();
        return user;
    }

    public List<Competence> findAllCompetences() {
        return em.createNamedQuery("findAllCompetences", Competence.class).
                getResultList();
    }
}
