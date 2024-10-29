
import Group15.Api.Exercise;
import Group15.WorkoutAlgorithm;
import Group15.Workout;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutAlgorithmTest {

    private WorkoutAlgorithm workoutAlgorithm;
    private List<Exercise> mockExercises;

    @Before
    public void setUp() {
        workoutAlgorithm = new WorkoutAlgorithm();
        mockExercises = new ArrayList<>();

        Exercise pushUp = new Exercise();
        pushUp.title = "Push Up";
        pushUp.description = "Push-up exercise";
        pushUp.bodyPart = "chest";
        pushUp.equipment = "bodyweight";
        pushUp.difficulty = "beginner";
        pushUp.timePerRep = 2000;

        Exercise benchPress = new Exercise();
        benchPress.title = "Bench Press";
        benchPress.description = "Bench press exercise";
        benchPress.bodyPart = "chest";
        benchPress.equipment = "barbell";
        benchPress.difficulty = "intermediate";
        benchPress.timePerRep = 3000;

        Exercise squat = new Exercise();
        squat.title = "Squat";
        squat.description = "Squat exercise";
        squat.bodyPart = "legs";
        squat.equipment = "bodyweight";
        squat.difficulty = "beginner";
        squat.timePerRep = 2500;

        Exercise bicepCurl = new Exercise();
        bicepCurl.title = "Bicep Curl";
        bicepCurl.description = "Bicep curl exercise";
        bicepCurl.bodyPart = "arms";
        bicepCurl.equipment = "dumbbell";
        bicepCurl.difficulty = "beginner";
        bicepCurl.timePerRep = 1500;

        mockExercises.add(pushUp);
        mockExercises.add(benchPress);
        mockExercises.add(squat);
        mockExercises.add(bicepCurl);
    }

    @Test
    public void testCreateWorkoutWithValidInputs() {
        Workout workout = workoutAlgorithm.createWorkoutFromExercises(Collections.singletonList("chest"), Collections.singletonList(""), "bodyweight", 10);
        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise -> {
            assertEquals("chest", exercise.bodyPart);
            assertEquals("bodyweight", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithLimitedTime() {
        Workout workout = workoutAlgorithm.createWorkoutFromExercises(Collections.singletonList("arms"), Collections.singletonList(""), "dumbbell", 2);
        assertNotNull(workout);
        assertTrue(workout.getExercises().size() <= 1);
        workout.getExercises().forEach(exercise -> {
            assertEquals("arms", exercise.bodyPart);
            assertEquals("dumbbell", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithDifferentBodyPart() {
        Workout workout = workoutAlgorithm.createWorkoutFromExercises(Collections.singletonList("legs"), Collections.singletonList(""), "bodyweight", 5);
        assertNotNull(workout);
        workout.getExercises().forEach(exercise -> {
            assertEquals("legs", exercise.bodyPart);
            assertEquals("bodyweight", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithAllEquipment() {
        Workout workout = workoutAlgorithm.createWorkoutFromExercises(Collections.singletonList("chest"), Collections.singletonList(""), "All", 15);
        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());

        workout.getExercises().forEach(exercise -> {
            assertEquals("chest", exercise.bodyPart);
            assertTrue(exercise.equipment.equals("bodyweight") || exercise.equipment.equals("barbell"));
        });
    }

    @Test
    public void testCreateWorkoutWithMultipleBodyParts() {
        List<String> bodyParts = List.of("chest", "legs", "arms");
        List<String> dislikedBodyParts = Collections.singletonList("");
        Workout workout = workoutAlgorithm.createWorkoutFromExercises(bodyParts, dislikedBodyParts, "All", 20);

        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());

        workout.getExercises().forEach(exercise -> {
            assertTrue(bodyParts.contains(exercise.bodyPart));
            assertTrue(exercise.equipment.equals("bodyweight") || exercise.equipment.equals("barbell") || exercise.equipment.equals("dumbbell"));
        });
    }
}
