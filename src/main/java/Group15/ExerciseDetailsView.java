package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExerciseDetailsView {

    private static String exerciseName = "Bench Press";
    private static String exerciseDescription = "This is a description of bench press. It is a compound exercise that works multiple muscle groups including the chest, shoulders, and triceps. Just press Bro!";
    private static String exerciseEquipment = "Bench, Barbell";

    public static Scene createScene() {
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane exerciseImagePane = createExerciseImagePane();
        layout.getChildren().add(exerciseImagePane);

        Pane exerciseInfoPane = createExerciseInfoPane();
        layout.getChildren().add(exerciseInfoPane);

        Pane buttonPane = createButtonPane();
        layout.getChildren().add(buttonPane);

        return new Scene(layout, 1000, 800);
    }

    private static Pane createExerciseImagePane() {
        VBox imagePane = new VBox();
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setPrefSize(640, 400);

        Image exerciseImage = new Image("file:src/main/resources/benchPress.png");
        ImageView imageView = new ImageView(exerciseImage);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        imagePane.getChildren().add(imageView);
        return imagePane;
    }

    private static Pane createExerciseInfoPane() {
        VBox infoPane = new VBox();
        infoPane.setSpacing(10);
        infoPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(exerciseName);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Label descriptionLabel = new Label(exerciseDescription);
        descriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(600);

        Label equipmentLabel = new Label("Equipment needed: " + exerciseEquipment);
        equipmentLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        infoPane.getChildren().addAll(nameLabel, descriptionLabel, equipmentLabel);

        return infoPane;
    }

    private static Pane createButtonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);

        Button favoriteButton = new Button("Favorite");
        favoriteButton.setPrefSize(200, 50);
        favoriteButton.setOnAction(e -> {
            //TODO: Add functionality to add exercise to favorites
            System.out.println("Added to Favorites");
        });

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setOnAction(e -> {
            //TODO: add functionality to go back to previous screen, eg. ExerciseListView or WorkoutView
            System.out.println("Back to previous screen");
        });

        buttonPane.getChildren().addAll(favoriteButton, backButton);

        return buttonPane;
    }
}