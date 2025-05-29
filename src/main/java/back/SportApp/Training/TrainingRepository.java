package back.SportApp.Training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    @Query("SELECT t FROM Training t WHERE t.user.id = :userId")
    Set<Training> findByUserId(@Param("userId") Integer userId);
}
