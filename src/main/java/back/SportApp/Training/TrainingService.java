package back.SportApp.Training;

import java.util.List;

public interface TrainingService {
    Training creer(Training training);
    List<Training> lire();
    Training lireById(Long id);
    Training modifier(Integer id, Training training);
    public void supprimer(Integer id);
}
