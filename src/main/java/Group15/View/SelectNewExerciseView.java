package Group15.View;

import Group15.Api.ApiUtils;
import Group15.Model.Exercise;
import Group15.Model.Workout;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        vBox.getChildren().add(createWorkoutPane(workout));
        vBox.setAlignment(Pos.CENTER);
        return new Scene(vBox);
    }

    private static Node createWorkoutPane(Workout workout)
    {
        VBox workoutPane = new VBox();
        workoutPane.setAlignment(Pos.CENTER);
        workoutPane.setSpacing(20);
        workoutPane.setPrefSize(640, 600);
        workoutPane.setMaxWidth(Region.USE_PREF_SIZE);

        for (Exercise exercise : ApiUtils.getAllExercises())
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

            Label exerciseLabel2 = new Label(exercise.description);
            exerciseLabel2.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

            EventHandler<MouseEvent> clickAction = event ->
                {
                System.out.println("Image or title clicked for exercise: " + exercise.title);
                ViewController.setScene(ExerciseDetailsView.createScene(exercise));
                };

            imageView.setOnMouseClicked(clickAction);
            exerciseLabel1.setOnMouseClicked(clickAction);


            HBox exerciseBox = new HBox();
            exerciseBox.setSpacing(10);
            exerciseBox.setAlignment(Pos.CENTER_LEFT);
            exerciseBox.setBackground(Background.fill(Color.LIGHTGRAY));
            exerciseBox.setPadding(new Insets(10));
            Button chooseExerciseButton = new Button("Choose Exercise");

            chooseExerciseButton.setOnAction(e ->
                {
                    if (exerciseToSwap == null)
                    {
                        workout.addExercise(exercise);
                    }
                    else
                    {
                        workout.swapExercise(exerciseToSwap,exercise);
                    }
                ViewController.setScene(EditWorkoutView.createScene(workout));
                });

            exerciseBox.getChildren().addAll(imageView, exerciseLabel1, exerciseLabel2, chooseExerciseButton);
            workoutPane.getChildren().add(exerciseBox);
        }
        return new ScrollPane(workoutPane);
    }
}
