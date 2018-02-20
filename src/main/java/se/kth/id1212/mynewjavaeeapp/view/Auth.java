
package se.kth.id1212.mynewjavaeeapp.view;


import java.security.Principal;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import se.kth.id1212.mynewjavaeeapp.controller.Controller;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;

@ManagedBean
@SessionScoped
public class Auth {

    private UserDTO user;

    @EJB
    private Controller controller;

    public UserDTO getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = controller.findUser(principal.getName());
            }
        }
        return user;
    }
}