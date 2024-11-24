package Group15.Util;

import Group15.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutAlgorithm {

    private static HashMap<String, Boolean> selectedExercises;
    private static final int MAX_SETS = 3;
    private static final int REPS_PER_SET = 5;

    public static Workout createWorkoutFromExercises(
            List<BodyPart> selectedBodyParts,
            List<BodyPart> bodyPartsToAvoid,
            List<Equipment> equipment,
            int timeInMinutes){
        selectedExercises = new HashMap<>();
        int timeLeftInMilli = timeInMinutes * 60000;
        Workout workout = new Workout();

        int bodyPartIndex = 0;
        int nullExercisesInARow = 0;

        while (timeLeftInMilli > 0)
        {
            Exercise validExercise = getValidExercise(selectedBodyParts.get(bodyPartIndex), bodyPartsToAvoid, equipment);
            // If this happens, we have most likely gathered all exercises that fit the given parameters
            if (validExercise == null)
            {
                nullExercisesInARow++;
                if (nullExercisesInARow == selectedBodyParts.size())
                {
                    return workout;
                }
                bodyPartIndex = (bodyPartIndex + 1) % selectedBodyParts.size();
                continue;
            }
            nullExercisesInARow = 0;

            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(validExercise);

            // This is basicly what musti does, might not be optimal
            workoutExercise.setRepsPerSet(REPS_PER_SET);
            int timePerSet = validExercise.timePerRep * REPS_PER_SET;

            while (timeLeftInMilli > 0 && workoutExercise.getSets() < MAX_SETS)
            {
                timeLeftInMilli -= timePerSet;
                workoutExercise.setSets(workoutExercise.getSets() + 1);
                timeLeftInMilli -= Workout.BREAK_BETWEEN_SETS;
            }
            selectedExercises.put(validExercise.title,true);
            workout.addExercise(workoutExercise);
            bodyPartIndex = (bodyPartIndex + 1) % selectedBodyParts.size();
            // Check if time is spent, if so, we remove the last set or exercise if sets = 1
            if (timeLeftInMilli <= 0)
            {
                // If we only went over time because of the break between sets, we can simply return the
                // workout without removal
                if (timeLeftInMilli <= (Workout.BREAK_BETWEEN_SETS*-1))
                {
                    return workout;
                }
                if (workoutExercise.getSets() == 1)
                {
                    workout.removeExercise(workoutExercise);
                }
                else
                {
                    workoutExercise.setSets(workoutExercise.getSets() - 1);
                }
                workout.setWorkoutDuration((timeInMinutes * 60000) - timeLeftInMilli);
                return workout;
            }
        }

        return null;
    }

    private static Exercise getValidExercise(BodyPart bodyPart, List<BodyPart> bodyPartsToAvoid, List<Equipment> equipment)
    {

        int maxRuns = 1000;
        int totalRuns = 0;
        double chanceToAvoidNonLiked = 0.25;
        while (maxRuns > totalRuns)
        {
            Exercise exercise = getRandomExercise(bodyPart);

            boolean exerciseValid = true;
            for (var bodyPartToAvoid : bodyPartsToAvoid)
            {
                if (exercise.bodyParts.contains(bodyPartToAvoid))
                {
                    exerciseValid = false;
                    break;
                }
            }

            exerciseValid &= equipment.containsAll(exercise.equipment);
            exerciseValid &= !selectedExercises.containsKey(exercise.title);
            exerciseValid &= !ExerciseUtils.getDislikedExercises().contains(exercise);

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

    private static Exercise getRandomExercise(BodyPart bodyPart)
    {
        List<Exercise> exercises = Api.getExercisesForBodyPart(bodyPart);

        if (exercises == null || exercises.isEmpty()) {
            throw new IllegalStateException("No exercises available. Ensure the API is reachable and returns data.");
        }

        int randomIndex = (int) (Math.random() * exercises.size());
        return exercises.get(randomIndex);
    }
}
