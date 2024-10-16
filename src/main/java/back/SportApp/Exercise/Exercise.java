package back.SportApp.Exercise;

import back.SportApp.Training.Training;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue
    @Column(name = "exercise_id")
    private Long id;

    @Column(name="exercise_name")
    private String name;

    @Column(name="exercise_set")
    private Integer set;

    @Column(name="exercise_rep")
    private Integer rep;

    @Column(name = "exercise_rest_time_in_minutes")
    private Integer restTimeInMinutes;

    @ManyToOne
    @JoinColumn(name="training_id", nullable = false)
    private Training training;

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long exerciseId) {
        this.id = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    public Integer getRep() {
        return rep;
    }

    public void setRep(Integer rep) {
        this.rep = rep;
    }

    public Integer getRestTimeInMinutes() {
        return restTimeInMinutes;
    }

    public void setRestTimeInMinutes(Integer restTimeInMinutes) {
        this.restTimeInMinutes = restTimeInMinutes;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
