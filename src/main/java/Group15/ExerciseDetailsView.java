package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class ExerciseDetailsView {

    private static String exerciseName = "Exercise Name";
    private static String exerciseDescription = "Description of the exercise will go here.";
    private static String exerciseEquipment = "Equipment needed";

    public static Scene createScene() {
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().add(createExerciseImagePane());
        layout.getChildren().add(createExerciseInfoPane());
        layout.getChildren().add(createButtonPane());

        return new Scene(layout, 1000, 800);
    }

    private static VBox createExerciseImagePane() {
        VBox imagePane = new VBox();
        imagePane.setAlignment(Pos.CENTER);
        return imagePane;
    }

    private static VBox createExerciseInfoPane() {
        VBox infoPane = new VBox();
        infoPane.setSpacing(10);
        infoPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(exerciseName);
        Label descriptionLabel = new Label(exerciseDescription);
        Label equipmentLabel = new Label("Equipment needed: " + exerciseEquipment);

        infoPane.getChildren().addAll(nameLabel, descriptionLabel, equipmentLabel);

        return infoPane;
    }

    private static VBox createButtonPane() {
        VBox buttonPane = new VBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);

        Button favoriteButton = new Button("Favorite");
        Button backButton = new Button("Back");

        buttonPane.getChildren().addAll(favoriteButton, backButton);

        return buttonPane;
    }
}