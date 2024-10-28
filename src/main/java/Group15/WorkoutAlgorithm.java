package Group15;

import Group15.Api.ApiUtils;
import Group15.Api.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAlgorithm {
    public Workout createWorkoutFromExercises(String bodyPart, int timeInMinutes) {
        List<Exercise> exercises = ApiUtils.getAllExercises();

        int timeInMilliseconds = timeInMinutes * 60000;

        Workout workout = new Workout();

        if(exercises != null) {
            for(int i = 0; i < exercises.size(); i++) {
                if(exercises.get(i).bodyPart.equals(bodyPart)) {
                   int timePerRep = exercises.get(i).timePerRep;
                   int maxPossibleReps = timeInMilliseconds / timePerRep;
                   int repsPerSet = Math.min(10, Math.max(5,maxPossibleReps));
                   int sets = Math.min(3, maxPossibleReps / repsPerSet);
                }
            }
        }
        return workout;
    }
}
