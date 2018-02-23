
package se.kth.id1212.mynewjavaeeapp.controller;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import se.kth.id1212.mynewjavaeeapp.integration.DatabaseDAO;
import se.kth.id1212.mynewjavaeeapp.model.Actor;
import se.kth.id1212.mynewjavaeeapp.model.Application;
import se.kth.id1212.mynewjavaeeapp.model.ApplicationStatus;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceDTO;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceProfile;
import se.kth.id1212.mynewjavaeeapp.model.User;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;
import se.kth.id1212.mynewjavaeeapp.model.UserInfoDTO;

/**
 * All calls to the model and integration layers from the view passes through the controller.
 *
 * @author mikaelnorberg
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {
    
    @EJB
    DatabaseDAO dB;

    /**
     *
     * @param userInfo user information entered by the user
     * @throws EntityExistsException Thrown if user is already registered.
     */
    public void registerUser(UserInfoDTO userInfo) throws EntityExistsException {
        Actor actor = dB.findActorApplicant("applicant");
        dB.registerUser(new User(userInfo, actor));       
    }

    /**
     *
     * @param email User identifier
     * @return A user as a UserDTO
     * @throws NoResultException if user not found
     */
    public UserDTO findUser(String email) throws NoResultException {
        return dB.findUser(email);
    }

    /**
     *
     * @param email User identifier
     * @return List of all competences not already added to the users competence profile
     */
    public List<Competence> getCompetences(UserDTO userDTO) {
        User user = (User) userDTO;
        return dB.findAllCompetences(user);
    }

    /**
     *
     * @param experience Number of years of experience for the chosen competence
     * @param competence Users competence
     * @param user
     * @param userEmail User identifier
     */
    public void addCompetence(int experience, CompetenceDTO competence, UserDTO user) {
        dB.addCompetence(new CompetenceProfile(experience, competence, user));
    }

    /**
     *
     * @param user Information about the current user
     * @param availableFrom User is available for work from this date
     * @param availableTo User is available for work to this date
     */
    public void addApplication(UserDTO user, Date availableFrom, Date availableTo) {
        ApplicationStatus status = dB.findApplicationStatus("PENDING");
        dB.addApplication(new Application(user, availableFrom, availableTo, status));
    }
}