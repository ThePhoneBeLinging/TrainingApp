import Group15.Model.*;
import org.junit.jupiter.api.*;

import java.util.Collections;

public class WorkoutClassTest
{
    static Workout testWorkout;

    @BeforeEach
    public void setup()
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
        WorkoutExercise wPushUp = new WorkoutExercise();
        wPushUp.setExercise(pushup);
        testWorkout.addExercise(wPushUp);
        Assertions.assertEquals(1, testWorkout.getExercises().size());
        Assertions.assertEquals("Pushup", testWorkout.getExercises().getFirst().getExercise().title);
        testWorkout.addExercise(new WorkoutExercise());
        Assertions.assertNotEquals(1, testWorkout.getExercises().size());
        testWorkout.addExercise(new WorkoutExercise());
        Assertions.assertEquals(3, testWorkout.getExercises().size());
        Assertions.assertNotEquals("Pushup", testWorkout.getExercises().get(1).getExercise().title);
    }

    @Test
    public void testRemoveExercise()
    {
        Exercise situp = new Exercise("Situp", "Sit up and down", Collections.singletonList(BodyPart.Abdominals), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);
        WorkoutExercise wSitup = new WorkoutExercise();
        wSitup.setExercise(situp);
        testAddExercise();
        testWorkout.addExercise(wSitup);
        Assertions.assertTrue(testWorkout.getExercises().contains(wSitup));
        Assertions.assertEquals(4, testWorkout.getExercises().size());
        testWorkout.removeExercise(wSitup);
        Assertions.assertEquals(3, testWorkout.getExercises().size());
        Assertions.assertFalse(testWorkout.getExercises().contains(wSitup));
    }

    @Test
    public void testSwapExercise()
    {
        testRemoveExercise();
        Assertions.assertEquals("Pushup", testWorkout.getExercises().getFirst().getExercise().title);
        Exercise pullup = new Exercise("Pullup", "Pull your body up and down", Collections.singletonList(BodyPart.Back), Collections.singletonList(Equipment.Bodyweight), "Hard", "None", 3000);
        WorkoutExercise wPullUp = new WorkoutExercise();
        wPullUp.setExercise(pullup);
        WorkoutExercise wPushUp = new WorkoutExercise();
        wPushUp.setExercise(new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000));
        testWorkout.swapExercise(testWorkout.getExercises().getFirst(), wPullUp);
        Assertions.assertTrue(testWorkout.getExercises().contains(wPullUp));
        Assertions.assertFalse(testWorkout.getExercises().contains(wPushUp));

    }


}
