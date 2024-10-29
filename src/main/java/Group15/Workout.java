package Group15;

import Group15.Api.Exercise;

import java.util.ArrayList;
import java.util.List;

public class Workout
{
    private final List<Exercise> exercises = new ArrayList<>();

    public Workout()
    {
    }

    public void addExercise(Exercise exercise)
    {
        exercises.add(exercise);
    }

    public void swapExercise(Exercise toRemove, Exercise toAdd)
    {
        if (exercises.contains(toRemove))
        {
            removeExercise(toRemove);
            addExercise(toAdd);
            return;
        }
        System.err.println("Exercise not found in workout");
        System.exit(1);

    }

    public void removeExercise(Exercise exercise)
    {
        exercises.remove(exercise);
    }

    public List<Exercise> getExercises()
    {
        return exercises;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Workout))
        {
            return false;
        }
        boolean result = true;

        for (int i = 0; i < exercises.size(); i++)
        {
            result &= exercises.get(i).equals(((Workout) obj).exercises.get(i));
        }

        return result;
    }
}
