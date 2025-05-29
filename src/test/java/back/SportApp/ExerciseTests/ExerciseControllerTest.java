package back.SportApp.ExerciseTests;

import back.SportApp.Exercise.*;
import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Training.TrainingService;
import back.SportApp.TrainingExercise.TrainingExerciseService;
import back.SportApp.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    private ExerciseMapper exerciseMapper;

    @BeforeEach
    void setUp() {
        exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("Push-up");
        exerciseDTO  = new ExerciseDTO(exercise);
    }

    @Test
    void testCreateExercise() {
        when(exerciseService.create(any(ExerciseDTO.class))).thenReturn(exercise);
        ResponseEntity<String> response = exerciseController.create(exerciseDTO);
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Exercise created", response.getBody());
    }

    @Test
    void testFindAllExercises() {
        when(exerciseService.findAll()).thenReturn(Collections.singletonList(exercise));
        ResponseEntity<List<ExerciseDTO>> response = exerciseController.findAll();
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testFindExerciseById() {
        when(exerciseService.findById(1)).thenReturn(exercise);
        ResponseEntity<Exercise> response = exerciseController.getById(1);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testUpdateExercise() {
        doNothing().when(exerciseService).update(any(Exercise.class));
        ResponseEntity<String> response = exerciseController.update(exercise);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Exercise updated", response.getBody());
    }

    @Test
    void testDeleteExercise() {
        doNothing().when(exerciseService).deleteById(1);
        ResponseEntity<String> response = exerciseController.delete(1);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Exercise deleted successfully", response.getBody());
    }
}
