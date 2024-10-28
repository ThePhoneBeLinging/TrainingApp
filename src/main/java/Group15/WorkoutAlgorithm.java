package Group15;

import Group15.Api.ApiUtils;
import Group15.Api.Exercise;

import java.util.List;

public class WorkoutAlgorithm {
    public Workout createWorkoutFromExercises(String bodyPart, String equipment, int timeInMinutes) {
        List<Exercise> exercises = ApiUtils.getAllExercises();

        int timeInMilliseconds = timeInMinutes * 60000;
        Workout workout = new Workout();

        System.out.println("Workout");

        if (exercises != null) {
            for (Exercise exercise : exercises) {
                if (exercise.bodyPart.equals(bodyPart)) {
                    boolean equipmentMatch = equipment.equals("All") || exercise.equipment.equals(equipment);
                    if (equipmentMatch) {
                        int timePerRep = exercise.timePerRep;
                        int maxPossibleReps = timeInMilliseconds / timePerRep;
                        int repsPerSet = Math.min(10, Math.max(5, maxPossibleReps));
                        int sets = Math.min(3, maxPossibleReps / repsPerSet);
                        int timeUsed = sets * repsPerSet * timePerRep;

                        if (timeUsed > timeInMilliseconds) {
                            break;
                        }

                        timeInMilliseconds -= timeUsed;

                        workout.addExercise(exercise);

                        System.out.println("Exercise name: " + exercise.title);
                        System.out.println("Description: " + exercise.description);
                        System.out.println("Equipment: " + exercise.equipment);
                        System.out.println("Sets: " + sets);
                        System.out.println("Rep Range: 5-10");

                        if (timeInMilliseconds <= 0) {
                            break;
                        }
                    }
                }
            }
        }
        return workout;
    }
}
