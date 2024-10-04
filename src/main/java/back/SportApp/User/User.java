package back.SportApp.User;

import back.SportApp.Training.Training;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "user")
    @Column(name = "user_trainings")
    private Set<Training> training;

    public User(){
    };

    public User(Long accountId, String name, String email, String password, Set<Training> training){
        this.id = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.training = training;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long accountId){
        this.id = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public Set<Training> getTraining(){
        return this.training;
    }
}

// LISTENER ON PORT 5432 ou 5433

