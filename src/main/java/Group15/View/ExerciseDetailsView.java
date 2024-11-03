package Group15.View;

import Group15.Model.Exercise;
import Group15.Util.ExerciseUtils;
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
    //TODO: Add a proper placement of these files
    private static final String FAVORITES_FILE = "favorites.dat";
    private static final String DISLIKED_FILE = "disliked.dat";

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

        Pane buttonPane = createButtonPane(exercise);
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

    private static Pane createButtonPane(Exercise exercise) {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);

        Button likeButton = new Button("Like");
        likeButton.setPrefSize(200, 50);

        Button dislikeButton = new Button("Dislike");
        dislikeButton.setPrefSize(200, 50);

        if (ExerciseUtils.isExerciseInFile(exercise, FAVORITES_FILE)) {
            likeButton.setStyle("-fx-background-color: #00ff00;");
        }
        likeButton.setOnAction(_ -> {
            ExerciseUtils.addExerciseToFile(exercise, FAVORITES_FILE);
            ExerciseUtils.removeExerciseFromFile(exercise, DISLIKED_FILE);
            likeButton.setStyle("-fx-background-color: #00ff00;");
            dislikeButton.setStyle(""); // Remove highlight
        });

        if (ExerciseUtils.isExerciseInFile(exercise, DISLIKED_FILE)) {
            dislikeButton.setStyle("-fx-background-color: #ff0000;");
        }
        dislikeButton.setOnAction(_ -> {
            ExerciseUtils.removeExerciseFromFile(exercise, FAVORITES_FILE);
            ExerciseUtils.addExerciseToFile(exercise, DISLIKED_FILE);
            dislikeButton.setStyle("-fx-background-color: #ff0000;");
            likeButton.setStyle(""); // Remove highlight
        });

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setOnAction(_ -> ViewController.setScene(WorkoutView.createScene()));

        buttonPane.getChildren().addAll(likeButton, dislikeButton, backButton);

        return buttonPane;
    }
}