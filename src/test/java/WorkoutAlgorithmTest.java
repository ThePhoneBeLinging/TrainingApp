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

    private List<Exercise> mockExercises;

    @Before
    public void setUp() {
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

        Exercise legExtension = new Exercise();
        legExtension.title = "Leg Extension";
        legExtension.description = "Leg extension exercise";
        legExtension.bodyPart = "quads, legs";
        legExtension.equipment = "machine, weights";
        legExtension.difficulty = "beginner";
        legExtension.timePerRep = 3000;

        mockExercises.add(pushUp);
        mockExercises.add(benchPress);
        mockExercises.add(squat);
        mockExercises.add(bicepCurl);
        mockExercises.add(legExtension);
    }

    @Test
    public void testCreateWorkoutWithValidInputs() {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList("chest"),
                Collections.singletonList(""),
                Collections.singletonList("bodyweight"),
                10
        );

        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise -> {
            assertEquals("chest", exercise.bodyPart);
            assertEquals("bodyweight", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithLimitedTime() {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList("arms"),
                Collections.singletonList(""),
                Collections.singletonList("dumbbell"),
                2
        );

        assertNotNull(workout);
        assertTrue(workout.getExercises().size() <= 1);
        workout.getExercises().forEach(exercise -> {
            assertEquals("arms", exercise.bodyPart);
            assertEquals("dumbbell", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithDifferentBodyPart() {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList("legs"),
                Collections.singletonList(""),
                Collections.singletonList("bodyweight"),
                5
        );

        assertNotNull(workout);
        workout.getExercises().forEach(exercise -> {
            assertEquals("legs", exercise.bodyPart);
            assertEquals("bodyweight", exercise.equipment);
        });
    }

    @Test
    public void testCreateWorkoutWithAllEquipment() {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList("chest"),
                Collections.singletonList(""),
                Collections.singletonList("All"),
                15
        );

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

        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                dislikedBodyParts,
                Collections.singletonList("All"),
                20
        );

        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise -> {
            assertTrue(bodyParts.contains(exercise.bodyPart));
            assertTrue(exercise.equipment.equals("bodyweight") || exercise.equipment.equals("barbell") || exercise.equipment.equals("dumbbell"));
        });
    }

    @Test
    public void testCreateWorkoutWithDislikedBodyParts() {
        List<String> bodyParts = List.of("chest", "legs", "arms");
        List<String> dislikedBodyParts = Collections.singletonList("legs");

        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                dislikedBodyParts,
                Collections.singletonList("All"),
                20
        );

        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());

        workout.getExercises().forEach(exercise -> {
            assertFalse("legs".equals(exercise.bodyPart));
        });
    }

    @Test
    public void testMultipleBodyPartsAndEquipment() {
        List<String> bodyParts = List.of("quads, legs", "chest, triceps");
        List<String> equipments = List.of("machine, body", "weights, machine");

        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                Collections.singletonList(""),
                equipments,
                10
        );

        assertNotNull(workout);
        assertFalse(workout.getExercises().isEmpty());

        workout.getExercises().forEach(exercise -> {
            assertFalse("machine, weights".contains(exercise.equipment));
            assertFalse("legs, quads".contains(exercise.bodyPart));
        });
    }
}
