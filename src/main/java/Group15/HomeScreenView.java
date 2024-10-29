package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreenView {

    private static String title = "Workout App";
    private static String[] buttons = {"New Workout", "My Workouts", "Exit"};

    public static Scene createScene() {
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Pane titlePane = createTitlePane();
        layout.getChildren().add(titlePane);

        Pane quickWorkoutPane = quickWorkoutPane();
        layout.getChildren().add(quickWorkoutPane);

        Pane buttonPane = createButtonPane();
        layout.getChildren().add(buttonPane);

        return new Scene(layout, 1000, 800);
    }

    private static Pane createTitlePane() {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titlePane.getChildren().add(titleLabel);

        return titlePane;
    }

    // Here we can display some sort of list of workout, maybe predefined, saved, random or idk
    private static Pane quickWorkoutPane() {
        VBox quickWorkoutPane = new VBox();
        quickWorkoutPane.setSpacing(20);
        quickWorkoutPane.setAlignment(Pos.CENTER);
        quickWorkoutPane.setPrefSize(640, 600);
        quickWorkoutPane.setMaxWidth(Region.USE_PREF_SIZE);
        quickWorkoutPane.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
        Label quickWorkoutLabel = new Label("A list of some sort of workouts could be displayed " +
                "here in the colored area. They could have a title and a description\nand be formatted in a way" +
                "where we can have 5-6 workouts displayed.");
        Label additionalComment = new Label("The color should not be green btw, it's just to show the area to use");
        quickWorkoutPane.getChildren().add(quickWorkoutLabel);
        quickWorkoutPane.getChildren().add(additionalComment);

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
                        ViewController.setScene(MuscleSelectionView.createMuscleSelectorScene());
                        break;
                    case "My Workouts":
                        System.out.println("My Workouts pressed");
                    case "Exit":
                        System.exit(0);
                }
            });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

}
