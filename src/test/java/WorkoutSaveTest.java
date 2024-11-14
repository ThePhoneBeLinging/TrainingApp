import Group15.Model.Workout;
import Group15.Util.JSONParser;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutSaveTest
{

    @Test
    public void testSaveWorkout()
    {
        Workout workout = new Workout();
        workout.saveWorkout();

        List<Workout> savedWorkouts = Workout.getSavedWorkouts();
        assertNotNull(savedWorkouts);
        assertTrue(savedWorkouts.size() > 0);
        assertEquals(workout, savedWorkouts.get(savedWorkouts.size() - 1));
    }
}