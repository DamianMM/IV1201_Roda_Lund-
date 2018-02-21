/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import se.kth.id1212.mynewjavaeeapp.controller.Controller;
import se.kth.id1212.mynewjavaeeapp.model.CompetenceDTO;

/**
 *
 * @author mikaelnorberg
 */
@Named("profileManager")
@SessionScoped
public class ProfileManager implements Serializable{
    @EJB
    private Controller controller;
    private ArrayList<CompetenceDTO> competences;
    private String chosenCompetence;
    private int experience;


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
     * @return the competences
     */
    public List<? extends CompetenceDTO> getCompetences() {
        return controller.getCompetences();
    }

    /**
     * @param competences the competences to set
     */
    public void setCompetences(ArrayList<CompetenceDTO> competences) {
        this.competences = competences;
    }
    
    public void addCompetence(){
        Auth auth = new Auth();
        String userEmail = auth.getUser().getEmail();
        controller.addCompetence(experience, chosenCompetence, userEmail);
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
   
}
