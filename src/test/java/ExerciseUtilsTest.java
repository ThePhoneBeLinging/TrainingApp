import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Util.ExerciseUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseUtilsTest {

    Exercise pushup = new Exercise("Pushup", "Push your body up and down", "Strength", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);

    @BeforeEach
    public void setUp() {
        ExerciseUtils.init();
    }

    @Test
    public void testInit() {
        List<Exercise> likedExercises = ExerciseUtils.getLikedExercises();
        List<Exercise> dislikedExercises = ExerciseUtils.getDislikedExercises();
        assertNotNull(likedExercises);
        assertNotNull(dislikedExercises);
    }

    @Test
    public void testLikeExercise() {
        ExerciseUtils.likeExercisePressed(pushup);
        assertTrue(ExerciseUtils.getLikedExercises().contains(pushup));
        assertFalse(ExerciseUtils.getDislikedExercises().contains(pushup));
    }

    @Test
    public void testDislikeExercise() {
        ExerciseUtils.dislikeExercisePressed(pushup);
        assertTrue(ExerciseUtils.getDislikedExercises().contains(pushup));
        assertFalse(ExerciseUtils.getLikedExercises().contains(pushup));
    }

    @Test
    public void testGetLikedExercises() {
        ExerciseUtils.likeExercisePressed(pushup);
        List<Exercise> likedExercises = ExerciseUtils.getLikedExercises();
        assertTrue(likedExercises.contains(pushup));
    }

    @Test
    public void testGetDislikedExercises() {
        ExerciseUtils.dislikeExercisePressed(pushup);
        List<Exercise> dislikedExercises = ExerciseUtils.getDislikedExercises();
        assertTrue(dislikedExercises.contains(pushup));
    }

    @Test
    public void testWriteToFile() {
        ExerciseUtils.likeExercisePressed(pushup);
        assertTrue(ExerciseUtils.getLikedExercises().contains(pushup));
    }
}
