package back.SportApp.TrainingExercise;

import back.SportApp.Exercise.models.Exercise;
import back.SportApp.Exercise.repository.ExerciseRepository;
import back.SportApp.Training.models.Training;
import back.SportApp.Training.repository.TrainingRepository;
import back.SportApp.TrainingExercise.repository.TrainingExerciseRepository;
import back.SportApp.TrainingExercise.services.TrainingExerciseServiceImpl;
import back.SportApp.User.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TrainingExerciseServiceImplTest {

    @Mock
    private TrainingExerciseRepository trainingExerciseRepository;
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private UserRepository userRepository;

    private TrainingExerciseServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        service = new TrainingExerciseServiceImpl();
        Field f;
        f = TrainingExerciseServiceImpl.class.getDeclaredField(
                "trainingExerciseRepository");
        f.setAccessible(true);
        f.set(service, trainingExerciseRepository);
        f = TrainingExerciseServiceImpl.class.getDeclaredField(
                "trainingRepository");
        f.setAccessible(true);
        f.set(service, trainingRepository);
        f = TrainingExerciseServiceImpl.class.getDeclaredField(
                "exerciseRepository");
        f.setAccessible(true);
        f.set(service, exerciseRepository);
        f = TrainingExerciseServiceImpl.class.getDeclaredField(
                "userRepository");
        f.setAccessible(true);
        f.set(service, userRepository);
    }

    @Test
    void deleteExerciseTraining_success() {
        Training training = new Training();
        training.setId(1);
        Exercise exercise = new Exercise();
        exercise.setId(2);

        when(trainingRepository.findById(1)).thenReturn(Optional.of(training));
        when(exerciseRepository.findById(2)).thenReturn(Optional.of(exercise));

        assertDoesNotThrow(() -> service.deleteExerciseTraining(1, 2));

        verify(trainingRepository).findById(1);
        verify(exerciseRepository).findById(2);
        verify(trainingExerciseRepository).deleteByTraining(training);
        verify(trainingRepository, never()).delete(any());
    }

    @Test
    void deleteExerciseTraining_trainingNotFound() {
        when(trainingRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> service.deleteExerciseTraining(1, 2));
        assertEquals("Training not found", e.getMessage());
        verify(trainingExerciseRepository, never()).deleteByTrainingIdAndExerciseId(anyInt(), anyInt());
    }

    @Test
    void deleteExerciseTraining_exerciseNotFound() {
        Training training = new Training();
        training.setId(1);
        when(trainingRepository.findById(1)).thenReturn(Optional.of(training));
        when(exerciseRepository.findById(2)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> service.deleteExerciseTraining(1, 2));
        assertEquals("Exercise not found", e.getMessage());
        verify(trainingExerciseRepository, never()).deleteByTrainingIdAndExerciseId(anyInt(), anyInt());
    }
}
