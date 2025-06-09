package back.SportApp.ExerciseTests;

import back.SportApp.Exercise.*;
import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.TrainingExercise.TrainingExercise;
import back.SportApp.TrainingExercise.TrainingExerciseRepository;
import back.SportApp.Training.TrainingRepository;
import back.SportApp.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExerciseServiceImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private TrainingExerciseRepository trainingExerciseRepository;

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private ExerciseMapper exerciseMapper;

    private ExerciseServiceImpl exerciseService;

    private Exercise exercise;
    private ExerciseDTO exerciseDTO;
    private User user;

    @BeforeEach
    void setUp() {
        // Création du service avec injection par constructeur
        exerciseService = new ExerciseServiceImpl(
                exerciseMapper,
                trainingExerciseRepository,
                trainingRepository
        );

        // Injection du repository restant avec réflection
        try {
            Field exerciseRepositoryField = ExerciseServiceImpl.class.getDeclaredField("exerciseRepository");
            exerciseRepositoryField.setAccessible(true);
            exerciseRepositoryField.set(exerciseService, exerciseRepository);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'injection du repository", e);
        }

        user = new User();
        user.setId(1);
        user.setFirstname("testUser");

        exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("Push-ups");
        exercise.setSet(3);
        exercise.setRep(15);
        exercise.setRestTimeInMinutes(2);
        exercise.setUser(user);

        exerciseDTO = new ExerciseDTO();
        exerciseDTO.setId(1);
        exerciseDTO.setName("Push-ups");
        exerciseDTO.setSet(3);
        exerciseDTO.setRep(15);
        exerciseDTO.setRestTimeInMinutes(2);
    }

    @Test
    void testExistsById_True() {
        // Given
        when(exerciseRepository.existsById(1)).thenReturn(true);

        // When
        boolean result = exerciseService.existsById(1);

        // Then
        assertTrue(result);
        verify(exerciseRepository).existsById(1);
    }

    @Test
    void testExistsById_False() {
        // Given
        when(exerciseRepository.existsById(1)).thenReturn(false);

        // When
        boolean result = exerciseService.existsById(1);

        // Then
        assertFalse(result);
        verify(exerciseRepository).existsById(1);
    }

    @Test
    void testFindAll_Success() {
        // Given
        List<Exercise> exercises = Arrays.asList(exercise);
        when(exerciseRepository.findAll()).thenReturn(exercises);

        // When
        List<Exercise> result = exerciseService.findAll();

        // Then
        assertEquals(1, result.size());
        assertEquals(exercise, result.get(0));
        verify(exerciseRepository).findAll();
    }

    @Test
    void testFindAll_EmptyList() {
        // Given
        when(exerciseRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Exercise> result = exerciseService.findAll();

        // Then
        assertTrue(result.isEmpty());
        verify(exerciseRepository).findAll();
    }

    @Test
    void testFindAll_RepositoryException() {
        // Given
        when(exerciseRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.findAll();
        });
        assertEquals("Database error", exception.getMessage());
        verify(exerciseRepository).findAll();
    }

    @Test
    void testFindAllByUserId_Success() {
        // Given
        Set<Exercise> exercises = new HashSet<>(Arrays.asList(exercise));
        when(exerciseRepository.findAllByUserId(1)).thenReturn(exercises);
        when(exerciseMapper.toDTO(exercise)).thenReturn(exerciseDTO);

        // When
        Set<ExerciseDTO> result = exerciseService.findAllByUserId(1);

        // Then
        assertEquals(1, result.size());
        assertTrue(result.contains(exerciseDTO));
        verify(exerciseRepository).findAllByUserId(1);
        verify(exerciseMapper).toDTO(exercise);
    }

    @Test
    void testFindAllByUserId_EmptySet() {
        // Given
        when(exerciseRepository.findAllByUserId(1)).thenReturn(new HashSet<>());

        // When
        Set<ExerciseDTO> result = exerciseService.findAllByUserId(1);

        // Then
        assertTrue(result.isEmpty());
        verify(exerciseRepository).findAllByUserId(1);
    }

    @Test
    void testFindAllByUserId_RepositoryException() {
        // Given
        when(exerciseRepository.findAllByUserId(1)).thenThrow(new RuntimeException("Database error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.findAllByUserId(1);
        });
        assertEquals("Database error", exception.getMessage());
        verify(exerciseRepository).findAllByUserId(1);
    }

    @Test
    void testCreate_Success() {
        // Given
        when(exerciseMapper.toEntity(exerciseDTO)).thenReturn(exercise);
        when(exerciseRepository.save(exercise)).thenReturn(exercise);

        // When
        Exercise result = exerciseService.create(exerciseDTO);

        // Then
        assertEquals(exercise, result);
        verify(exerciseMapper).toEntity(exerciseDTO);
        verify(exerciseRepository).save(exercise);
    }

    @Test
    void testCreate_RepositoryException() {
        // Given
        when(exerciseMapper.toEntity(exerciseDTO)).thenReturn(exercise);
        when(exerciseRepository.save(exercise)).thenThrow(new RuntimeException("Creation failed"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.create(exerciseDTO);
        });
        assertEquals("Creation failed", exception.getMessage());
        verify(exerciseMapper).toEntity(exerciseDTO);
        verify(exerciseRepository).save(exercise);
    }

    @Test
    void testFindById_Success() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));

        // When
        Exercise result = exerciseService.findById(1);

        // Then
        assertEquals(exercise, result);
        verify(exerciseRepository).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.findById(1);
        });
        assertEquals("Exercise not found with id 1", exception.getMessage());
        verify(exerciseRepository).findById(1);
    }

    @Test
    void testFindById_RepositoryException() {
        // Given
        when(exerciseRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.findById(1);
        });
        assertEquals("Database error", exception.getMessage());
        verify(exerciseRepository).findById(1);
    }

    @Test
    void testUpdate_Success() {
        // Given
        Exercise existingExercise = new Exercise();
        existingExercise.setId(1);
        existingExercise.setName("Old name");
        existingExercise.setRep(10);
        existingExercise.setSet(2);
        existingExercise.setRestTimeInMinutes(1);

        when(exerciseRepository.findById(1)).thenReturn(Optional.of(existingExercise));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(existingExercise);

        // When
        exerciseService.update(exercise);

        // Then
        verify(exerciseRepository).findById(1);
        verify(exerciseRepository).save(any(Exercise.class));
        // Vérifier que les valeurs ont été mises à jour
        assertEquals("Push-ups", existingExercise.getName());
        assertEquals(15, existingExercise.getRep());
        assertEquals(3, existingExercise.getSet());
        assertEquals(2, existingExercise.getRestTimeInMinutes());
    }

    @Test
    void testUpdate_ExerciseNotFound() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.update(exercise);
        });
        assertEquals("Wrong or inexistant ID1", exception.getMessage());
        verify(exerciseRepository).findById(1);
        verify(exerciseRepository, never()).save(any());
    }

    @Test
    void testDeleteById_Success() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));
        when(trainingExerciseRepository.findByExerciseId(1)).thenReturn(Collections.emptyList());

        // When
        assertDoesNotThrow(() -> exerciseService.deleteById(1));

        // Then
        verify(exerciseRepository).findById(1);
        verify(trainingExerciseRepository).findByExerciseId(1);
        verify(exerciseRepository).deleteById(1);
    }

    @Test
    void testDeleteById_ExerciseNotFound() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.deleteById(1);
        });
        assertEquals("Exercise not found with id 1", exception.getMessage());
        verify(exerciseRepository).findById(1);
        verify(trainingExerciseRepository, never()).findByExerciseId(any());
        verify(exerciseRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteById_ExerciseUsedInTraining() {
        // Given
        TrainingExercise trainingExercise1 = new TrainingExercise();
        TrainingExercise trainingExercise2 = new TrainingExercise();
        List<TrainingExercise> associations = Arrays.asList(trainingExercise1, trainingExercise2);

        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));
        when(trainingExerciseRepository.findByExerciseId(1)).thenReturn(associations);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.deleteById(1);
        });
        assertEquals("Cannot delete exercise: it is used in 2 training(s). Please remove it from trainings first.", exception.getMessage());
        verify(exerciseRepository).findById(1);
        verify(trainingExerciseRepository).findByExerciseId(1);
        verify(exerciseRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteById_SingleTrainingAssociation() {
        // Given
        TrainingExercise trainingExercise = new TrainingExercise();
        List<TrainingExercise> associations = Arrays.asList(trainingExercise);

        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));
        when(trainingExerciseRepository.findByExerciseId(1)).thenReturn(associations);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.deleteById(1);
        });
        assertEquals("Cannot delete exercise: it is used in 1 training(s). Please remove it from trainings first.", exception.getMessage());
        verify(exerciseRepository).findById(1);
        verify(trainingExerciseRepository).findByExerciseId(1);
        verify(exerciseRepository, never()).deleteById(any());
    }

    @Test
    void testDeleteById_TrainingExerciseRepositoryException() {
        // Given
        when(exerciseRepository.findById(1)).thenReturn(Optional.of(exercise));
        when(trainingExerciseRepository.findByExerciseId(1)).thenThrow(new RuntimeException("Database error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            exerciseService.deleteById(1);
        });
        assertEquals("Database error", exception.getMessage());
        verify(exerciseRepository).findById(1);
        verify(trainingExerciseRepository).findByExerciseId(1);
        verify(exerciseRepository, never()).deleteById(any());
    }
}