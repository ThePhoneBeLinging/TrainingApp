package Group15;

import Group15.Api.ApiUtils;
import Group15.Api.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAlgorithm {
    public Workout createWorkoutFromExercises(String bodyPart, String equipment, int timeInMinutes) {
        List<Exercise> exercises = ApiUtils.getAllExercises();

        int timeInMilliseconds = timeInMinutes * 60000;

        Workout workout = new Workout();

        System.out.println("Workout");

        if(exercises != null) {
            for(int i = 0; i < exercises.size(); i++) {
                if(exercises.get(i).bodyPart.equals(bodyPart) &&
                        (equipment.equals("All") || exercises.get(i).equipment.equals(equipment))) {
                   int timePerRep = exercises.get(i).timePerRep;
                   int maxPossibleReps = timeInMilliseconds / timePerRep;
                   int repsPerSet = Math.min(10, Math.max(5,maxPossibleReps));
                   int sets = Math.min(3, maxPossibleReps / repsPerSet);
                   int timeUsed = sets * repsPerSet * timePerRep;

                   if(timeUsed > timeInMilliseconds) {
                       break;
                   }

                   timeInMilliseconds -= timeUsed;

                   workout.addExercise(exercises.get(i));

                    System.out.println("Exercise name: " + exercises.get(i).title);
                    System.out.println("Description: " + exercises.get(i).description);
                    System.out.println("Equipment: " + exercises.get(i).equipment);
                    System.out.println("Sets: " + sets);
                    System.out.println("Rep Range: 5-10");

                    if(timeInMilliseconds <= 0) {
                        break;
                    }
                }
            }
        }
        return workout;
    }
}
