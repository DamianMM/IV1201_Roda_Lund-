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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author mikaelnorberg
 */



@NamedQueries({
        @NamedQuery(
            name = "findAllCompetences",
            query = "SELECT cmpt FROM Competence cmpt"
    )
})

@Entity(name="Competence")
public class Competence implements CompetenceDTO, Serializable{
    @Id
    private String competence_name;
    
    public Competence(){}

    @Override
    public String toString() {
        return competence_name;
    }
}
