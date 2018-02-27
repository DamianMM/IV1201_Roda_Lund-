
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Persistence representation of a user
 * 
 * @author mikaelnorberg
 */
@NamedQueries({
        @NamedQuery(
            name = "findUserByEmail",
            query = "SELECT usr FROM Person usr WHERE usr.email LIKE :searchedEmail"
    )
})

@Entity(name="Person")
public class User implements UserDTO, Serializable {
    
    @Id
    @Pattern(regexp = "[a-z0-9]+@[a-z0-9]+\\.[a-z0-9]+")
    private String email;
    
    @Size(min=1, max=100)
    private String first_name;
    
    
    @Size(min=1, max=100)
    private String last_name;
    
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date_of_birth;
    
    @Size(min=3, max=100)
    private String password;
    
    @ManyToOne(optional=false)
    @JoinColumn(name = "ACTOR", referencedColumnName="ACTOR_NAME") 
    private Actor actor;

    /**
     * creates a new instance of a user object
     */
    public User() {
    }

    /**
     * creates a new instance of a user object
     * 
     * @param userInfo information about the current user
     * @param actor specifies the users role
     */
    public User(UserInfoDTO userInfo, Actor actor){
        first_name = userInfo.getFirst_name();
        last_name = userInfo.getLast_name();
        date_of_birth = userInfo.getDate_of_birth();
        email = userInfo.getEmail();
        password = userInfo.getPassword();
        this.actor = actor;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getFirst_name() {
        return first_name;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getLast_name() {
        return last_name;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getEmail() {
        return email;
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return this.email;
    }
}