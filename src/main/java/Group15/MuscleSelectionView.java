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
        VBox mainVBox = new VBox();
        HBox inputAndEquipBox = new HBox();
        HBox imageAndTextButtons;

        List<String> selectedBodyParts = new ArrayList<>();
        List<String> dislikedBodyParts = new ArrayList<>();
        List<String> selectedEquipment = new ArrayList<>();

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

        GridPane bodyPartsGridPane = new GridPane();
        bodyPartsGridPane.setHgap(20);
        bodyPartsGridPane.setVgap(20);
        bodyPartsGridPane.setAlignment(Pos.CENTER);

        List<ToggleButton> bodyPartToggleButtons = new ArrayList<>();

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

            ToggleButton bodyPartToggleButton = new ToggleButton();
            bodyPartToggleButton.setGraphic(imageAndTextButtons);
            bodyPartToggleButton.setMinSize(200,50);
            bodyPartToggleButtons.add(bodyPartToggleButton);
            bodyPartsGridPane.add(bodyPartToggleButton, col, row);

            col++;
            if(col > 1) {
                col = 0;
                row++;
            }
        }

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back");

        TextField minutesInputField = new TextField();

        submitButton.setPrefSize(200,50);
        backButton.setPrefSize(200,50);

        minutesInputField.setPrefSize(200, 50);
        minutesInputField.setMaxSize(200, 50);
        minutesInputField.setPromptText("Input how many minutes to workout");

        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));

        inputAndEquipBox.getChildren().addAll(minutesInputField,equipmentSelector);
        inputAndEquipBox.setSpacing(20);
        inputAndEquipBox.setAlignment(Pos.CENTER);

        mainVBox.getChildren().add(bodyPartsGridPane);
        mainVBox.getChildren().add(inputAndEquipBox);
        mainVBox.getChildren().add(submitButton);
        mainVBox.getChildren().add(backButton);


        submitButton.setOnAction(_ -> {
            if(minutesInputField.getText() == null || minutesInputField.getText().isEmpty() || !minutesInputField.getText().matches("\\d+")) {
                System.out.println("Invalid input");
                return;
            }
            int timeInSeconds = Integer.parseInt(minutesInputField.getText());
            for(ToggleButton toggleButton : bodyPartToggleButtons) {
                if (toggleButton.isSelected()) {
                    selectedBodyParts.add(toggleButton.getText());
                    WorkoutAlgorithm.createWorkoutFromExercises(selectedBodyParts, dislikedBodyParts, selectedEquipment, timeInSeconds);
                }
            }
        });

        backButton.setOnAction(_ -> {
            ViewController.setScene(HomeScreenView.createScene());
        });

        mainVBox.setSpacing(20);
        mainVBox.setAlignment(Pos.CENTER);

        return new Scene(mainVBox, 1000,800);
    }
}
