package Group15;

import Group15.Api.BodyPart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MuscleSelectionView {

    public static Scene createMuscleSelectorScene() {
        VBox vBox = new VBox();
        HBox inputAndEquipBox = new HBox();
        HBox imageAndTextButtons;

        List<String> selectedBodyparts = new ArrayList<>();

        ComboBox equipmentSelector = new ComboBox<>();
        equipmentSelector.getItems().addAll(
                "All",
                "Body weight",
                "Barbell",
                "Dumbbell",
                "Machine"
        );
        equipmentSelector.setMinSize(200,50);
        equipmentSelector.setPromptText("Select Equipment...");
        String selectedEquipment = (String) equipmentSelector.getValue();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        List<ToggleButton> toggleButtons = new ArrayList<>();

        int col = 0;
        int row = 0;
        for (BodyPart bodyPart : BodyPart.values()) {
            Image bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/" +bodyPart.toString()+ ".png")));
            ImageView bodyPartImageView = new ImageView(bodyPartImage);
            bodyPartImageView.setFitHeight(60);
            bodyPartImageView.setFitWidth((double) 200 /2);
            bodyPartImageView.setPreserveRatio(true);

            Label bodyPartName = new Label(bodyPart.toString());
            bodyPartName.setMinWidth((double) 200 /3);
            bodyPartName.setAlignment(Pos.CENTER);

            imageAndTextButtons = new HBox(bodyPartImageView, bodyPartName);
            imageAndTextButtons.setAlignment(Pos.CENTER);
            imageAndTextButtons.setSpacing(10);


            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setGraphic(imageAndTextButtons);
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

        inputAndEquipBox.getChildren().addAll(inputField,equipmentSelector);
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
                return;
            }
            int timeInSeconds = Integer.parseInt(inputField.getText());
            for(ToggleButton toggleButton : toggleButtons) {
                if (toggleButton.isSelected()) {
                    selectedBodyparts.add(toggleButton.getText());
                    workoutAlgorithm.createWorkoutFromExercises(selectedBodyparts, Collections.singletonList(""), selectedEquipment, timeInSeconds);
                }
            }
        });

        backButton.setOnAction(_ -> {
            ViewController.setScene(HomeScreenView.createScene());
        });

        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        return new Scene(vBox, 1000,800);
    }
}
