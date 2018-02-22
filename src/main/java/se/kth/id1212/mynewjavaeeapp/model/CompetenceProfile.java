/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A persistence representation of a users competence profile
 * 
 * @author mikaelnorberg
 */
@Entity(name="Competence_Profile")
public class CompetenceProfile implements Serializable{

    
    private int years_of_experience;
    
    @Id
    private String competence;
    
    @Id
    private String person;
    
    /**
     * Creates a new instance of a competence profile
     */
    public CompetenceProfile(){}
    
    /**
     * Creates a new instance of a competence profile
     * 
     * @param years_of_experience A users experience in number of years for the chosen competence
     * @param competence Users competence
     * @param email User identifier
     */
    public CompetenceProfile(int years_of_experience, String competence, String email){
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        person = email;
    }
}
