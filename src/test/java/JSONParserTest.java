import Group15.Model.*;
import Group15.Util.JSONParser;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONParserTest {
    // Can hold objects between tests:
    private static String exercisesFilePath;
    private static String workoutsFilePath;
    private static List<Exercise> exercises;
    private static List<Workout> workouts;

    private static void initExercises() {
        exercises = new ArrayList<>();

        Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        Exercise situp = new Exercise("Situp", "Sit up and down", Collections.singletonList(BodyPart.Abdominal), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        exercises.add(pushup);
        exercises.add(situp);
    }

    private static void initWorkouts() {
        workouts = new ArrayList<>();

        Workout workout = new Workout();
        for (Exercise exercise : exercises) {
            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(exercise);
            workout.addExercise(workoutExercise);
        }
        workouts.add(workout);
        workout = new Workout();
        workouts.add(workout);
    }

    @BeforeAll
    public static void setUp() {
        initExercises();
        initWorkouts();
        exercisesFilePath = "src/test/resources/exercises.json";
        workoutsFilePath = "src/test/resources/workouts.json";

    }

    @AfterAll
    public static void tearDown() throws IOException {
        exercises = null;
        workouts = null;
        Files.deleteIfExists(Paths.get(exercisesFilePath));
        Files.deleteIfExists(Paths.get(workoutsFilePath));
    }

    @Test
    public void testSaveFunction() {
        JSONParser.saveObjectsAsJSON(exercisesFilePath, exercises.toArray());
        JSONParser.saveObjectsAsJSON(workoutsFilePath, workouts.toArray());
    }

    @Test
    public void testLoadFunction() {
        var jsonExercises = JSONParser.loadObjectsFromJSON(exercisesFilePath, Exercise[].class);
        var jsonWorkouts = JSONParser.loadObjectsFromJSON(workoutsFilePath, Workout[].class);

        for (int i = 0; i < exercises.size(); i++) {
            Assertions.assertEquals(jsonExercises[i], exercises.get(i));
        }
        for (int i = 0; i < workouts.size(); i++) {
            Assertions.assertEquals(jsonWorkouts[i], workouts.get(i));
        }
    }
}
