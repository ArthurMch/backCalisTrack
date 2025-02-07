package back.SportApp.Training;

import java.util.List;

public interface TrainingService {
    Training create(Training training);
    List<Training> findAll();
    Training findById(Integer id);
    Training update(Training training);
    void deleteById(Integer id);
    Boolean existById(Integer id);
}
