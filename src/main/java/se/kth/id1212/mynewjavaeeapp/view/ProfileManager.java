/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.view;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import se.kth.id1212.mynewjavaeeapp.controller.Controller;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceDTO;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;

/**
 * The logged in users actions from the view are handled by this class
 * 
 * @author mikaelnorberg
 */
@Named("profileManager")
@ManagedBean
@SessionScoped
public class ProfileManager implements Serializable{
    @EJB
    private Controller controller;
    
    private ArrayList<CompetenceDTO> competences;
    
    private String chosenCompetence;
    
    @NotNull(message = "Experience field is required!")
    @Min(value=1, message = "You must have at least one year of experience.")
    @Max(value=70, message = "You must have at most 70 years of experience.")
    private int experience;
    
    private UserDTO user;
    
    @NotNull(message = "From date is required!")
    private Date availableFrom;
    
    @NotNull(message = "To date is required!")
    private Date availableTo;
    
    private UIComponent availableto;


    /**
     * @return the chosenCompetence
     */
    public String getChosenCompetence() {
        return null;
    }

    /**
     * @param chosenCompetence the chosenCompetence to set
     */
    public void setChosenCompetence(String chosenCompetence) {
        this.chosenCompetence = chosenCompetence;
    }

    /**
     * @return List of all competences not already added to the users competence profile
     */
    public List<? extends CompetenceDTO> getCompetences() {
        return controller.getCompetences(user.getEmail());
    }

    /**
     * @param competences the competences to set
     */
    public void setCompetences(ArrayList<CompetenceDTO> competences) {
        this.competences = competences;
    }
    
    /**
     *  Called when the user press the submit button to add competence
     */
    public void addCompetence(){
        controller.addCompetence(experience, chosenCompetence, user.getEmail());
    }
    
    /**
     * Called when the user press the submit button to add application for a job
     */
    public void addApplication(){
        if(availableTo.after(availableFrom)){
            controller.addApplication(user, availableFrom, availableTo);            
        } else {
            FacesMessage message = new FacesMessage("to date must be after from date.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("availableTo", message);
            availableFrom = null;
            availableTo = null;
        }
    }
    

    /**
     * @return the experience 
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @param experience the experience to set
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    /**
     *
     * @return The logged in user for the current session
     */
    public UserDTO getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = controller.findUser(principal.getName());
            }
        }
        return user;
    }
        
    /**
     *
     * @return Redirect to index.xhtml
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    /**
     * @return the availableFrom
     */
    public Date getAvailableFrom() {
        return null;
    }

    /**
     * @param availableFrom the availableFrom to set
     */
    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    /**
     * @return the availableTo
     */
    public Date getAvailableTo() {
        return null;
    }

    /**
     * @param availableTo the availableTo to set
     */
    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

   
}
