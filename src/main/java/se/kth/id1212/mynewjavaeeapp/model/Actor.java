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
 *
 * @author mikaelnorberg
 */

@Entity(name="Actor")
public class Actor implements Serializable {
    
    @Id
    private String actor_name;
    
    public Actor() {}
}
