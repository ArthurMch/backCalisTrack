package back.SportApp.Training.DTO;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Training.Training;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainingDTO {
    private Integer id;
    private Date date;
    private Integer numberOfExercise;
    private Set<ExerciseDTO> exercises;

    public TrainingDTO(Training training) {
        this.id = training.getId();
        this.date = training.getDate();
        this.numberOfExercise = training.getNumberOfExercise();
        this.exercises = training.getExercises().stream()
                .map(ExerciseDTO::new)  // Conversion des entités en DTOs
                .collect(Collectors.toSet());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

}


