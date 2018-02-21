/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author mikaelnorberg
 */
@Entity(name="Competence_Profile")
public class CompetenceProfile implements Serializable{
    
    @Id
    @SequenceGenerator(name="COMPETENCE_PROFILE_SEQUENCE_GENERATOR", sequenceName="COMPETENCE_PROFILE_PRIMARY_KEY_SEQUENCE",initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPETENCE_PROFILE_SEQUENCE_GENERATOR")    
    private int competence_profile_id;
    
    private int years_of_experience;
    
    private String competence;
    
    private String person;
    
    public CompetenceProfile(){}
    
    public CompetenceProfile(int years_of_experience, String competence, String email){
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        person = email;
    }
    
}
