package back.SportApp.Training;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Exercise.Exercise;
import back.SportApp.Training.DTO.TrainingDTO;

import java.util.List;
import java.util.Set;

public interface TrainingService {
    void create(TrainingDTO training, Set<ExerciseDTO> exercises);
    List<TrainingDTO> findAll();
    Set<TrainingDTO> findAllByUserId(Integer userId);
    TrainingDTO findById(Integer id);
    Training update(Training training);
    void deleteById(Integer id);
    Boolean existById(Integer id);
}
