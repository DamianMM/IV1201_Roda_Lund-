
package se.kth.id1212.mynewjavaeeapp.model;

import java.util.Date;

public class UserInfoDTO {
    private int person_id;
    private String first_name;
    private String last_name;
    private Date date_of_birth;
    private String email;
    private String password;
    
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
    
    
    /**
     * @return the person_id
     */
    public int getPerson_id() {
        return person_id;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @return the date_of_birth
     */
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}