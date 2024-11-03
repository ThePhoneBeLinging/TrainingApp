package Group15;

import Group15.WorkoutPdfGenerator;
import Group15.Workout;
import Group15.Api.Exercise;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTestsWorkoutView {

    public void testSaveWorkoutAsPdf() {

        Workout workout = new Workout();
        workout.addExercise(new Exercise("benchPress", "Great for upper body strength", "Strength", "Chest", "None", "Intermediate", "/images/benchPress.png"));
        workout.addExercise(new Exercise("squats", "Essential lower body exercise", "Strength", "Legs", "None", "Beginner", "/images/squats.png"));

        String pdfPath = "test_workout.pdf";

        try {
            WorkoutPdfGenerator.saveWorkoutAsPdf(workout, pdfPath);

            File pdfFile = new File(pdfPath);
            assertTrue(pdfFile.exists(), "PDF file should be created");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            assertTrue(false, "FileNotFoundException was thrown");

        }

    }

}
