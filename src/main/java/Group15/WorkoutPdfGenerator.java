package Group15;
import Group15.Api.Exercise;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

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
                URL imageUrl = WorkoutView.class.getResource(exercise.imagePath);
                if (imageUrl != null) {
                    System.out.println("Loading image from: " + imageUrl.toExternalForm());
                    Image img = new Image(ImageDataFactory.create(imageUrl)).setWidth(100).setHeight(100);
                    document.add(img);
                } else {
                    System.out.println("Image not found for exercise: " + exercise.title);
                }
            } catch (Exception e) {
                System.out.println("Could not load image for exercise: " + exercise.title);
                e.printStackTrace();
            }

            document.add(new Paragraph("\n"));
        }

        document.close();
    }
}

