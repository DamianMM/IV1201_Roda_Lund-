/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * A persistence representation of an Applications status
 * @author mikaelnorberg
 */

@Entity(name="Application_Status")
public class ApplicationStatus implements Serializable {
    
    @Id
    @NotNull
    private String description;
    
    /**
     * Creates a new instance of ApplicationStatus
     */
    public ApplicationStatus(){}
    
}
