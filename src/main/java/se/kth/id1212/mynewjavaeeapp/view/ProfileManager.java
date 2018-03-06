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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
import se.kth.id1212.mynewjavaeeapp.model.Application;
import se.kth.id1212.mynewjavaeeapp.model.Competence;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceProfile;
import se.kth.id1212.mynewjavaeeapp.model.UserDTO;

/**
 * The logged in users actions from the view are handled by this class
 * 
 * @author mikaelnorberg
 */
@Named("profileManager")
@ManagedBean
@SessionScoped
@Stateful
public class ProfileManager implements Serializable{
    @EJB
    private Controller controller;
    
    private List<Competence> competences;    
    private List<CompetenceProfile> competenceProfile;
    private List<Application> userApplications;
    private UIComponent component;    
    
    private UserDTO user;
    
    @NotNull(message="Choose one of the available competences.")
    private Competence chosenCompetence;
    
    @NotNull(message = "Experience field is required!")
    @Min(value=1, message = "You must have at least one year of experience.")
    @Max(value=70, message = "You must have at most 70 years of experience.")
    private int experience;
    
    @NotNull(message = "From date is required!")
    private Date availableFrom;
    
    @NotNull(message = "To date is required!")
    private Date availableTo;

    
    
    /**
     * initializes profile manager objects.
     */
    @PostConstruct
    public void init() throws IllegalStateException {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (principal != null) {
            try {
                user = controller.findUser(principal.getName());
                userApplications = controller.findAllApplicationsForUser(user);
                competenceProfile = controller.findAllCompetenceProfilesForUser(user);
                competences = controller.getCompetences(user);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    
    /**
     * Called when the user press the submit button to add competence
     * @throws java.lang.Exception when exception occurs
     */
    public void addCompetence() throws Exception{
        controller.addCompetence(experience, chosenCompetence, user);
        competences = controller.getCompetences(user);
        competenceProfile = controller.findAllCompetenceProfilesForUser(user);
    }
    
    /**
     * Called when the user press the submit button to add application for a jobs
     * @throws java.lang.Exception when exception occurs
     */
    public void submitApplication() throws Exception{
        if(availableFrom.before(availableTo)){
            controller.addApplication(user, availableFrom, availableTo);
            userApplications = controller.findAllApplicationsForUser(user);
        } else {
            FacesMessage message = new FacesMessage("to date must be after from date.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(context), message);
            availableFrom = null;
            availableTo = null;
        }
    }
    
    
    /**
     * Logout the current user by invalidating session.
     * @return Redirect to index.xhtml
     * @throws java.lang.Exception when exception occurs
     */
    public String logout() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    
    
    
    
 
    //Setters AND Getters Below
    
    public UIComponent getComponent() {
        return null;
    }


    public void setComponent(UIComponent component) {
        this.component = component;
    }
    

    public String getChosenCompetence() {
        return null;
    }
 
        
    public boolean getAvailableCompetence(){
        return !competenceProfile.isEmpty();
    }
    
    public boolean getAvailableCompetences(){
        return !competences.isEmpty();
    }
    
    public boolean getAvailableApplication(){
        return !userApplications.isEmpty();
    }
    
    public void setChosenCompetence(String chosenCompetence) {
        for (Competence comp : competences) {
            if(comp.getCompetence().equalsIgnoreCase(chosenCompetence)) {
                this.chosenCompetence = comp;
            }
        }
    }


    public List<Competence> getCompetences() {
        return competences;
    }


    public void setCompetences(ArrayList<Competence> competences) {
        this.competences = competences;
    }    
    
    public int getExperience() {
        return experience;
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    public UserDTO getUser() {
        return user;
    }
    
    public Date getAvailableFrom() {
        return null;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }


    public Date getAvailableTo() {
        return null;
    }    

   public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }
    public List<CompetenceProfile> getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(CompetenceProfile competenceProfile) {
    }



    public List<Application> getUserApplications() {
        return userApplications;
    }


    public void setUserApplications(List<Application> userApplications) {
    }
    
}
