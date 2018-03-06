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
 * Handles a user registration process
 * 
 * @author mikaelnorberg
 */
@Named("registerManager")
@RequestScoped
public class RegisterManager implements Serializable{
    @EJB
    private Controller controller;
    
    @Size(max=100,
             message = "First name must be less than 100 characters.")
    @Pattern(regexp = "^[\\p{L}\\s'.-]+$", 
             message = "Please enter a valid first name.")    
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
     * Registers user. Called when the user press the submit registration button.
     * @return String with navigation outcome
     */
    public String register() {
        
        user = new UserInfoDTO(firstName, lastName, dateOfBirth, email, password);
        try{
            registerFailure = null;
            controller.registerUser(user);
        }catch(Exception e){
            if(e.getMessage().equalsIgnoreCase("Transaction aborted")){
                return "fail";
            } else {
                return "error";
            }
        }
        return "success";
    }    
    
    
    /**
     *
     * @return false if register unsuccessful
     */
    public boolean getSuccess(){
        return registerFailure == null;
    }    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return null;
    }


    public void setPassword(String password) {
        this.password = password;
    }



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
