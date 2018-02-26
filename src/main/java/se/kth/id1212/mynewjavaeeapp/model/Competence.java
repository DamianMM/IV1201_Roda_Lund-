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

/**
 * A persistence representation of a competence
 * 
 * @author mikaelnorberg
 */



@NamedQueries({
        @NamedQuery(
            name = "findAllCompetencesNotAllreadyPartOfCompetenceProfile",
            query = "SELECT c FROM Competence as c, Competence_Profile as cp, Person as p WHERE c != cp.competence AND cp.person = :user"
    )
})


@Entity(name="Competence")
public class Competence implements CompetenceDTO, Serializable{
    
    
    @Id
    @Column(name="COMPETENCE_NAME")
    private String competence_name;
    
    /**
     * Creates a new instance of Account. 
     */
    public Competence(){}

    @Override
    public String toString() {
        return getCompetence();
    }

    @Override
    public String getCompetence() {
        return competence_name;
    }
}
