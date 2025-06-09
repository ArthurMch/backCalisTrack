package back.SportApp.ExerciseTests;

import back.SportApp.Exercise.*;
import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingService;
import back.SportApp.TrainingExercise.TrainingExerciseService;
import back.SportApp.User.User;
import back.SportApp.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExerciseControllerTest {

    @Mock
    private ExerciseService exerciseService;

    @Mock
    private TrainingService trainingService;

    @Mock
    private TrainingExerciseService trainingExerciseService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ExerciseController exerciseController;

    private Exercise exercise;
    private ExerciseDTO exerciseDTO;
    private User user;
    private Training training;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setFirstname("testUser");

        exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("Push-up");
        exercise.setUser(user);

        exerciseDTO = new ExerciseDTO(exercise);

        training = new Training();
        training.setId(1);
        training.setUser(user);
    }

    // Tests pour create()
    @Test
    void testCreateExercise_Success() {
        when(exerciseService.create(any(ExerciseDTO.class))).thenReturn(exercise);

        ResponseEntity<String> response = exerciseController.create(exerciseDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Exercise created", response.getBody());
        verify(exerciseService).create(exerciseDTO);
    }

    @Test
    void testCreateExercise_Failure() {
        when(exerciseService.create(any(ExerciseDTO.class))).thenThrow(new RuntimeException("Creation failed"));

        ResponseEntity<String> response = exerciseController.create(exerciseDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Exercise not created", response.getBody());
    }

    // Tests pour findAll()
    @Test
    void testFindAllExercises_Success() {
        List<Exercise> exercises = Arrays.asList(exercise);
        when(exerciseService.findAll()).thenReturn(exercises);

        ResponseEntity<List<ExerciseDTO>> response = exerciseController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Push-up", response.getBody().get(0).getName());
    }

    @Test
    void testFindAllExercises_NoContent() {
        when(exerciseService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ExerciseDTO>> response = exerciseController.findAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFindAllExercises_Exception() {
        when(exerciseService.findAll()).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<List<ExerciseDTO>> response = exerciseController.findAll();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Tests pour findByUserId()
    @Test
    void testFindByUserId_Success() {
        Integer userId = 1;
        Set<ExerciseDTO> exercises = Set.of(exerciseDTO);
        when(exerciseService.findAllByUserId(userId)).thenReturn(exercises);

        ResponseEntity<Set<ExerciseDTO>> response = exerciseController.findByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testFindByUserId_NoContent() {
        Integer userId = 1;
        when(exerciseService.findAllByUserId(userId)).thenReturn(Collections.emptySet());

        ResponseEntity<Set<ExerciseDTO>> response = exerciseController.findByUserId(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testFindByUserId_Exception() {
        Integer userId = 1;
        when(exerciseService.findAllByUserId(userId)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<Set<ExerciseDTO>> response = exerciseController.findByUserId(userId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Tests pour getById()
    @Test
    void testGetExerciseById_Success() {
        when(exerciseService.findById(1)).thenReturn(exercise);

        ResponseEntity<Exercise> response = exerciseController.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals("Push-up", response.getBody().getName());
    }

    @Test
    void testGetExerciseById_NotFound() {
        when(exerciseService.findById(999)).thenReturn(null);

        ResponseEntity<Exercise> response = exerciseController.getById(999);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetExerciseById_Exception() {
        when(exerciseService.findById(1)).thenThrow(new RuntimeException("Not found"));

        ResponseEntity<Exercise> response = exerciseController.getById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Tests pour update()
    @Test
    void testUpdateExercise_Success() {
        doNothing().when(exerciseService).update(any(Exercise.class));

        ResponseEntity<String> response = exerciseController.update(exercise);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise updated", response.getBody());
        verify(exerciseService).update(exercise);
    }

    @Test
    void testUpdateExercise_NotFound() {
        doThrow(new RuntimeException("Exercise not found")).when(exerciseService).update(any(Exercise.class));

        ResponseEntity<String> response = exerciseController.update(exercise);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Exercise not updated", response.getBody());
    }

    // Tests pour delete()
    @Test
    void testDeleteExercise_Success() {
        Integer exerciseId = 3; // Utiliser un ID différent
        doNothing().when(exerciseService).deleteById(exerciseId);

        ResponseEntity<String> response = exerciseController.delete(exerciseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise deleted successfully", response.getBody());
        verify(exerciseService).deleteById(exerciseId);
    }

    @Test
    void testDeleteExercise_Conflict() {
        Integer exerciseId = 4;
        doThrow(new RuntimeException("Cannot delete exercise: it is used in training"))
                .when(exerciseService).deleteById(exerciseId);

        ResponseEntity<String> response = exerciseController.delete(exerciseId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody().contains("Cannot delete exercise: it is used"));
    }

    @Test
    void testDeleteExercise_NotFound() {
        Integer exerciseId = 999;
        doThrow(new RuntimeException("Wrong or inexistant ID"))
                .when(exerciseService).deleteById(exerciseId);

        ResponseEntity<String> response = exerciseController.delete(exerciseId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("Wrong or inexistant ID"));
    }

    @Test
    void testDeleteExercise_InternalServerError() {
        Integer exerciseId = 5;
        doThrow(new RuntimeException("Database connection failed"))
                .when(exerciseService).deleteById(exerciseId);

        ResponseEntity<String> response = exerciseController.delete(exerciseId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erreur interne du serveur", response.getBody());
    }
}