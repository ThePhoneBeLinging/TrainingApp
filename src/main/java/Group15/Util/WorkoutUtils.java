package Group15.Util;

import Group15.Model.Workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutUtils {

    private static final String USER_DATA_PATH = "src/main/resources/userData/savedWorkouts.json";

    private static final String POPULAR_DATA_PATH = "src/main/resources/userData/popularWorkouts.json";

    private static List<Workout> savedWorkouts;

    private static List<Workout> popularWorkouts;

    static {
        init();
    }

    public static void init() {
        savedWorkouts = new ArrayList<>(Arrays.asList(JSONParser.loadObjectsFromJSON(USER_DATA_PATH, Workout[].class)));
        popularWorkouts = new ArrayList<>(Arrays.asList(JSONParser.loadObjectsFromJSON(POPULAR_DATA_PATH, Workout[].class)));
        removeInvalidWorkouts();
    }

    private static void removeInvalidWorkouts() {
        savedWorkouts = savedWorkouts.stream()
                .filter(workout -> workout.getName() != null || !workout.getExercises().isEmpty())
                .collect(Collectors.toList());
        if (popularWorkouts != null) {
            popularWorkouts = popularWorkouts.stream()
                    .filter(workout -> workout.getName() != null || !workout.getExercises().isEmpty())
                    .collect(Collectors.toList());
        }

    }

    public static List<Workout> getSavedWorkouts() {
        return savedWorkouts;
    }

    public static List<Workout> getPopularWorkouts(){
        return popularWorkouts;
    }

    public static Boolean isPopularWorkout(Workout workout) {
        if (workout.getIsSaved()) {
            return false;
        }
        return popularWorkouts.contains(workout);
    }

    public static void addWorkout(Workout workout) {
        savedWorkouts.add(workout);
        writeToFile();
    }

    public static void deleteWorkout(Workout workout) {
        savedWorkouts.remove(workout);
        writeToFile();
    }

    public static void writeToFile() {JSONParser.saveObjectsAsJSON(USER_DATA_PATH, savedWorkouts.toArray());}
}