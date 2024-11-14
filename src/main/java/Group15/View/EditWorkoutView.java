package Group15.View;

import Group15.Model.Exercise;
import Group15.Model.Workout;
import Group15.Model.WorkoutExercise;
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


public class EditWorkoutView
{
    private static final String[] buttons = {"Cancel", "Add Exercise", "Apply Changes"};

    public static Scene createScene(Workout workout)
    {
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane titlePane = createTitlePane();
        layout.getChildren().add(titlePane);

        Node workoutPane = createWorkoutPane(workout);
        layout.getChildren().add(workoutPane);

        Pane buttonPane = createButtonPane(workout);
        layout.getChildren().add(buttonPane);

        return new Scene(layout);
    }

    private static Pane createTitlePane()
    {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label("Edit Workout");
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

        for (WorkoutExercise workoutExercise : workout.getExercises())
        {
            ImageView imageView = null;

            try
            {
                Image image = new Image(WorkoutView.class.getResource("/images/" + workoutExercise.getExercise().title + ".png").toExternalForm(), 100, 100, true, true);
                imageView = new ImageView(image);
            }
            catch (Exception e)
            {
                System.out.println("Error loading image for exercise: " + workoutExercise.getExercise().title);
                imageView = new ImageView();
            }

            Label exerciseLabel1 = new Label(workoutExercise.getExercise().title + ": ");
            exerciseLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 16));

            EventHandler<MouseEvent> clickAction = event ->
                {
                System.out.println("Image or title clicked for exercise: " + workoutExercise.getExercise().title);
                ViewController.setScene(ExerciseDetailsView.createScene(workoutExercise.getExercise()));
                };


            HBox exerciseBox = new HBox();
            exerciseBox.setOnMouseClicked(clickAction);
            exerciseBox.setSpacing(10);
            exerciseBox.setAlignment(Pos.CENTER_LEFT);
            exerciseBox.setBackground(Background.fill(Color.LIGHTGRAY));
            exerciseBox.setPadding(new Insets(10));


            //
            // THE FOLLOWING SHOULD NOT BE UPDATED CASUALLY
            //
            Button deleteExerciseButton = new Button("Delete Exercise");
            Button swapExerciseButton = new Button("Swap Exercise");

            deleteExerciseButton.setOnAction(e ->
                {
                    workout.removeExercise(workoutExercise);
                    ViewController.setScene(EditWorkoutView.createScene(workout));
                });
            swapExerciseButton.setOnAction(e ->
                {
                   ViewController.setScene(SelectNewExerciseView.createScene(workoutExercise.getExercise(),workout));
                });
            //
            //
            //
            exerciseBox.getChildren().addAll(imageView, exerciseLabel1,swapExerciseButton,deleteExerciseButton);

            workoutPane.getChildren().add(exerciseBox);
        }

        ScrollPane scrollPane = new ScrollPane(workoutPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setPadding(new Insets(10));

        return scrollPane;
    }

    private static Pane createButtonPane(Workout workout)
    {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);

        for (String button : buttons)
        {
            Button newButton = new Button(button);
            newButton.setPrefSize(200, 50);
            newButton.setOnAction(_ ->
                {
                switch (button)
                {
                    //TODO This should either be a copy of the workout object, or use more advanced navigation :)
                    case "Cancel":
                    {
                        ViewController.goBack();
                        break;
                    }
                    case "Add Exercise":
                    {
                        ViewController.setScene(SelectNewExerciseView.createScene(null,workout));
                        break;
                    }
                    case "Apply Changes":
                    {
                        ViewController.applyChanges(WorkoutView.createScene(workout));
                        break;
                    }
                }
                });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

}
