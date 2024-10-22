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
        //Add when fred eventually manages to get his PR through
        //Exercise pushup = new Exercise("Pushup", "Push your body up and down", "Strength", "Chest", "None", "Easy");
        testWorkout.addExercise(new Exercise());
        Assert.assertEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(new Exercise());
        testWorkout.addExercise(new Exercise());
        Assert.assertEquals(3, testWorkout.getExercises().size());
    }

    @Test
    public void testRemoveExercise() {
        //Add when fred eventually manages to get his PR through
    }


}
