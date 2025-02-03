package back.SportApp.Training;

import back.SportApp.Exercise.Exercise;
import back.SportApp.Exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public Training create(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Training findById(Integer id) {
        Optional<Training> training = trainingRepository.findById(id);
        if (training.isPresent()) {
            return training.get();
        }else{
            throw new RuntimeException("Wrong id" + id);
        }
    }

    @Override
    public Training update(Training training) {
        Optional<Training> existingTraining = trainingRepository.findById(training.getId());
        if (existingTraining.isPresent()) {
            Training updateTraining = existingTraining.get();
            updateTraining.setDate(training.getDate());
            updateTraining.setTotalMinutesOfTraining(training.getTotalMinutesOfTraining());
            updateTraining.setTotalMinutesOfRest(training.getTotalMinutesOfRest());
            updateTraining.setNumberOfExercise(training.getNumberOfExercise());
            return trainingRepository.save(updateTraining);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + training.getId());
        }
    }

    @Override
    public void deleteById(Integer id) {
        trainingRepository.deleteById(id);
    }
}
