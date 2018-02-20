
package se.kth.id1212.mynewjavaeeapp.model;

import java.util.Date;

public interface UserDTO {
    
    /**
     * @return the person_id
     */
   // public int getPerson_id(); 

    /**
     * @return the first_name
     */
    public String getFirst_name();

    /**
     * @return the last_name
     */
    public String getLast_name();

    /**
     * @return the date_of_birth
     */
    public Date getDate_of_birth();

    /**
     * @return the email
     */
    public String getEmail();
    
    public String toString();
    
}