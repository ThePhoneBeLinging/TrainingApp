import Group15.Model.*;
import Group15.Util.JSONParser;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutSaveTest {

    @Test
    public void testSaveWorkout() {
        Workout workout = new Workout();
        WorkoutExercise exercise = new WorkoutExercise();
        Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        exercise.setExercise(pushup);
        workout.addExercise(exercise);
        workout.saveWorkout();

        List<Workout> savedWorkouts = Workout.getSavedWorkouts();
        assertNotNull(savedWorkouts);
        assertTrue(savedWorkouts.size() > 0);
        Workout savedWorkout = savedWorkouts.get(savedWorkouts.size() - 1);
        assertEquals(workout, savedWorkout);

        List<WorkoutExercise> savedExercises = savedWorkout.getExercises();
        assertNotNull(savedExercises);
        assertTrue(savedExercises.size() > 0);
        assertEquals(exercise, savedExercises.get(0));
        assertEquals(pushup, savedExercises.get(0).getExercise());
    }
}