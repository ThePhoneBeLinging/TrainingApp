package Group15;
import Group15.Api.Exercise;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.FileNotFoundException;

public class WorkoutPdfGenerator {
    public static void saveWorkoutAsPdf(Workout workout, String filePath) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Workout Plan").setFontSize(20).setBold());

        for (Exercise exercise : workout.getExercises()) {
            document.add(new Paragraph("Exercise: " + exercise.title).setFontSize(16).setBold());
            document.add(new Paragraph("Description: " + exercise.description).setFontSize(12));
            document.add(new Paragraph("Type: " + exercise.type + " | Body Part: " + exercise.bodyPart).setFontSize(12));
            document.add(new Paragraph("Equipment: " + exercise.equipment + " | Difficulty: " + exercise.difficulty).setFontSize(12));

            try {
                String imagePath = WorkoutView.class.getResource(exercise.imagePath).toExternalForm();
                Image img = new Image(ImageDataFactory.create(imagePath)).setWidth(100).setHeight(100);
                document.add(img);
            } catch (Exception e) {
                System.out.println("Could not load image for exercise: " + exercise.title);
            }

            document.add(new Paragraph("\n"));
        }

        document.close();
    }
}

