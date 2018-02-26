/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * A persistence representation of a users competence profile
 * 
 * @author mikaelnorberg
 */
@Entity(name="Competence_Profile")
public class CompetenceProfile implements Serializable{

    
    private int years_of_experience;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="COMPETENCE",referencedColumnName="COMPETENCE")    
    private Competence competence;
    
    /*
    @Id
    private String person;
    */
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="PERSON",referencedColumnName="PERSON")
    private User person;
    
    
    /**
     * Creates a new instance of a competence profile
     */
    public CompetenceProfile(){}
    
    /**
     * Creates a new instance of a competence profile
     * 
     * @param years_of_experience A users experience in number of years for the chosen competence
     * @param competence Users competence
     * @param user User identifier
     */
    public CompetenceProfile(int years_of_experience, CompetenceDTO competence, UserDTO user){
        this.years_of_experience = years_of_experience;
        this.competence = (Competence) competence;
        person = (User) user;
    }

    /**
     * @return the years_of_experience
     */
    public int getYears_of_experience() {
        return years_of_experience;
    }

    /**
     * @return the competence
     */
    public Competence getCompetence() {
        return competence;
    }

    /**
     * @return the person
     */
    public User getPerson() {
        return person;
    }
}
