package back.SportApp.Training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Optional<Training> findById(Long id);

    void deleteById(Long id);
}
