package Group15;

import Group15.Api.Exercise;

import ch.qos.logback.core.Layout;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class WorkoutView {

    private static String title = "Workout";
    private static String[] buttons = {"Back", "Edit Workout", "Save As PDF"};
    public static Scene createScene(){
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane titlePane = createTitlePane();
        layout.getChildren().add(titlePane);

        Pane WorkoutPane = createWorkoutPane(workout);
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

    private static Pane createWorkoutPane(Workout workout){
        VBox workoutPane = new VBox();
        workoutPane.setAlignment(Pos.CENTER);
        workoutPane.setSpacing(20);
        workoutPane.setPrefSize(640, 600);
        workoutPane.setMaxWidth(Region.USE_PREF_SIZE);

        workoutPane.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));

        for (Exercise exercise : workout.getExercises()){
            Label exerciseLabel = new Label(exercise.title);
            workoutPane.getChildren().add(exerciseLabel);
        }

        return workoutPane;
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
                    case "Edit Workout" -> System.out.println("Edit Workout Pressed");
                    case "Save As PDF" -> System.out.println("Save As PDF Pressed");
                }
            });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

}
