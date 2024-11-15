package back.SportApp.TrainingExercise;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrainingExerciseId implements Serializable {

    private Long trainingId;
    private Long exerciseId;

    public TrainingExerciseId() {}

    public TrainingExerciseId(Long trainingId, Long exerciseId) {
        this.trainingId = trainingId;
        this.exerciseId = exerciseId;
    }
    public Long getTrainingId() {
        return trainingId;
    }
    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }
    public Long getExerciseId() {
        return exerciseId;
    }
    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingExerciseId that = (TrainingExerciseId) o;
        return Objects.equals(trainingId, that.trainingId) &&
                Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, exerciseId);
    }
}
