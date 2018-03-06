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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * A persistence representation of an Application
 * 
 * @author mikaelnorberg
 */


@NamedQueries({
        @NamedQuery(
            name = "findAllApplicationsForUser",
            query = "SELECT app FROM Application app WHERE app.person = :user"
    )
})



@Entity(name="Application")
public class Application implements Serializable {
    
    @Id
    @SequenceGenerator(name="APPLICATION_SEQUENCE_GENERATOR", sequenceName="APPLICATION_PRIMARY_KEY_SEQUENCE",initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATION_SEQUENCE_GENERATOR")
    int application_id;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull
    Date register_date;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="PERSON",referencedColumnName="EMAIL")    
    User person;
    
    
    @ManyToOne(optional=false)
    @JoinColumn(name="STATUS",referencedColumnName="DESCRIPTION")    
    ApplicationStatus status;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    Date from_date;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    Date to_date;
    
    /**
     * Creates a new instance of Application
     */
    public Application(){}
    
    /**
     * 
     * Creates a new instance of Application
     *
     * @param user info belonging to the application about to be created.
     * @param availableFrom User is available for work from this date
     * @param availableTo User is available for work to this date
     * @param status status for the application.
     */
    public Application(UserDTO user, Date availableFrom, Date availableTo, ApplicationStatus status){
        register_date = Calendar.getInstance().getTime();
        person = (User) user;
        this.status = status;
        from_date = availableFrom;
        to_date = availableTo;
    }
    
    
    /**
     * @return String representation of applications from_date and to_date
     */
    @Override
    public String toString(){
        return " Availability: " + from_date + " -- " + to_date +".";
    }
    
}
