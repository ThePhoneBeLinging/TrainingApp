package Group15.Util;

import Group15.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutAlgorithm {

    private static HashMap<String, Boolean> selectedExercises;
    private static int MAX_SETS = 3;
    private static int REPS_PER_SET = 5;

    public static Workout createWorkoutFromExercises(
            List<BodyPart> selectedBodyParts,
            List<BodyPart> bodyPartsToAvoid,
            List<Equipment> equipment,
            int timeInMinutes){
        selectedExercises = new HashMap<>();
        int timeInMilli = timeInMinutes * 60000;
        Workout workout = new Workout();
        int bodyPartIndex = 0;
        int nullExercisesInARow = 0;

        while (workout.getWorkoutDuration() <= timeInMilli)
        {
            Exercise validExercise = getValidExercise(selectedBodyParts.get(bodyPartIndex), bodyPartsToAvoid, equipment);
            bodyPartIndex = (bodyPartIndex + 1) % selectedBodyParts.size();
            if (validExercise == null)
            {
                nullExercisesInARow++;
                if (nullExercisesInARow == selectedBodyParts.size())
                {
                    return workout;
                }
                continue;
            }
            nullExercisesInARow = 0;
            selectedExercises.put(validExercise.title,true);

            WorkoutExercise workoutExercise = new WorkoutExercise();
            workoutExercise.setExercise(validExercise);
            workoutExercise.setRepsPerSet(REPS_PER_SET);
            workoutExercise.setSets(MAX_SETS);

            workout.addExercise(workoutExercise);

            while (workout.getWorkoutDuration() >= timeInMilli)
            {
                if (workoutExercise.getSets() == 1)
                {
                    workout.removeExercise(workoutExercise);
                }
                else
                {
                    workoutExercise.setSets(workoutExercise.getSets() - 1);
                }

                if (workout.getWorkoutDuration() < timeInMilli)
                {
                    return workout;
                }
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