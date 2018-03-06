
package se.kth.id1212.mynewjavaeeapp.model;

import java.util.Date;

/**
 * Used to transfer information about user to be created. 
 * @author mikaelnorberg
 */
public class UserInfoDTO {
    private final String first_name;
    private final String last_name;
    private final Date date_of_birth;
    private final String email;
    private final String password;
    
    /**
     *
     * @param first_name
     * @param last_name
     * @param date_of_birth
     * @param email
     * @param password
     */
    public UserInfoDTO(
    String first_name,
    String last_name,
    Date date_of_birth,
    String email,
    String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.password = password;
    }


    public String getFirst_name() {
        return first_name;
    }


    public String getLast_name() {
        return last_name;
    }


    public Date getDate_of_birth() {
        return date_of_birth;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }
}