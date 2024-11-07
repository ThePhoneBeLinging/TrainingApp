package Group15.Util;

import Group15.Model.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseUtils {

    private static final String USER_DATA_PATH = "src/main/resources/userData/";

    private static List<Exercise> likedExercises;
    private static List<Exercise> dislikedExercises;

    static {
        init();
    }

    public static void init() {

        likedExercises = new ArrayList<>(Arrays.asList(JSONParser.loadObjectsFromJSON(USER_DATA_PATH + "likedExercises.json", Exercise[].class)));
        dislikedExercises = new ArrayList<>(Arrays.asList(JSONParser.loadObjectsFromJSON(USER_DATA_PATH + "dislikedExercises.json", Exercise[].class)));
    }

    public static void likeExercisePressed(Exercise exercise) {
        if (likedExercises.contains(exercise)) {
            likedExercises.remove(exercise);
        } else {
            likedExercises.add(exercise);
            dislikedExercises.remove(exercise);
        }
        writeToFile();
    }

    public static void dislikeExercisePressed(Exercise exercise) {
        if (dislikedExercises.contains(exercise)) {
            dislikedExercises.remove(exercise);
        } else {
            dislikedExercises.add(exercise);
            likedExercises.remove(exercise);
        }
        writeToFile();
    }

    public static List<Exercise> getLikedExercises() {
        return likedExercises;
    }

    public static List<Exercise> getDislikedExercises() {
        return dislikedExercises;
    }

    private static void writeToFile() {
        JSONParser.saveObjectsAsJSON(USER_DATA_PATH + "likedExercises.json", likedExercises.toArray());
        JSONParser.saveObjectsAsJSON(USER_DATA_PATH + "dislikedExercises.json", dislikedExercises.toArray());
    }
}