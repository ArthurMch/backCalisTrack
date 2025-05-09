package back.SportApp.Training.DTO;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Training.Training;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainingDTO {
    private Integer id;
    private String name;
    private Date date;
    private Integer numberOfExercise;
    private Integer totalMinutesOfRest;
    private Integer totalMinutesOfTraining;
    private Integer trainingUser;
    private Set<ExerciseDTO> exercises;


    // Dois je passer un trainingExercise dans la signature ?
    public TrainingDTO(Training training, Set<ExerciseDTO> exercises) {
        this.id = training.getId();
        this.name = training.getName();
        this.date = training.getDate();
        this.numberOfExercise = exercises.size();
        this.totalMinutesOfRest = training.getTotalMinutesOfRest();
        this.totalMinutesOfTraining = training.getTotalMinutesOfTraining();
        this.trainingUser = training.getTrainingUser().getId();
        this.exercises = exercises;
    }

    public TrainingDTO() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTrainingUser() {
        return trainingUser;
    }

    public void setTrainingUser(Integer trainingUser) {
        this.trainingUser = trainingUser;
    }

}


