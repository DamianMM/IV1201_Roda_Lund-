
package se.kth.id1212.mynewjavaeeapp.model;

import java.util.Date;

/**
 * Used to transfer info about user.
 * @author mikaelnorberg
 */
public interface UserDTO {

    public String getFirst_name();
    public String getLast_name();
    public Date getDate_of_birth();
    public String getEmail();
    @Override
    public String toString();
    
}