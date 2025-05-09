package back.SportApp.Training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    Set<Training> findByTrainingUserId(Integer userId);
}
