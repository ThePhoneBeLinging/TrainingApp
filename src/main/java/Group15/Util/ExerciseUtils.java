package Group15.Util;

import Group15.Model.Exercise;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseUtils {

    private static final String LIKED_FILE = "liked.dat";
    private static final String DISLIKED_FILE = "disliked.dat";

    public static void saveExercises(List<Exercise> exercises, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(exercises);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Exercise> loadExercises(String fileName) {
        List<Exercise> exercises = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            exercises = (List<Exercise>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    public static void addExerciseToFile(Exercise exercise, String fileName) {
        List<Exercise> exercises = loadExercises(fileName);
        exercises.add(exercise);
        saveExercises(exercises, fileName);
    }

    public static void removeExerciseFromFile(Exercise exercise, String fileName) {
        List<Exercise> exercises = loadExercises(fileName);
        exercises.remove(exercise);
        saveExercises(exercises, fileName);
    }
}