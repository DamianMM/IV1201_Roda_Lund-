
package se.kth.id1212.mynewjavaeeapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(
            name = "findUserByEmail",
            query = "SELECT usr FROM Person usr WHERE usr.email LIKE :searchedEmail"
            //lockMode = LockModeType.OPTIMISTIC
    )
})

@Entity(name="Person")
public class User implements UserDTO, Serializable {
    
    
    //@Id
    //@SequenceGenerator(name="PERSON_SEQUENCE_GENERATOR", sequenceName="PERSON_PRIMARY_KEY_SEQUENCE",initialValue=1, allocationSize=1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSON_SEQUENCE_GENERATOR")
    //private int person_id;
    
    @Id
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
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
    
    
    private String actor;



    
    
    public User() {
    }

    /*
    public User(String firstName, String lastName, Date dateOfBirth, String email, String password) {
        first_name = firstName;
        last_name = lastName;
        //Calendar tempDate = Calendar.getInstance();
        //tempDate.set(1999, 1, 1);
        System.out.println(dateOfBirth);
        date_of_birth = dateOfBirth;//tempDate.getTime();
        this.email = email;
        this.password = password;
        actor_id = 1;
    }
    */
    public User(UserInfoDTO userInfo){
        first_name = userInfo.getFirst_name();
        last_name = userInfo.getLast_name();
        date_of_birth = userInfo.getDate_of_birth();
        email = userInfo.getEmail();
        password = userInfo.getPassword();
        actor = "applicant";
    }





   /* @Override
    public int hashCode() {
        int hash = 0;
        return new Integer(person_id).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return this.person_id == other.person_id;
    }

    @Override
    public int getPerson_id() {
        return person_id;
    } */

    @Override
    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String getLast_name() {
        return last_name;
    }

    @Override
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    @Override
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString(){
        return this.email;
    }

}