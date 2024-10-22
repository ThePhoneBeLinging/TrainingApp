package Group15;

import Group15.Api.Exercise;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<Exercise> exercises = new ArrayList<>();

    public Workout() {}

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void swapExercise(Exercise toRemove, Exercise toAdd) {
        removeExercise(toRemove);
        addExercise(toAdd);

    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
