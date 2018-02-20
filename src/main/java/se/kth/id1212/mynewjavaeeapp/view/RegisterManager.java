/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.mynewjavaeeapp.view;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import se.kth.id1212.mynewjavaeeapp.controller.Controller;
import se.kth.id1212.mynewjavaeeapp.model.UserInfoDTO;

/**
 *
 * @author mikaelnorberg
 */
@Named("registerManager")
@RequestScoped
public class RegisterManager implements Serializable{
    @EJB
    private Controller controller;
    private String firstName;
    
   @Size(max=100,
             message = "Last name must be less than 100 characters.")
    @Pattern(regexp = "^[\\p{L}\\s'.-]+$", 
             message = "Please enter a valid last name.")
    private String lastName;
    
    @NotNull(message = "Date of birth is required!")
    private Date dateOfBirth;
    
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", 
             message = "Email format is invalid.")
    private String email;
    
   @Size(min=3, max=100,
             message = "Enter a valid password. It must be 3-100 characters.")    
    private String password;
    private UserInfoDTO user;
    private Exception registerFailure;
    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void register() {
        
        user = new UserInfoDTO(firstName, lastName, dateOfBirth, email, password);
        try{
            registerFailure = null;
            controller.registerUser(user);
        }catch(Exception e){
            registerFailure = e;
        }
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public boolean getSuccess(){
        return registerFailure == null;
    }
}
