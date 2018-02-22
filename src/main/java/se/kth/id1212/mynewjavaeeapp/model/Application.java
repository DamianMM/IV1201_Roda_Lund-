/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A persistence representation of an Application
 * 
 * @author mikaelnorberg
 */
@Entity(name="Application")
public class Application implements Serializable {
    
    @Id
    @SequenceGenerator(name="APPLICATION_SEQUENCE_GENERATOR", sequenceName="APPLICATION_PRIMARY_KEY_SEQUENCE",initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATION_SEQUENCE_GENERATOR")                
    int application_id;
    
    @Temporal(TemporalType.DATE)
    Date register_date;
    
    String person;
    
    String status;
    
    @Temporal(TemporalType.DATE)
    Date from_date;
    
    @Temporal(TemporalType.DATE)
    Date to_date;
    
    /**
     * Creates a new instance of Application
     */
    public Application(){}
    
    /**
     * 
     * Creates a new instance of Application
     *
     * @param userEmail User identifier
     * @param availableFrom User is available for work from this date
     * @param availableTo User is available for work to this date
     */
    public Application(String userEmail, Date availableFrom, Date availableTo){
        register_date = Calendar.getInstance().getTime();
        person = userEmail;
        status = "PENDING";
        from_date = availableFrom;
        to_date = availableTo;
    }
    
}
