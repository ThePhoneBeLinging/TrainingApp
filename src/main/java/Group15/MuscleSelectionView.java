package Group15;

import Group15.Api.BodyPart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MuscleSelectionView {

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
        TextField inputField = new TextField();

        submitButton.setPrefSize(200,50);
        backButton.setPrefSize(200,50);

        inputField.setPrefSize(200, 50);
        inputField.setMaxSize(200, 50);
        inputField.setPromptText("Input how many minutes to workout");

        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));

        vBox.getChildren().add(inputField);
        vBox.getChildren().add(submitButton);
        vBox.getChildren().add(backButton);

        WorkoutAlgorithm workoutAlgorithm = new WorkoutAlgorithm();

        submitButton.setOnAction(_ -> {
            if(inputField.getText() != null && !inputField.getText().isEmpty()) {
                if(!inputField.getText().matches("\\d+")){
                    System.out.println("Input is not a number!");
                } else {
                    int timeInSeconds = Integer.parseInt(inputField.getText()) * 60;
                    for(ToggleButton toggleButton : toggleButtons) {
                        if (toggleButton.isSelected()) {
                            workoutAlgorithm.createWorkoutFromExercises(toggleButton.getText(), timeInSeconds);
                        }
                    }
                }
            }
        });

        backButton.setOnAction(_ -> {
            Scene homeScreen = HomeScreenView.createScene();
            Stage thisStage = (Stage) backButton.getScene().getWindow();
            thisStage.setScene(homeScreen);
        });

        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        return new Scene(vBox, 1000,800);
    }
}
