package Group15.View;

import Group15.Model.Exercise;
import Group15.Model.Workout;
import Group15.Model.WorkoutExercise;
import Group15.Util.Api;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SelectNewExerciseView
{
    private static Exercise exerciseToSwap;
    public static Scene createScene(Exercise exercise , Workout workout)
    {
        exerciseToSwap = exercise;
        VBox vBox = new VBox();
        vBox.getChildren().add(createTitlePane());
        vBox.setPrefSize(640,400);
        vBox.setMaxWidth(vBox.getPrefWidth());
        vBox.setMaxHeight(vBox.getPrefHeight());
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(createWorkoutPane(workout));
        return new Scene(vBox);
    }

    private static Pane createTitlePane()
    {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label("Select Exercise");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titlePane.getChildren().add(titleLabel);

        return titlePane;
    }

    private static Node createWorkoutPane(Workout workout)
    {
        VBox workoutPane = new VBox();
        workoutPane.setAlignment(Pos.CENTER);
        workoutPane.setSpacing(20);
        workoutPane.setPrefSize(640, 600);
        workoutPane.setMaxWidth(Region.USE_PREF_SIZE);

        for (Exercise exercise : Api.getAllExercises())
        {
            ImageView imageView = null;

            try
            {
                Image image = new Image(WorkoutView.class.getResource("/images/" + exercise.title + ".png").toExternalForm(), 100, 100, true, true);
                imageView = new ImageView(image);
            }
            catch (Exception e)
            {
                System.out.println("Error loading image for exercise: " + exercise.title);
                imageView = new ImageView();
            }

            Label exerciseLabel1 = new Label(exercise.title + ": ");
            exerciseLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 16));

            EventHandler<MouseEvent> clickAction = event ->
                {
                System.out.println("Image or title clicked for exercise: " + exercise.title);
                ViewController.setScene(ExerciseDetailsView.createScene(exercise));
                };
            HBox exerciseBox = new HBox();
            exerciseBox.setOnMouseClicked(clickAction);
            exerciseBox.setSpacing(10);
            exerciseBox.setAlignment(Pos.CENTER);
            exerciseBox.setBackground(Background.fill(Color.LIGHTGRAY));
            exerciseBox.setPadding(new Insets(10));
            Button chooseExerciseButton = new Button("Choose Exercise");

            chooseExerciseButton.setOnAction(e ->
                {
                    if (exerciseToSwap == null)
                    {
                        var workoutExercise = new WorkoutExercise();
                        workoutExercise.setExercise(exercise);
                        workout.addExercise(workoutExercise);
                    }
                    else
                    {
                        var workoutExercise = new WorkoutExercise();
                        workoutExercise.setExercise(exercise);
                        var otherWorkOutExercise = new WorkoutExercise();
                        otherWorkOutExercise.setExercise(exercise);
                        workout.swapExercise(workoutExercise,otherWorkOutExercise);
                    }
                ViewController.applyChanges(EditWorkoutView.createScene(workout));
                });

            exerciseBox.getChildren().addAll(imageView, exerciseLabel1, chooseExerciseButton);
            workoutPane.getChildren().add(exerciseBox);
        }
        ScrollPane scrollPane = new ScrollPane(workoutPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setPadding(new Insets(10));
        return scrollPane;
    }
}
