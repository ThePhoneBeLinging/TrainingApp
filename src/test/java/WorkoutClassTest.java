import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Model.Workout;
import org.junit.jupiter.api.*;

import java.util.Collections;

public class WorkoutClassTest
{
    static Workout testWorkout;

    @BeforeAll
    public static void setup()
    {
        testWorkout = new Workout();
    }

    @Test
    public void testInit()
    {
        Assertions.assertEquals(0, testWorkout.getExercises().size());
    }

    @Test
    public void testAddExercise()
    {
        Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        testWorkout.addExercise(pushup);
        Assertions.assertEquals(1, testWorkout.getExercises().size());
        Assertions.assertEquals("Pushup", testWorkout.getExercises().getFirst().title);
        testWorkout.addExercise(new Exercise());
        Assertions.assertNotEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(new Exercise());
        Assertions.assertEquals(3, testWorkout.getExercises().size());
        Assertions.assertNotEquals("Pushup", testWorkout.getExercises().get(1).title);
    }

    @Test
    public void testRemoveExercise()
    {
        Exercise situp = new Exercise("Situp", "Sit up and down", Collections.singletonList(BodyPart.Abdominals), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        testAddExercise();
        testWorkout.addExercise(situp);
        Assertions.assertTrue(testWorkout.getExercises().contains(situp));
        Assertions.assertEquals(4, testWorkout.getExercises().size());
        testWorkout.removeExercise(situp);
        Assertions.assertEquals(3, testWorkout.getExercises().size());
        Assertions.assertFalse(testWorkout.getExercises().contains(situp));
    }

    @Test
    public void testSwapExercise()
    {
        testRemoveExercise();
        Assertions.assertEquals("Pushup", testWorkout.getExercises().getFirst().title);
        Exercise pullup = new Exercise("Pullup", "Pull your body up and down", Collections.singletonList(BodyPart.Back), Collections.singletonList(Equipment.Bodyweight), "Hard", "None", 3000);
        testWorkout.swapExercise(testWorkout.getExercises().getFirst(), pullup);
        Assertions.assertTrue(testWorkout.getExercises().contains(pullup));
        Assertions.assertFalse(testWorkout.getExercises().contains(new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000)));

    }


}
