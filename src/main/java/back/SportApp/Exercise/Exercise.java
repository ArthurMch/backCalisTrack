package back.SportApp.Exercise;

import back.SportApp.Training.Training;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Integer id;

    @Column(name="exercise_name")
    private String name;

    @Column(name="exercise_set")
    private Integer set;

    @Column(name="exercise_rep")
    private Integer rep;

    @Column(name = "exercise_rest_time_in_minutes")
    private Integer restTimeInMinutes;

    @ManyToMany(mappedBy = "exercises")
    @JsonBackReference
    private Set<Training> trainings = new HashSet<>();

    public Exercise() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer exerciseId) {
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

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }
}
