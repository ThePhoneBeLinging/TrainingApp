package Group15.Util;

import Group15.Model.*;

import java.util.List;

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

            // This is basicly what musti does, might not be optimal
            workoutExercise.setRepsPerSet(5);
            int timePerSet = validExercise.timePerRep * 5;

            while (timeLeftInMilli > 0 && workoutExercise.getSets() < 3)
            {
                timeLeftInMilli -= timePerSet;
                workoutExercise.setSets(workoutExercise.getSets() + 1);
                timeLeftInMilli -= 120;
            }
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

        while (true)
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
