package Group15.View;

import Group15.Model.Workout;
import Group15.Util.WorkoutUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HomeScreenView {

    private static final String title = "Workout App";
    private static final String[] buttons = {"New Workout", "Support the Developers", "Exit"}; //TODO: Find out what to do with middle button

    public static Scene createScene() {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));

        Pane titlePane = createTitlePane();
        titlePane.setPadding(new Insets(10));
        layout.setTop(titlePane);

        Pane quickWorkoutPane = quickWorkoutPane();
        layout.setCenter(quickWorkoutPane);

        VBox bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        Pane buttonPane = createButtonPane();
        buttonPane.setPadding(new Insets(20, 0, 0, 0));
        bottomPane.getChildren().addAll(buttonPane, legalLabel());
        layout.setBottom(bottomPane);

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
        ScrollPane savedWorkoutsPane = createWorkoutsPane("Saved");
        ScrollPane popularWorkoutsPane = createWorkoutsPane("Popular");
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
                    case "Support the Developers":
                        openPayPalLink();
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

    private static ScrollPane createWorkoutsPane(String workoutsToShow) {
        VBox workoutsPane = new VBox();
        workoutsPane.setPrefSize(320, 600);
        workoutsPane.setMaxWidth(Region.USE_PREF_SIZE);
        workoutsPane.setSpacing(10);
        workoutsPane.setPadding(new Insets(10));
        workoutsPane.setAlignment(Pos.TOP_CENTER);
        workoutsPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        Label title = new Label(workoutsToShow + " Workouts");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        workoutsPane.getChildren().add(title);


        List<Workout> workouts;

        if ("Saved".equals(workoutsToShow)) {
            workouts = WorkoutUtils.getSavedWorkouts();
        } else {
            workouts = WorkoutUtils.getPopularWorkouts();
        }

        for (Workout workout : workouts) {
            VBox workoutItem = new VBox();
            workoutItem.setPrefSize(300, 80);
            workoutItem.setMaxWidth(Region.USE_PREF_SIZE);
            workoutItem.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

            Label titleWorkout = new Label(workout.getName());
            titleWorkout.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Label descriptionWorkout = new Label(workout.makeDescription());
            descriptionWorkout.setWrapText(true);
            workoutItem.getChildren().addAll(titleWorkout, descriptionWorkout);

            workoutItem.onMouseClickedProperty().set(_ -> ViewController.setScene(WorkoutView.createScene(workout)));
            workoutsPane.getChildren().add(workoutItem);
        }

        ScrollPane scrollPane = new ScrollPane(workoutsPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(600);

        return scrollPane;
    }

    private static void openPayPalLink() {
        String url = "https://paypal.me/FBastrup?country.x=DK&locale.x=da_DK";
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported. Cannot open PayPal link.");
        }
    }

    private static Label legalLabel() {
        Label legalLabel = new Label("We are not responsible for any injuries you may sustain while using the app.");
        legalLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        legalLabel.setWrapText(true);
        legalLabel.setAlignment(Pos.CENTER);
        legalLabel.setPadding(new Insets(10));
        return legalLabel;
    }
}