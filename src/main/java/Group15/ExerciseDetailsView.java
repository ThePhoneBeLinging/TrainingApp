package Group15;

import Group15.Api.Exercise;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExerciseDetailsView {

    public static Scene createScene(Exercise exercise, Stage stage, Scene previousScene) {
        System.out.println("Creating ExerciseDetailsView for: " + exercise.title);

        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane exerciseImagePane = createExerciseImagePane(exercise.imagePath);
        layout.getChildren().add(exerciseImagePane);

        Pane exerciseInfoPane = createExerciseInfoPane(exercise);
        layout.getChildren().add(exerciseInfoPane);

        Pane buttonPane = createButtonPane(stage, previousScene);
        layout.getChildren().add(buttonPane);

        return new Scene(layout, 1000, 800);
    }


    private static Pane createExerciseImagePane(String imagePath) {
        VBox imagePane = new VBox();
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setPrefSize(640, 400);

        try {
            String fullImagePath = WorkoutView.class.getResource(imagePath).toExternalForm();
            System.out.println("Loading image from: " + fullImagePath);

            Image exerciseImage = new Image(fullImagePath);
            ImageView imageView = new ImageView(exerciseImage);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);

            imagePane.getChildren().add(imageView);

        } catch (Exception e){
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }

        return imagePane;
    }

    private static Pane createExerciseInfoPane(Exercise exercise) {
        VBox infoPane = new VBox();
        infoPane.setSpacing(10);
        infoPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(exercise.title);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Label descriptionLabel = new Label(exercise.description);
        descriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(600);

        Label typeLabel = new Label("Type: " + exercise.type);
        typeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label bodyPartLabel = new Label("Body Part: " + exercise.bodyPart);
        bodyPartLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label equipmentLabel = new Label("Equipment needed: " + exercise.equipment);
        equipmentLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label difficultyLabel = new Label("Difficulty: " + exercise.difficulty);
        difficultyLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        infoPane.getChildren().addAll(nameLabel, descriptionLabel, typeLabel, bodyPartLabel, equipmentLabel, difficultyLabel);

        return infoPane;
    }

    private static Pane createButtonPane(Stage stage, Scene previousScene) {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);

        Button favoriteButton = new Button("Favorite");
        favoriteButton.setPrefSize(200, 50);
        favoriteButton.setOnAction(e -> {
            //TODO: Add functionality to add exercise to favorites
        });

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setOnAction(e -> {
            stage.setScene(previousScene);
        });

        buttonPane.getChildren().addAll(favoriteButton, backButton);

        return buttonPane;
    }
}