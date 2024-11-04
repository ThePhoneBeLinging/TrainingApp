package Group15.Util;

import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Model.Workout;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorkoutAlgorithm {
    public static Workout createWorkoutFromExercises(
            List<BodyPart> bodyParts,
            List<BodyPart> dislikedBodyParts,
            List<Equipment> equipment,
            int timeInMinutes){

        List<Exercise> exercises = Api.getAllExercises();

        int timeInMilliseconds = timeInMinutes * 60000;
        Workout workout = new Workout();

        if (exercises != null) {
            Collections.shuffle(exercises, new Random(exercises.size()));
            for (Exercise exercise : exercises) {
                if(dislikedBodyParts == exercise.bodyPart) {
                    continue;
                }
                if (bodyParts == exercise.bodyPart) {
                    boolean equipmentMatch = equipment == exercise.equipment;
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
