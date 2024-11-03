package Group15.Model;

import java.util.ArrayList;
import java.util.List;

public class Workout
{
    private final List<Exercise> exercises = new ArrayList<>();
    private String name;
    private String description;

    public Workout()
    {
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