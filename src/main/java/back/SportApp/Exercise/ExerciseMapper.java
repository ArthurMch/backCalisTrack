package back.SportApp.Exercise;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.User.UserService;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    final UserService userService;

    public ExerciseMapper(UserService userService) {
        this.userService = userService;
    }

    public ExerciseDTO toDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setSet(exercise.getSet());
        dto.setRep(exercise.getRep());
        dto.setRestTimeInMinutes(exercise.getRestTimeInMinutes());
        dto.setUser(exercise.getUser().getId());
        return dto;
    }

    public Exercise toEntity(ExerciseDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setSet(dto.getSet());
        exercise.setRep(dto.getRep());
        exercise.setRestTimeInMinutes(dto.getRestTimeInMinutes());
        exercise.setUser(userService.findById(dto.getUser()));
        return exercise;
    }
}

