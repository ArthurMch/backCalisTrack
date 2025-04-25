package back.SportApp.User;

import back.SportApp.Training.Training;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_firstname")
    private String firstname;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_lastname")
    private String lastname;

    @Column(name = "user_email", unique = true)
    private String email;

    @NotBlank
    @Size(max = 15)
    @Column(name = "user_phone")
    private String phone;

    @Size(max = 120)
    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "trainingUser", cascade = CascadeType.ALL)
    private Set<Training> trainings;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    @Column(name = "user_expiration")
    private Date expiration;

    @Column(name = "user_password_expiration")
    private Date passwordExpiration;

    @Column(name = "user_lost_password_token")
    private String lostPasswordToken;

    @Column(name = "user_lost_password_expiration")
    private Date lostPasswordExpiration;

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

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Date getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(Date passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    public String getLostPasswordToken() {
        return lostPasswordToken;
    }

    public void setLostPasswordToken(String lostPasswordToken) {
        this.lostPasswordToken = lostPasswordToken;
    }

    public Date getLostPasswordExpiration() {
        return lostPasswordExpiration;
    }

    public void setLostPasswordExpiration(Date lostPasswordExpiration) {
        this.lostPasswordExpiration = lostPasswordExpiration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

// LISTENER ON PORT 5432 ou 5433

