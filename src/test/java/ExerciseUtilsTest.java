import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import Group15.Util.ExerciseUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseUtilsTest {

    private static final String LIKED_FILE_PATH = "src/main/resources/userData/likedExercises.json";
    private static final String DISLIKED_FILE_PATH = "src/main/resources/userData/dislikedExercises.json";
    private static final String LIKED_FILE_BACKUP_PATH = "src/main/resources/userData/likedExercises_backup.json";
    private static final String DISLIKED_FILE_BACKUP_PATH = "src/main/resources/userData/dislikedExercises_backup.json";

    Exercise pushup = new Exercise("Pushup", "Push your body up and down", Collections.singletonList(BodyPart.Chest), Collections.singletonList(Equipment.Bodyweight), "Easy", "None", 3000);

    @BeforeEach
    public void setUp() throws IOException {
        backupFile(LIKED_FILE_PATH, LIKED_FILE_BACKUP_PATH);
        backupFile(DISLIKED_FILE_PATH, DISLIKED_FILE_BACKUP_PATH);

        ExerciseUtils.init();
    }

    @AfterEach
    public void restoreFilesToOriginal() throws IOException {
        restoreFile(LIKED_FILE_BACKUP_PATH, LIKED_FILE_PATH);
        restoreFile(DISLIKED_FILE_BACKUP_PATH, DISLIKED_FILE_PATH);
    }

    private void backupFile(String sourcePath, String backupPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File backupFile = new File(backupPath);
        if (sourceFile.exists()) {
            Files.copy(sourceFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void restoreFile(String backupPath, String sourcePath) throws IOException {
        File backupFile = new File(backupPath);
        File sourceFile = new File(sourcePath);
        if (backupFile.exists()) {
            Files.copy(backupFile.toPath(), sourceFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            backupFile.delete();
        } else {
            sourceFile.delete();
        }
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