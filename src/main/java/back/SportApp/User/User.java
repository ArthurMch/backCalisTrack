package back.SportApp.User;

import back.SportApp.Training.Training;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_firstname")
    private String firstname;

    @Column(name = "user_lastname")
    private String lastname;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "trainingUser", cascade = CascadeType.ALL)
    private Set<Training> trainings;

    public User(){
    };

    public Long getId(){
        return id;
    }

    public void setId(Long accountId){
        this.id = accountId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String name){
        this.firstname = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Set<Training> getTrainings(){
        return this.trainings;
    }
}

// LISTENER ON PORT 5432 ou 5433

