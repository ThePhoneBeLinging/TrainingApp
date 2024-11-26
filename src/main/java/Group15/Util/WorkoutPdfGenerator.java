package Group15.Util;

import Group15.Model.Workout;
import Group15.Model.WorkoutExercise;
import Group15.View.WorkoutView;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.FileNotFoundException;
import java.net.URL;

public class WorkoutPdfGenerator {
    public static void saveWorkoutAsPdf(Workout workout, String filePath) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph(workout.getName()).setFontSize(40).setBold());
        document.add(new Paragraph(WorkoutView.formatWorkoutDuration(workout.calculateWorkoutDuration())).setFontSize(20).setBold());
        String breakFieldString = Workout.BREAK_BETWEEN_SETS / 1000 + " seconds break between sets" + " | " + Workout.BREAK_BETWEEN_EXERCISES / 1000 + " seconds break between exercises";
        document.add(new Paragraph(breakFieldString).setHorizontalAlignment(HorizontalAlignment.CENTER));

        for (WorkoutExercise workoutExercise : workout.getExercises()) {
            document.add(new Paragraph("Exercise: " + workoutExercise.getExercise().title).setFontSize(16).setBold());
            document.add(new Paragraph("Description: " + workoutExercise.getExercise().description).setFontSize(12));
            document.add(new Paragraph("Body Part: " + workoutExercise.getExercise().makeBodypartsString()).setFontSize(12));
            document.add(new Paragraph("Equipment: " + workoutExercise.getExercise().makeEquipmentString() + " | Difficulty: " + workoutExercise.getExercise().difficulty).setFontSize(12));

            try {
                URL imageUrl = WorkoutView.class.getResource("/images/" + workoutExercise.getExercise().title + ".png");
                if (imageUrl != null) {
                    System.out.println("Loading image from: " + imageUrl.toExternalForm());
                    Image img = new Image(ImageDataFactory.create(imageUrl)).setWidth(100).setHeight(100);
                    document.add(img);
                } else {
                    System.out.println("Image not found for exercise: " + workoutExercise.getExercise().title);
                }
            } catch (Exception e) {
                System.out.println("Could not load image for exercise: " + workoutExercise.getExercise().title);
                e.printStackTrace();
            }

            document.add(new Paragraph("\n"));
        }

        document.close();
    }
}