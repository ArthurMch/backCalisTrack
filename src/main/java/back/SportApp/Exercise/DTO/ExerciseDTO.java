package back.SportApp.Exercise.DTO;

import back.SportApp.Exercise.Exercise;
import back.SportApp.User.User;

public class ExerciseDTO {
    private Integer id;
    private String name;
    private Integer set;
    private Integer rep;
    private Integer restTimeInMinutes;
    private Integer user;

    public ExerciseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.set = exercise.getSet();
        this.rep = exercise.getRep();
        this.restTimeInMinutes = exercise.getRestTimeInMinutes();
        this.user = exercise.getUser().getId();
    }

    public ExerciseDTO() {}

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
