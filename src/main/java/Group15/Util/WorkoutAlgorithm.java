package Group15.Util;

import Group15.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutAlgorithm {

    private static HashMap<String, Boolean> selectedExercises = new HashMap<>();

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

            // If this happens, we have most likely gathered all exercises that fit the given parameters
            if (validExercise == null)
            {
                return workout;
            }

            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(validExercise);

            // This is basicly what musti does, might not be optimal
            workoutExercise.setRepsPerSet(5);
            int timePerSet = validExercise.timePerRep * 5;

            while (timeLeftInMilli > 0 && workoutExercise.getSets() < 3)
            {
                timeLeftInMilli -= timePerSet;
                workoutExercise.setSets(workoutExercise.getSets() + 1);
                timeLeftInMilli -= 120;
            }
            selectedExercises.put(validExercise.title,true);
            workout.addExercise(workoutExercise);
            // Check if time is spent, if so, we remove the last set or exercise if sets = 1
            if (timeLeftInMilli < 0)
            {
                if (workoutExercise.getSets() == 1)
                {
                    workout.removeExercise(workoutExercise);
                }
                else
                {
                    workoutExercise.setSets(workoutExercise.getSets() - 1);
                }
                return workout;
            }
        }

        return null;
    }

    private static Exercise getValidExercise(List<BodyPart> selectedBodyParts, List<BodyPart> bodyPartsToAvoid, List<Equipment> equipment)
    {

        int maxRuns = 1000;
        int totalRuns = 0;
        double chanceToAvoidNonLiked = 0.25;
        while (maxRuns > totalRuns)
        {
            Exercise exercise = getRandomExercise();

            boolean exerciseValid = true;
            for (var bodyPart : bodyPartsToAvoid)
            {
                if (exercise.bodyParts.contains(bodyPart))
                {
                    exerciseValid = false;
                    break;
                }
            }

            for (var bodyPart : selectedBodyParts)
            {
                if (exercise.bodyParts.contains(bodyPart))
                {
                    continue;
                }

                if (bodyPart.equals(selectedBodyParts.getLast()))
                {
                    exerciseValid = false;
                }
            }

            exerciseValid |= equipment.containsAll(exercise.equipment);
            exerciseValid |= !selectedExercises.containsKey(exercise.title);
            exerciseValid |= !ExerciseUtils.getDislikedExercises().contains(exercise);

            if (exerciseValid)
            {

                if (!ExerciseUtils.getLikedExercises().contains(exercise))
                {
                    if (Math.random() <= chanceToAvoidNonLiked)
                    {
                        continue;
                    }
                }


                return exercise;
            }
            totalRuns++;
        }
        return null;
    }

    private static Exercise getRandomExercise()
    {
        List<Exercise> exercises = Api.getAllExercises();

        int randomIndex = (int) (Math.random() * exercises.size());
        return exercises.get(randomIndex);
    }
}
