package Group15;

import Group15.Api.ApiUtils;
import Group15.Api.Exercise;

import java.util.List;

public class WorkoutAlgorithm {
    public Workout createWorkoutFromExercises(String bodyPart, int timeInSeconds) {
        List<Exercise> exercises = ApiUtils.getAllExercises();
        Workout workout = new Workout();

        if(exercises != null) {
            for(int i = 0; i < exercises.size(); i++) {
                if(exercises.get(i).bodyPart.equals(bodyPart)) {
                    workout.addExercise(exercises.get(i));
                    System.out.println(i + ". Exercise for " + exercises.get(i).bodyPart + ": " + exercises.get(i).title);
                }
            }
        }
        return workout;
    }
}
