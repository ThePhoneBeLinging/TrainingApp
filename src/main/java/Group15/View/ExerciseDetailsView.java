package Group15.View;

import Group15.Model.Exercise;
import Group15.Model.Workout;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ExerciseDetailsView
{

    public static Scene createScene(Exercise exercise)
    {
        System.out.println("Creating ExerciseDetailsView for: " + exercise.title);

        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane exerciseImagePane = createExerciseImagePane(exercise.imagePath);
        layout.getChildren().add(exerciseImagePane);

        Pane exerciseInfoPane = createExerciseInfoPane(exercise);
        layout.getChildren().add(exerciseInfoPane);

        Pane buttonPane = createButtonPane();
        layout.getChildren().add(buttonPane);

        return new Scene(layout);
    }


    private static Pane createExerciseImagePane(String imagePath)
    {
        VBox imagePane = new VBox();
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setPrefSize(640, 400);

        try
        {
            String fullImagePath = WorkoutView.class.getResource(imagePath).toExternalForm();
            System.out.println("Loading image from: " + fullImagePath);

            Image exerciseImage = new Image(fullImagePath);
            ImageView imageView = new ImageView(exerciseImage);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);

            imagePane.getChildren().add(imageView);

        }
        catch (Exception e)
        {
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }

        return imagePane;
    }

    private static Pane createExerciseInfoPane(Exercise exercise)
    {
        VBox infoPane = new VBox();
        infoPane.setSpacing(10);
        infoPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label(exercise.title);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Label descriptionLabel = new Label(exercise.description);
        descriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(600);

        Label bodyPartLabel = new Label("Body Part: " + exercise.bodyPart);
        bodyPartLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label equipmentLabel = new Label("Equipment needed: " + exercise.equipment);
        equipmentLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        Label difficultyLabel = new Label("Difficulty: " + exercise.difficulty);
        difficultyLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        infoPane.getChildren().addAll(nameLabel, descriptionLabel, bodyPartLabel, equipmentLabel, difficultyLabel);

        return infoPane;
    }

    private static Pane createButtonPane()
    {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);

        Button favoriteButton = new Button("Favorite");
        favoriteButton.setPrefSize(200, 50);
        favoriteButton.setOnAction(_ ->
            {
            //TODO: Add functionality to add exercise to favorites
            });

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        //TODO BACK BUTTON!!
        backButton.setOnAction(_ -> ViewController.setScene(WorkoutView.createScene(new Workout())));

        buttonPane.getChildren().addAll(favoriteButton, backButton);

        return buttonPane;
    }
}