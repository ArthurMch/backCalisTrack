package back.SportApp.Training;

import java.util.List;

public interface TrainingService {
    Training create(Training training);
    List<Training> findAll();
    Training findById(Long id);
    Training update(Training training);
    public void deleteById(Long id);
}
