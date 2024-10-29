package Group15;

import Group15.Api.Exercise;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.text.Document;
import java.io.FileOutputStream;


public class WorkoutView {
    private  static Workout testWorkout = new Workout();
    private static String title = "Workout";
    private static String[] buttons = {"Back", "Edit Workout", "Save"};
    public static Scene createScene(){
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane titlePane = createTitlePane();
        layout.getChildren().add(titlePane);

        Node WorkoutPane = createWorkoutPane(testWorkout);
        layout.getChildren().add(WorkoutPane);

        Pane buttonPane = createButtonPane();
        layout.getChildren().add(buttonPane);

        return new Scene(layout, 1000, 800);
    }

    private static Pane createTitlePane(){
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titlePane.getChildren().add(titleLabel);

        return titlePane;
    }

    private static Node createWorkoutPane(Workout workout){
        VBox workoutPane = new VBox();
        workoutPane.setAlignment(Pos.CENTER);
        workoutPane.setSpacing(20);
        workoutPane.setPrefSize(640, 600);
        workoutPane.setMaxWidth(Region.USE_PREF_SIZE);

        for (Exercise exercise : workout.getExercises()){
            ImageView imageView = null;

            try{
                Image image = new Image(WorkoutView.class.getResource("/images/" + exercise.title + ".png").toExternalForm(), 100, 100, true, true);
                imageView = new ImageView(image);
            } catch (Exception e) {
                System.out.println("Error loading image for exercise: " + exercise.title);
                e.printStackTrace();
                imageView = new ImageView();
            }

            Label exerciseLabel1 = new Label(exercise.title + ": ");
            exerciseLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 16));

            Label exerciseLabel2 = new Label(exercise.description);
            exerciseLabel2.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

            EventHandler<MouseEvent> clickAction = event -> {
                System.out.println("Image or title clicked for exercise: " + exercise.title);
                Scene currentScene = ViewController.getScene();
                Scene exerciseDetailsScene = ExerciseDetailsView.createScene(exercise);
                ViewController.setScene(exerciseDetailsScene);
            };

            imageView.setOnMouseClicked(clickAction);
            exerciseLabel1.setOnMouseClicked(clickAction);



            HBox exerciseBox = new HBox();
            exerciseBox.setSpacing(10);
            exerciseBox.setAlignment(Pos.CENTER_LEFT);
            exerciseBox.setBackground(Background.fill(Color.LIGHTGRAY));
            exerciseBox.setPadding(new Insets(10));

            exerciseBox.getChildren().addAll(imageView, exerciseLabel1, exerciseLabel2);

            workoutPane.getChildren().add(exerciseBox);
        }

        ScrollPane  scrollPane = new ScrollPane(workoutPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setPadding(new Insets(10));

        return scrollPane;
    }

    private static Pane createButtonPane(){
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);

        for (String button : buttons){
            Button newButton = new Button(button);
            newButton.setPrefSize(200, 50);
            newButton.setOnAction(_ -> {
                switch (button){
                    case "Back" -> ViewController.setScene(HomeScreenView.createScene());
                    case "Edit Workout" -> {
                        testWorkout.addExercise(new Exercise("benchPress", "Push-ups are a great upper body exercise.", "Strength", "Chest", "None", "Intermediate", "/images/benchPress.png"));
                        testWorkout.addExercise(new Exercise("benchPress", "Squats are a fundamental lower body exercise.", "Strength", "Legs", "None", "Beginner", "/images/benchPress.png"));
                        ViewController.setScene(WorkoutView.createScene());
                    }
                    case "Save" -> System.out.println("Save Pressed");
                }
            });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

}
