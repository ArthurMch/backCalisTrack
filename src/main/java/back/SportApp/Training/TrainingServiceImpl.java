package back.SportApp.Training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Training creer(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> lire() {
        return trainingRepository.findAll();
    }

    @Override
    public Training lireById(Long id) {
        Optional<Training> training = trainingRepository.findByTrainingId(id);
        if (training.isPresent()) {
            return training.get();
        }else{
            throw new RuntimeException("Wrong id" + id);
        }
    }

    @Override
    public Training modifier(Integer id, Training training) {
        Optional<Training> existingTraining = trainingRepository.findById(id);
        if (existingTraining.isPresent()) {
            Training updateTraining = existingTraining.get();
            updateTraining.setDate(training.getDate());
            updateTraining.setTotalMinutesOfTraining(training.getTotalMinutesOfTraining());
            updateTraining.setTotalMinutesOfRest(training.getTotalMinutesOfRest());
            updateTraining.setNumberOfExercise(training.getNumberOfExercise());
            return trainingRepository.save(updateTraining);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + id);
        }
    }

    @Override
    public void supprimer(Integer id) {
        trainingRepository.deleteById(id);
    }
}
