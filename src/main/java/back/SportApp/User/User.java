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
    private Integer id;

    @Column(name = "user_firstname")
    private String firstname;

    @Column(name = "user_lastname")
    private String lastname;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "trainingUser", cascade = CascadeType.ALL)
    private Set<Training> trainings;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    public User(){
    };

    public Integer getId(){
        return id;
    }

    public void setId(Integer accountId){
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

// LISTENER ON PORT 5432 ou 5433

