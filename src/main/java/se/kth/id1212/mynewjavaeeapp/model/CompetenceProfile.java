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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * A persistence representation of a users competence profile
 * 
 * @author mikaelnorberg
 */


@NamedQueries({
        @NamedQuery(
            name = "findAllCompetenceProfilesForUser",
            query = "SELECT cp FROM Competence_Profile cp WHERE cp.person = :user"
    )
})


@Entity(name="Competence_Profile")
public class CompetenceProfile implements Serializable{

    
    private int years_of_experience;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="COMPETENCE",referencedColumnName="COMPETENCE_NAME")   
    @NotNull
    private Competence competence;
    
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="PERSON",referencedColumnName="EMAIL")
    @NotNull
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
     * @param user Info about the user to be created
     */
    public CompetenceProfile(int years_of_experience, Competence competence, UserDTO user){
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        person = (User) user;
    }


    public int getYears_of_experience() {
        return years_of_experience;
    }


    public Competence getCompetence() {
        return competence;
    }


    public User getPerson() {
        return person;
    }
    
    /**
     * @return String representation that has info about the expertise and years of experience of the expertise
     */
    @Override
    public String toString(){
        return "Expertise: " + getCompetence() +", Years of experience: " + getYears_of_experience();
    }
}
