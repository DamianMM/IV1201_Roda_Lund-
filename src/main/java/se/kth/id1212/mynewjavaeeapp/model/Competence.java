/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * A persistence representation of a competence
 * 
 * @author mikaelnorberg
 */



@NamedQueries({
        @NamedQuery(
            name = "findAllCompetencesNotAlreadyInCompetenceProfile",
            query = "SELECT c FROM Competence c WHERE c NOT IN (SELECT cp.competence FROM Competence_Profile cp WHERE cp.person = :user)"
    )
})


@Entity(name="Competence")
public class Competence implements Serializable{
    
    
    @Id
    @Column(name="COMPETENCE_NAME")
    @NotNull
    private String competence_name;
    
    /**
     * Creates a new instance of Competence. 
     */
    public Competence(){}

    
    /**
     * @return String representation of the competence
     */
    @Override
    public String toString() {
        return getCompetence();
    }

    public String getCompetence() {
        return competence_name;
    }
}
