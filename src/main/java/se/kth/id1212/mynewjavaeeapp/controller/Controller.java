
package se.kth.id1212.mynewjavaeeapp.controller;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import se.kth.id1212.mynewjavaeeapp.integration.DatabaseDAO;
import se.kth.id1212.mynewjavaeeapp.model.Actor;
import se.kth.id1212.mynewjavaeeapp.model.Application;
import se.kth.id1212.mynewjavaeeapp.model.ApplicationStatus;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
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
     * @throws java.lang.Exception for all exceptions
     */
    public void registerUser(UserInfoDTO userInfo) throws Exception {
        Actor actor = dB.findActorApplicant("applicant");
        dB.registerUser(new User(userInfo, actor));
    }

    /**
     *
     * @param email User identifier
     * @return A user as a UserDTO
     * @throws java.lang.Exception for all exceptions
     */
    public UserDTO findUser(String email) throws Exception {
        return dB.findUser(email);
    }

    /**
     *
     * @param user to get competences for.
     * @return List of all competences not already added to the users competence profile
     * @throws java.lang.Exception for all exceptions
     */
    public List<Competence> getCompetences(UserDTO user) throws Exception {
        return dB.findAllCompetences((User)user);
    }

    /**
     *
     * @param experience Number of years of experience for the chosen competence
     * @param competence Users competence
     * @param user to add competence for.
     * @throws java.lang.Exception for all exceptions
     */
    public void addCompetence(int experience, Competence competence, UserDTO user) throws Exception {
        dB.addCompetence(new CompetenceProfile(experience, competence, user));
    }

    /**
     *
     * @param user to add application info for.
     * @param availableFrom User is available for work from this date
     * @param availableTo User is available for work to this date
     * @throws java.lang.Exception for all exceptions
     */
    public void addApplication(UserDTO user, Date availableFrom, Date availableTo) throws Exception {
        ApplicationStatus status = dB.findApplicationStatus("PENDING");
        dB.addApplication(new Application(user, availableFrom, availableTo, status));
    }

    /**
     *
     * @param user to get competence profiles for.
     * @return List of competence profiles of @param user. 
     * @throws java.lang.Exception for all exceptions
     */
    public List<CompetenceProfile> findAllCompetenceProfilesForUser(UserDTO user) throws Exception {
        return dB.findAllCompetenceProfilesForUser((User)user);
    }

    /**
     *
     * @param user to get applications for.
     * @return List of applications of @param user. 
     * @throws java.lang.Exception for all exceptions
     */
    public List<Application> findAllApplicationsForUser(UserDTO user) throws Exception {
        return dB.findAllApplicationsForUser((User)user);
    }
}