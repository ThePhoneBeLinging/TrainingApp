package Group15;

import Group15.Api.BodyPart;
import Group15.Api.Equipment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MuscleSelectionView {

    public static Scene createMuscleSelectorScene() {
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);

        HBox inputAndEquipBox = new HBox(20);
        inputAndEquipBox.setAlignment(Pos.CENTER);

        List<BodyPart> selectedBodyParts = new ArrayList<>();
        List<BodyPart> dislikedBodyParts = new ArrayList<>();
        List<Equipment> selectedEquipment = new ArrayList<>();

        TextField minutesInputField = new TextField();
        minutesInputField.setPrefSize(200, 50);
        minutesInputField.setPromptText("Input how many minutes to workout");

        GridPane equipmentSelectorGridPane = createEquipmentSelectorGridPane(selectedEquipment);

        inputAndEquipBox.getChildren().addAll(minutesInputField, equipmentSelectorGridPane);

        GridPane bodyPartsGridPane = createBodyPartsSelectorGridPane(selectedBodyParts);

        Button submitButton = new Button("Submit");
        submitButton.setPrefSize(200, 50);
        submitButton.setOnAction(_ -> createSubmitButtonFunctionality(selectedBodyParts, dislikedBodyParts, selectedEquipment, minutesInputField));

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 50);
        backButton.setOnAction(_ -> ViewController.setScene(HomeScreenView.createScene()));

        mainVBox.getChildren().addAll(bodyPartsGridPane, inputAndEquipBox, submitButton, backButton);
        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));

        return new Scene(mainVBox);
    }

    private static GridPane createEquipmentSelectorGridPane(List<Equipment> selectedEquipment) {
        GridPane equipmentSelectorGridPane = new GridPane();
        equipmentSelectorGridPane.setVgap(10);
        equipmentSelectorGridPane.setAlignment(Pos.CENTER);

        Label selectEquipmentText = new Label("Select Equipment");
        selectEquipmentText.setStyle("-fx-font-weight: bold");
        equipmentSelectorGridPane.add(selectEquipmentText, 0, 0, 2, 1);

        String[] equipments = {"Body weight", "Barbell", "Dumbbell", "Machine"};
        List<CheckBox> equipmentCheckBoxes = new ArrayList<>();

        CheckBox allCheckbox = new CheckBox("All");
        allCheckbox.setSelected(true);
        equipmentCheckBoxes.add(allCheckbox);
        equipmentSelectorGridPane.add(allCheckbox, 0, 1, 2, 1);

        for (int i = 0; i < equipments.length; i++) {
            CheckBox equipmentCheckbox = new CheckBox(equipments[i]);
            equipmentCheckBoxes.add(equipmentCheckbox);
            equipmentSelectorGridPane.add(equipmentCheckbox, i % 2, 2 + (i / 2));
        }

        createEquipmentCheckboxFunctionality(allCheckbox, equipmentCheckBoxes, selectedEquipment);

        return equipmentSelectorGridPane;
    }

    private static void createEquipmentCheckboxFunctionality(CheckBox allCheckbox, List<CheckBox> equipmentCheckBoxes, List<Equipment> selectedEquipment) {
        allCheckbox.setOnAction(_ -> {
            if (allCheckbox.isSelected()) {
                selectedEquipment.clear();
                //selectedEquipment.add("All");
                for (CheckBox checkbox : equipmentCheckBoxes) {
                    if (checkbox != allCheckbox) checkbox.setSelected(false);
                }
            }
        });

        for (CheckBox checkbox : equipmentCheckBoxes) {
            if (checkbox != allCheckbox) {
                checkbox.setOnAction(_ -> {
                    if (checkbox.isSelected()) {
                        allCheckbox.setSelected(false);
                        selectedEquipment.remove("All");
                        if (!selectedEquipment.contains(checkbox.getText())) selectedEquipment.add(Equipment.valueOf(checkbox.getText()));
                    } else {
                        selectedEquipment.remove(checkbox.getText());
                    }
                });
            }
        }
    }

    private static GridPane createBodyPartsSelectorGridPane(List<BodyPart> selectedBodyParts) {
        GridPane bodyPartsGridPane = new GridPane();
        bodyPartsGridPane.setHgap(20);
        bodyPartsGridPane.setVgap(20);
        bodyPartsGridPane.setAlignment(Pos.CENTER);

        List<ToggleButton> bodyPartToggleButtons = new ArrayList<>();
        int columns = 0;
        int rows = 0;

        for (BodyPart bodyPart : BodyPart.values()) {
            ToggleButton bodyPartToggleButton = createBodyPartToggleButton(bodyPart);
            bodyPartToggleButtons.add(bodyPartToggleButton);
            bodyPartsGridPane.add(bodyPartToggleButton, columns, rows);

            columns++;
            if (columns > 1) {
                columns = 0;
                rows++;
            }
        }

        bodyPartToggleButtons.forEach(toggleButton -> {
            toggleButton.setOnAction(_ -> {
                if (toggleButton.isSelected()) selectedBodyParts.add(BodyPart.valueOf(toggleButton.getText()));
                else selectedBodyParts.remove(toggleButton.getText());
            });
        });

        return bodyPartsGridPane;
    }

    private static ToggleButton createBodyPartToggleButton(BodyPart bodyPart) {
        Image bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/" + bodyPart.toString() + ".png")));
        ImageView bodyPartImageView = new ImageView(bodyPartImage);
        bodyPartImageView.setFitHeight(60);
        bodyPartImageView.setFitWidth(100);
        bodyPartImageView.setPreserveRatio(true);

        Label bodyPartName = new Label(bodyPart.toString());
        bodyPartName.setMinWidth(67);
        bodyPartName.setAlignment(Pos.CENTER);

        HBox imageAndTextButtons = new HBox(10, bodyPartImageView, bodyPartName);
        imageAndTextButtons.setAlignment(Pos.CENTER);

        ToggleButton bodyPartToggleButton = new ToggleButton();
        bodyPartToggleButton.setGraphic(imageAndTextButtons);
        bodyPartToggleButton.setMinSize(200, 50);

        return bodyPartToggleButton;
    }

    private static void createSubmitButtonFunctionality(List<BodyPart> selectedBodyParts, List<BodyPart> dislikedBodyParts, List<Equipment> selectedEquipment, TextField minutesInputField) {
        if (minutesInputField.getText() == null || minutesInputField.getText().isEmpty() || !minutesInputField.getText().matches("\\d+")) {
            System.out.println("Invalid input");
            return;
        }
        int timeInMinutes = Integer.parseInt(minutesInputField.getText());
        WorkoutAlgorithm.createWorkoutFromExercises(selectedBodyParts, dislikedBodyParts, selectedEquipment, timeInMinutes);
    }
}
