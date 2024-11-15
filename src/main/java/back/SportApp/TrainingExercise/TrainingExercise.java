package back.SportApp.TrainingExercise;


import back.SportApp.Exercise.Exercise;
import back.SportApp.Training.Training;
import jakarta.persistence.*;

@Entity
@Table(name ="training_exercise")
public class TrainingExercise {

    @EmbeddedId
    private TrainingExerciseId id;

    @ManyToOne
    @MapsId("trainingId")
    private Training training;

    @ManyToOne
    @MapsId("exerciseId")
    private Exercise exercise;

    public TrainingExercise(){}

    public TrainingExercise(Training training, Exercise exercise){
        this.training = training;
        this.exercise = exercise;
        this.id = new TrainingExerciseId(training.getId(), exercise.getId());
    }

    public TrainingExerciseId getId() {
        return id;
    }
    public void setId(TrainingExerciseId id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }
    public void setTraining(Training training) {
        this.training = training;
    }

    public Exercise getExercise() {
        return exercise;
    }
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

}
