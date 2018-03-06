package se.kth.id1212.mynewjavaeeapp.integration;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import se.kth.id1212.mynewjavaeeapp.model.Actor;
import se.kth.id1212.mynewjavaeeapp.model.Application;
import se.kth.id1212.mynewjavaeeapp.model.ApplicationStatus;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceProfile;
import se.kth.id1212.mynewjavaeeapp.model.User;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;

/**
 * Handles all interaction with the entity manager. No code outside of this class, except for the
 * JPA entities, shall have anything to do with JPA.
 * 
 * @author mikaelnorberg
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class DatabaseDAO {
    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    /**
     * Registers a new user
     * @param person The user who wants to register
     * @throws EntityExistsException Thrown if user already exist in db
     */
    public void registerUser(User person) throws EntityExistsException{
        em.persist(person);
    }

    /**
     * finds existing user by email
     * @param email user identifier
     * @return Found user
     * @throws NoResultException if user is not registered
     */
    public UserDTO findUser(String email) throws NoResultException {
       return em.find(User.class, email);
    }
    
    /**
     *
     * @param actor role to be found.
     * @return searched actor object.
     */
    public Actor findActorApplicant(String actor) {
        return em.find(Actor.class, actor);
    }
    
    /**
     *
     * @param status to be found.
     * @return application status object.
     */
    public ApplicationStatus findApplicationStatus(String status) {
        return em.find(ApplicationStatus.class, status);
    }

    /**
     * Lists all competences not already added to the users competence profile
     * @param user used to not return competences already belonging to user.
     * @return List of competences not belonging to @param user.
     */
    public List<Competence> findAllCompetences(User user) {
        return em.createNamedQuery("findAllCompetencesNotAlreadyInCompetenceProfile", Competence.class).
                setParameter("user", user).
                getResultList();
    }
    
    /**
     *
     * @param user the user to get competence profiles for.
     * @return list of competence profiles for @param user.   
     */
    public List<CompetenceProfile> findAllCompetenceProfilesForUser(User user) {
        return em.createNamedQuery("findAllCompetenceProfilesForUser", CompetenceProfile.class).
                setParameter("user", user).
                getResultList();
    }
    
    /**
     *
     * @param user search for applications belonging to user.
     * @return list of applications belonging to user.
     */
    public List<Application> findAllApplicationsForUser(User user) {
        return em.createNamedQuery("findAllApplicationsForUser", Application.class).
                setParameter("user", user).
                getResultList();
    }

    /**
     * Add a competence profile for current user
     * @param competenceProfile the users competence profile
     */
    public void addCompetence(CompetenceProfile competenceProfile) {
        em.persist(competenceProfile);
    }

    /**
     * Add a application for current user
     * @param application users application
     */
    public void addApplication(Application application) {
        em.persist(application);
    }
}
