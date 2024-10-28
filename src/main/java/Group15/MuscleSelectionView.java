package Group15;

import Group15.Api.BodyPart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MuscleSelectionView {

    public static Scene createMuscleSelectorScene() {
        VBox vBox = new VBox();
        HBox inputAndEquipBox = new HBox();

        ComboBox comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "All",
                "Bodyweight",
                "Barbell",
                "Dumbell",
                "Machine"
        );
        comboBox.setMinSize(200,50);
        comboBox.setPromptText("Select Equipment...");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        List<ToggleButton> toggleButtons = new ArrayList<>();

        int col = 0;
        int row = 0;
        for (BodyPart bodyPart : BodyPart.values()) {
            ToggleButton toggleButton = new ToggleButton(bodyPart.toString());
            toggleButton.setMinSize(200,50);
            toggleButtons.add(toggleButton);
            gridPane.add(toggleButton, col, row);

            col++;
            if(col > 1) {
                col = 0;
                row++;
            }
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

        inputAndEquipBox.getChildren().addAll(inputField,comboBox);
        inputAndEquipBox.setSpacing(20);
        inputAndEquipBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(inputAndEquipBox);
        vBox.getChildren().add(submitButton);
        vBox.getChildren().add(backButton);

        WorkoutAlgorithm workoutAlgorithm = new WorkoutAlgorithm();

        submitButton.setOnAction(_ -> {
            if(inputField.getText() == null || inputField.getText().isEmpty() || !inputField.getText().matches("\\d+")) {
                System.out.println("Invalid input");
            }
            int timeInSeconds = Integer.parseInt(inputField.getText());
            for(ToggleButton toggleButton : toggleButtons) {
                if (toggleButton.isSelected()) {
                    workoutAlgorithm.createWorkoutFromExercises(toggleButton.getText(), timeInSeconds);
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
