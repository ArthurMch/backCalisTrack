package back.SportApp.Training;


import back.SportApp.Exercise.Exercise;
import back.SportApp.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="training_id")
    private Integer id;

    @Column(name = "training_name")
    private String name;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="training_date")
    private Date date;

    @Column(name="training_number_of_exercise")
    private Integer numberOfExercise;

    @Column(name = "training_total_minutes_of_rest")
    private Integer totalMinutesOfRest;

    @Column(name = "training_total_minutes_of_training")
    private Integer totalMinutesOfTraining;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User trainingUser;

    public Training(){
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer trainingId) {
        this.id = trainingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfExercise() {
        return numberOfExercise;
    }

    public void setNumberOfExercise(Integer numberOfExercise) {
        this.numberOfExercise = numberOfExercise;
    }

    public Integer getTotalMinutesOfRest() {
        return totalMinutesOfRest;
    }

    public void setTotalMinutesOfRest(Integer totalMinutesOfRest) {
        this.totalMinutesOfRest = totalMinutesOfRest;
    }

    public Integer getTotalMinutesOfTraining() {
        return totalMinutesOfTraining;
    }

    public void setTotalMinutesOfTraining(Integer totalMinutesOfTraining) {
        this.totalMinutesOfTraining = totalMinutesOfTraining;
    }

    public User getTrainingUser() {
        return trainingUser;
    }

    public void setTrainingUser(User trainingUser) {
        this.trainingUser = trainingUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
