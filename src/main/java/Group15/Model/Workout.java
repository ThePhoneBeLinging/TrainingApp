package Group15.Model;

import Group15.Util.JSONParser;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workout implements Serializable {
    private final List<WorkoutExercise> exercises = new ArrayList<>();
    private String name;
    private String description;

    public Workout() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addExercise(WorkoutExercise exercise) {
        exercises.add(exercise);
    }

    public void swapExercise(WorkoutExercise toRemove, WorkoutExercise toAdd) {
        if (exercises.contains(toRemove)) {
            removeExercise(toRemove);
            addExercise(toAdd);
            return;
        }
        System.err.println("Exercise not found in workout");
        System.exit(1);

    }

    public void removeExercise(WorkoutExercise exercise) {
        exercises.remove(exercise);
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Workout)) {
            return false;
        }
        boolean result = true;

        for (int i = 0; i < exercises.size(); i++) {
            result &= exercises.get(i).equals(((Workout) obj).exercises.get(i));
        }

        return result;
    }

    public void saveWorkout() {
        String filePath = "src/main/resources/userData/savedWorkouts.json";
        File file = new File(filePath);

        List<Workout> workouts = new ArrayList<>();
        if (file.exists() && file.length() > 0) {
            Workout[] existingWorkouts = JSONParser.loadObjectsFromJSON(filePath, Workout[].class);
            if (existingWorkouts != null) {
                Collections.addAll(workouts, existingWorkouts);
            }
        }

        workouts.add(this);

        JSONParser.saveObjectsAsJSON(filePath, workouts.toArray(new Workout[0]));
    }

    public static List<Workout> getSavedWorkouts() {
        String filePath = "src/main/resources/userData/savedWorkouts.json";
        File file = new File(filePath);

        if (file.exists() && file.length() > 0) {
            Workout[] existingWorkouts = JSONParser.loadObjectsFromJSON(filePath, Workout[].class);
            if (existingWorkouts != null) {
                return List.of(existingWorkouts);
            }
        }
        return new ArrayList<>();
    }
}
