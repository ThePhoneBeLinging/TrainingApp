package Group15;

import Group15.Api.BodyPart;
import Group15.Api.ApiUtils;
import Group15.WorkoutAlgorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewController
{
    private static Stage stage;

    public static void init(Stage stage)
    {
        ApiUtils.getAllExercises();
        ViewController.stage = stage;
        ViewController.stage.setScene(HomeScreenView.createScene());
        ViewController.stage.show();
    }

    public static Scene getScene()
    {
        return ViewController.stage.getScene();
    }

    public static void setScene(Scene scene)
    {
        ViewController.stage.setScene(scene);
    }

    public static Scene createMuscleSelectorScene() {
        VBox vBox = new VBox();
        List<ToggleButton> toggleButtons = new ArrayList<>();

        for (BodyPart bodyPart : BodyPart.values()) {
            ToggleButton toggleButton = new ToggleButton(bodyPart.toString());
            toggleButton.setPrefSize(200,50);
            toggleButtons.add(toggleButton);
            vBox.getChildren().add(toggleButton);
        }

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");
        submitButton.setPrefSize(200,50);
        backButton.setPrefSize(200,50);
        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));
        vBox.getChildren().add(submitButton);
        vBox.getChildren().add(backButton);
        WorkoutAlgorithm workoutAlgorithm = new WorkoutAlgorithm();

        submitButton.setOnAction(e -> {
            for (ToggleButton toggleButton : toggleButtons) {
                if (toggleButton.isSelected()) {
                    workoutAlgorithm.createWorkoutFromExercises(toggleButton.getText());
                }
            }
        });

        backButton.setOnAction(e -> {
            Scene homeScreen = HomeScreenView.createScene();
            Stage thisStage = (Stage) backButton.getScene().getWindow();
            thisStage.setScene(homeScreen);
        });

        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        return new Scene(vBox, 1000,800);
    }
}
