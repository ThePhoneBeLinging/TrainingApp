import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Model.Workout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

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
        Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        testWorkout.addExercise(pushup);
        Assert.assertEquals(1, testWorkout.getExercises().size());
        Assert.assertEquals("Pushup", testWorkout.getExercises().getFirst().title);
        testWorkout.addExercise(new Exercise());
        Assert.assertNotEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(new Exercise());
        Assert.assertEquals(3, testWorkout.getExercises().size());
        Assert.assertNotEquals("Pushup", testWorkout.getExercises().get(1).title);
    }

    @Test
    public void testRemoveExercise() {
        Exercise situp = new Exercise("Situp", "Sit up and down", Collections.singletonList(BodyPart.Abdominal), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
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
        Assert.assertEquals("Pushup", testWorkout.getExercises().getFirst().title);
        Exercise pullup = new Exercise("Pullup", "Pull your body up and down", Collections.singletonList(BodyPart.UpperBack), Collections.singletonList(Equipment.Bodyweight), "Hard", "None", 3000);
        testWorkout.swapExercise(testWorkout.getExercises().getFirst(), pullup);
        Assert.assertTrue(testWorkout.getExercises().contains(pullup));
        Assert.assertFalse(testWorkout.getExercises().contains(new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000)));

    }


}
