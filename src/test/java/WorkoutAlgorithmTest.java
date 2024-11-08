import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Util.WorkoutAlgorithm;
import Group15.Model.Workout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutAlgorithmTest
{

    @BeforeAll
    public static void setUp()
    {
        List<Exercise> mockExercises = new ArrayList<>();

        Exercise pushUp = new Exercise();
        pushUp.title = "Push Up";
        pushUp.description = "Push-up exercise";
        pushUp.bodyPart = Collections.singletonList(BodyPart.Chest);
        pushUp.equipment = Collections.singletonList(Equipment.Bodyweight);
        pushUp.difficulty = "beginner";
        pushUp.timePerRep = 2000;

        Exercise benchPress = new Exercise();
        benchPress.title = "Bench Press";
        benchPress.description = "Bench press exercise";
        benchPress.bodyPart = Collections.singletonList(BodyPart.Chest);
        benchPress.equipment = Collections.singletonList(Equipment.Barbell);
        benchPress.difficulty = "intermediate";
        benchPress.timePerRep = 3000;

        Exercise squat = new Exercise();
        squat.title = "Squat";
        squat.description = "Squat exercise";
        squat.bodyPart = Collections.singletonList(BodyPart.Quadriceps);
        squat.equipment = Collections.singletonList(Equipment.Bodyweight);
        squat.difficulty = "beginner";
        squat.timePerRep = 2500;

        Exercise bicepCurl = new Exercise();
        bicepCurl.title = "Bicep Curl";
        bicepCurl.description = "Bicep curl exercise";
        bicepCurl.bodyPart = Collections.singletonList(BodyPart.Biceps);
        bicepCurl.equipment = Collections.singletonList(Equipment.Dumbbell);
        bicepCurl.difficulty = "beginner";
        bicepCurl.timePerRep = 1500;

        Exercise legExtension = new Exercise();
        legExtension.title = "Leg Extension";
        legExtension.description = "Leg extension exercise";
        legExtension.bodyPart = List.of(BodyPart.Quadriceps, BodyPart.Adductors, BodyPart.Abductors);
        legExtension.equipment = List.of(Equipment.Machine, Equipment.Cable);
        legExtension.difficulty = "beginner";
        legExtension.timePerRep = 3000;

        mockExercises.add(pushUp);
        mockExercises.add(benchPress);
        mockExercises.add(squat);
        mockExercises.add(bicepCurl);
        mockExercises.add(legExtension);
    }

    @Test
    public void testCreateWorkoutWithValidInputs()
    {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList(BodyPart.Chest),
                Collections.emptyList(),
                Collections.singletonList(Equipment.Bodyweight),
                10
        );

        Assertions.assertNotNull(workout);
        Assertions.assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertEquals(Collections.singletonList(BodyPart.Chest), exercise.bodyPart);
            Assertions.assertEquals(Collections.singletonList(Equipment.Bodyweight), exercise.equipment);
            });
    }

    @Test
    public void testCreateWorkoutWithLimitedTime()
    {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList(BodyPart.Biceps),
                Collections.emptyList(),
                Collections.singletonList(Equipment.Dumbbell),
                2
        );

        Assertions.assertNotNull(workout);
        Assertions.assertTrue(workout.getExercises().size() <= 1);
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertEquals(Collections.singletonList(BodyPart.Biceps), exercise.bodyPart);
            Assertions.assertEquals(Collections.singletonList(Equipment.Dumbbell), exercise.equipment);
            });
    }

    @Test
    public void testCreateWorkoutWithDifferentBodyPart()
    {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList(BodyPart.Quadriceps),
                Collections.emptyList(),
                Collections.singletonList(Equipment.Bodyweight),
                5
        );

        Assertions.assertNotNull(workout);
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertEquals(Collections.singletonList(BodyPart.Quadriceps), exercise.bodyPart);
            Assertions.assertEquals(Collections.singletonList(Equipment.Bodyweight), exercise.equipment);
            });
    }

    @Test
    public void testCreateWorkoutWithAllEquipment()
    {
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                Collections.singletonList(BodyPart.Chest),
                Collections.emptyList(),
                List.of(Equipment.Bodyweight, Equipment.Barbell),
                15
        );

        Assertions.assertNotNull(workout);
        Assertions.assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertEquals(Collections.singletonList(BodyPart.Chest), exercise.bodyPart);
            Assertions.assertTrue(exercise.equipment.contains(Equipment.Bodyweight) || exercise.equipment.contains(Equipment.Barbell));
            });
    }

    @Test
    public void testCreateWorkoutWithMultipleBodyParts()
    {
        List<BodyPart> bodyParts = List.of(BodyPart.Chest, BodyPart.Quadriceps, BodyPart.Biceps);
        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                Collections.emptyList(),
                List.of(Equipment.Bodyweight, Equipment.Barbell, Equipment.Dumbbell),
                20
        );

        Assertions.assertNotNull(workout);
        Assertions.assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertTrue(bodyParts.contains(exercise.bodyPart.getFirst()));
            Assertions.assertTrue(exercise.equipment.contains(Equipment.Bodyweight) || exercise.equipment.contains(Equipment.Barbell) || exercise.equipment.contains(Equipment.Dumbbell));
            });
    }

    @Test
    public void testCreateWorkoutWithDislikedBodyParts()
    {
        List<BodyPart> bodyParts = List.of(BodyPart.Chest, BodyPart.Quadriceps, BodyPart.Biceps);
        List<BodyPart> dislikedBodyParts = Collections.singletonList(BodyPart.Quadriceps);

        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                dislikedBodyParts,
                List.of(Equipment.Bodyweight, Equipment.Barbell),
                20
        );

        Assertions.assertNotNull(workout);
        Assertions.assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise -> Assertions.assertFalse(dislikedBodyParts.contains(exercise.bodyPart.getFirst())));
    }

    @Test
    public void testMultipleBodyPartsAndEquipment()
    {
        List<BodyPart> bodyParts = List.of(BodyPart.Quadriceps, BodyPart.Chest);
        List<Equipment> equipments = List.of(Equipment.Machine, Equipment.Bodyweight);

        Workout workout = WorkoutAlgorithm.createWorkoutFromExercises(
                bodyParts,
                Collections.emptyList(),
                equipments,
                10
        );

        Assertions.assertNotNull(workout);
        Assertions.assertFalse(workout.getExercises().isEmpty());
        workout.getExercises().forEach(exercise ->
            {
            Assertions.assertTrue(equipments.contains(exercise.equipment.getFirst()));
            Assertions.assertTrue(bodyParts.contains(exercise.bodyPart.getFirst()));
            });
    }
}
