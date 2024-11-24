package Group15.View;

import Group15.MockData.MockWorkouts;
import Group15.Model.Workout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class HomeScreenView {

    private static final String title = "Workout App";
    private static final String[] buttons = {"New Workout", "Do smthn", "Exit"}; //TODO: Find out what to do with middle button

    public static Scene createScene() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));

        Pane titlePane = createTitlePane();
        titlePane.setPadding(new Insets(10));
        layout.setTop(titlePane);

        Pane quickWorkoutPane = quickWorkoutPane();
        layout.setCenter(quickWorkoutPane);

        Pane buttonPane = createButtonPane();
        buttonPane.setPadding(new Insets(20, 0, 0, 0));
        layout.setBottom(buttonPane);

        return new Scene(layout);
    }

    private static Pane createTitlePane() {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titlePane.getChildren().add(titleLabel);

        return titlePane;
    }

    private static Pane quickWorkoutPane() {
        HBox quickWorkoutPane = new HBox();
        quickWorkoutPane.setSpacing(20);
        quickWorkoutPane.setAlignment(Pos.CENTER);
        quickWorkoutPane.setPrefSize(640, 600);
        quickWorkoutPane.setMaxWidth(Region.USE_PREF_SIZE);
        Pane savedWorkoutsPane = createWorkoutsPane("Saved");
        Pane popularWorkoutsPane = createWorkoutsPane("Popular");
        quickWorkoutPane.getChildren().addAll(savedWorkoutsPane, popularWorkoutsPane);

        return quickWorkoutPane;
    }

    private static Pane createButtonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);

        for (String button : buttons) {
            Button newButton = new Button(button);
            newButton.setPrefSize(200, 50);
            newButton.setOnAction(_ -> {
                switch (button) {
                    case "New Workout":
                        ViewController.setScene(MuscleSelectionView.createMuscleSelectionScene());
                        break;
                    case "Do smthn":
                        //TODO: Implement this button
                        break;
                    case "Exit":
                        System.exit(0);
                }
            });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

    private static Pane createWorkoutsPane(String workoutsToShow) {
        VBox workoutsPane = new VBox();
        workoutsPane.setPrefSize(320, 600);
        workoutsPane.setMaxWidth(Region.USE_PREF_SIZE);
        workoutsPane.setSpacing(10);
        workoutsPane.setAlignment(Pos.TOP_CENTER);
        workoutsPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        Label title = new Label(workoutsToShow + " Workouts");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        workoutsPane.getChildren().add(title);

        // TODO: here we would fetch the workouts and put them into a list, but for now we just fetch mock data
        MockWorkouts mockWorkouts = new MockWorkouts();
        List<Workout> workouts = mockWorkouts.getWorkouts();

        for (Workout workout : workouts) {
            VBox workoutItem = new VBox();
            workoutItem.setPrefSize(300, 60);
            workoutItem.setMaxWidth(Region.USE_PREF_SIZE);
            workoutItem.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

            Label titleWorkout = new Label(workout.getName());
            titleWorkout.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Label descriptionWorkout = new Label(workout.getDescription());
            workoutItem.getChildren().addAll(titleWorkout, descriptionWorkout);


            // In addition to that we should not navigate to the workoutView on the new workout button.
            // We can change this when mr. Musti finally merges his branch
            workoutItem.onMouseClickedProperty().set(_ -> ViewController.setScene(WorkoutView.createScene(new Workout())));
            workoutsPane.getChildren().add(workoutItem);
        }

        return workoutsPane;
    }

}