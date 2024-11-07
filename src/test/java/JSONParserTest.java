import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Model.Workout;
import Group15.Util.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONParserTest
{
    // Can hold objects between tests:
    private String exercisesFilePath;
    private String workoutsFilePath;
    private List<Exercise> exercises;
    private List<Workout> workouts;

    private void initExercises()
    {
        exercises = new ArrayList<>();

        Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        Exercise situp = new Exercise("Situp", "Sit up and down", Collections.singletonList(BodyPart.Abdominals), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        exercises.add(pushup);
        exercises.add(situp);
    }

    private void initWorkouts()
    {
        workouts = new ArrayList<>();

        Workout workout = new Workout();
        for (Exercise exercise : exercises)
        {
            workout.addExercise(exercise);
        }
        workouts.add(workout);
        workout = new Workout();
        workouts.add(workout);
    }

    @Before
    public void setUp()
    {
        initExercises();
        initWorkouts();
        exercisesFilePath = "src/test/resources/exercises.json";
        workoutsFilePath = "src/test/resources/workouts.json";

    }

    @Test
    public void testSaveFunction()
    {
        JSONParser.saveObjectsAsJSON(exercisesFilePath, exercises.toArray());
        JSONParser.saveObjectsAsJSON(workoutsFilePath, workouts.toArray());
    }

    @Test
    public void testLoadFunction()
    {
        var jsonExercises = JSONParser.loadObjectsFromJSON(exercisesFilePath, Exercise[].class);
        var jsonWorkouts = JSONParser.loadObjectsFromJSON(workoutsFilePath, Workout[].class);

        for (int i = 0; i < exercises.size(); i++)
        {
            Assert.assertEquals(jsonExercises[i], exercises.get(i));
        }
        for (int i = 0; i < workouts.size(); i++)
        {
            Assert.assertEquals(jsonWorkouts[i], workouts.get(i));
        }
    }
}
