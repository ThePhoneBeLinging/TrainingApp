package Group15;

import Group15.Api.ApiUtils;
import Group15.Api.Exercise;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorkoutAlgorithm {
    public static Workout createWorkoutFromExercises(List<String> bodyParts, List<String> dislikedBodyParts, List<String> equipment, int timeInMinutes) {
        List<Exercise> exercises = ApiUtils.getAllExercises();

        int timeInMilliseconds = timeInMinutes * 60000;
        Workout workout = new Workout();

        if (exercises != null) {
            Collections.shuffle(exercises, new Random(exercises.size()));
            for (Exercise exercise : exercises) {
                if(dislikedBodyParts.contains(exercise.bodyPart)) {
                    continue;
                }
                if (bodyParts.contains(exercise.bodyPart)) {
                    boolean equipmentMatch = equipment.contains("All") || equipment.contains(exercise.equipment);
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
