package Group15.Util;

import Group15.Model.Workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutUtils {

    private static final String SAVED_WORKOUTS_FILE_PATH = "src/main/resources/userData/savedWorkouts.json";

    public static List<Workout> getSavedWorkouts() {
        List<Workout> savedWorkouts = new ArrayList<>(Arrays.asList(JSONParser.loadObjectsFromJSON(SAVED_WORKOUTS_FILE_PATH, Workout[].class)));

        // Only way i could figure out how to remove the null workout that is in the file by standard
        return savedWorkouts.stream()
                .filter(workout -> workout.getName() != null && workout.getDescription() != null)
                .collect(Collectors.toList());
    }
}
