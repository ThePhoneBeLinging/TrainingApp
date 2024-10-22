package Group15;

import Group15.Api.Exercise;

import java.util.List;

public class Workout {
    private List<Exercise> exercises;

    public Workout() {}

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void swapExercise(Exercise toRemove, Exercise toAdd) {
        if (exercises.contains(toRemove)) {
            exercises.remove(toRemove);
            exercises.add(toAdd);
        }
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
