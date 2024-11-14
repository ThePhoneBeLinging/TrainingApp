package Group15.Model;

public class WorkoutExercise
{
    private Exercise exercise;
    private int sets;
    private int repsPerSet;

    public WorkoutExercise()
    {
        exercise = new Exercise();
    }

    public Exercise getExercise()
    {
        return exercise;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }

    public int getSets()
    {
        return sets;
    }

    public void setSets(int sets)
    {
        this.sets = sets;
    }

    public int getRepsPerSet()
    {
        return repsPerSet;
    }

    public void setRepsPerSet(int repsPerSet)
    {
        this.repsPerSet = repsPerSet;
    }

    @Override
    public boolean equals(Object obj)
    {
        return exercise.equals(((WorkoutExercise) obj).getExercise());
    }
}
