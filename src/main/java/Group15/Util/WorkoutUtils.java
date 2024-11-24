package Group15.Util;

import Group15.Model.Workout;

import java.util.List;

public class WorkoutUtils {

    private static final String SAVED_WORKOUTS_FILE_PATH = "src/main/resources/userData/savedWorkouts.json";

    public static List<Workout> getSavedWorkouts() {
        Workout[] savedWorkouts = JSONParser.loadObjectsFromJSON(SAVED_WORKOUTS_FILE_PATH, Workout[].class);
        return List.of(savedWorkouts);
    }
}
