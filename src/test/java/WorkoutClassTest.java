import Group15.Api.Exercise;
import Group15.Workout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkoutClassTest {
    Workout testWorkout;

    @Before
    public void setup() {
        testWorkout = new Workout();
    }

    @Test
    public void testInit() {
        Assert.assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    public void testAddExercise() {
        Exercise pushup = new Exercise("Pushup", "Push your body up and down", "Strength", "Chest", "None", "Easy", "None");
        testWorkout.addExercise(pushup);
        Assert.assertEquals(1, testWorkout.getExercises().size());
        Assert.assertEquals("Pushup", testWorkout.getExercises().get(0).title);
        testWorkout.addExercise(new Exercise());
        Assert.assertNotEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(new Exercise());
        Assert.assertEquals(3, testWorkout.getExercises().size());
        Assert.assertNotEquals("Pushup", testWorkout.getExercises().get(1).title);
    }

    @Test
    public void testRemoveExercise() {
        Exercise situp = new Exercise("Situp", "Sit up and down", "Strength", "Abs", "None", "Easy", "None");
        testAddExercise();
        testWorkout.addExercise(situp);
        Assert.assertTrue(testWorkout.getExercises().contains(situp));
        Assert.assertEquals(4, testWorkout.getExercises().size());
        testWorkout.removeExercise(situp);
        Assert.assertEquals(3, testWorkout.getExercises().size());
        Assert.assertFalse(testWorkout.getExercises().contains(situp));
    }

    @Test
    public void testSwapExercise() {
        testRemoveExercise();
        Assert.assertEquals("Pushup", testWorkout.getExercises().get(0).title);
        Exercise pullup = new Exercise("Pullup", "Pull your body up and down", "Strength", "Back", "Pullup bar", "Hard", "None");
        testWorkout.swapExercise(testWorkout.getExercises().get(0), pullup);
        Assert.assertTrue(testWorkout.getExercises().contains(pullup));
        Assert.assertFalse(testWorkout.getExercises().contains(new Exercise("Pushup", "Push your body up and down", "Strength", "Chest", "None", "Easy", "None")));

    }


}
