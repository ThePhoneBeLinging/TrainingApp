package Group15.Util;

import Group15.Model.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorkoutAlgorithm {
    public static Workout createWorkoutFromExercises(
            List<BodyPart> selectedBodyParts,
            List<BodyPart> bodyPartsToAvoid,
            List<Equipment> equipment,
            int timeInMinutes){

        int timeLeftInMilli = timeInMinutes * 60000;
        Workout workout = new Workout();

        while (timeLeftInMilli > 0)
        {
            Exercise validExercise = getValidExercise(selectedBodyParts, bodyPartsToAvoid, equipment);
            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(validExercise);
            workout.addExercise(workoutExercise);


        }
        /*
        if (exercises != null) {
            Collections.shuffle(exercises, new Random(exercises.size()));
            for (Exercise exercise : exercises) {
                if(bodyPartsToAvoid == exercise.bodyPart) {
                    continue;
                }
                if (selectedBodyParts == exercise.bodyPart) {
                    boolean equipmentMatch = equipment == exercise.equipment;
                    if (equipmentMatch) {
                        int timePerRep = exercise.timePerRep;
                        int maxPossibleReps = timeLeftInMilli / timePerRep;
                        int repsPerSet = Math.min(10, Math.max(5, maxPossibleReps));
                        int sets = Math.min(3, maxPossibleReps / repsPerSet);
                        int timeUsed = sets * repsPerSet * timePerRep;

                        if (timeUsed > timeLeftInMilli) {
                            break;
                        }

                        timeLeftInMilli -= timeUsed;

                        workout.addExercise(exercise);

                        if (timeLeftInMilli <= 0) {
                            break;
                        }
                    }
                }
            }

        }
        */
        //return workout;
        return null;
    }

    private static Exercise getValidExercise(List<BodyPart> selectedBodyParts, List<BodyPart> bodyPartsToAvoid, List<Equipment> equipment)
    {

        while (true)
        {
            Exercise exercise = getRandomExercise();

            boolean exerciseValid = true;
            for (var bodyPart : bodyPartsToAvoid)
            {
                if (exercise.bodyPart.contains(bodyPart))
                {
                    exerciseValid = false;
                    break;
                }
            }

            for (var bodyPart : selectedBodyParts)
            {
                if (exercise.bodyPart.contains(bodyPart))
                {
                    continue;
                }

                if (bodyPart.equals(selectedBodyParts.getLast()))
                {
                    exerciseValid = false;
                }
            }

            exerciseValid |= equipment.containsAll(exercise.equipment);

            if (exerciseValid)
            {
                return exercise;
            }
        }

    }

    private static Exercise getRandomExercise()
    {
        List<Exercise> exercises = Api.getAllExercises();

        int randomIndex = (int) (Math.random() * exercises.size());
        return exercises.get(randomIndex);
    }
}
