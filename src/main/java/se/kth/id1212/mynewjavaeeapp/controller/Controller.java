
package se.kth.id1212.mynewjavaeeapp.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import se.kth.id1212.mynewjavaeeapp.integration.DatabaseDAO;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
import se.kth.id1212.mynewjavaeeapp.model.User;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;
import se.kth.id1212.mynewjavaeeapp.model.UserInfoDTO;
//HEJ


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {
    
    @EJB
    DatabaseDAO dB;

    public void registerUser(UserInfoDTO userInfo) throws EntityExistsException {
       dB.registerUser(new User(userInfo));
       
    }

    
    public UserDTO findUser(String email) throws NoResultException {
        return dB.findUser(email);
    }

    public List<Competence> getCompetences() {
        return dB.findAllCompetences();
    }
}