package Group15.View;

import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Util.WorkoutAlgorithm;

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
        minutesInputField.setMinSize(210,40);
        minutesInputField.setMaxSize(210,40);
        minutesInputField.setPromptText("Input how many minutes to workout");

        ScrollPane equipmentSelectorScrollPane = createEquipmentSelectorScrollPane(selectedEquipment);
        inputAndEquipBox.getChildren().addAll(minutesInputField, equipmentSelectorScrollPane);

        ScrollPane bodyPartsScrollPane = createBodyPartsSelectorScrollPane(selectedBodyParts);

        Button submitButton = new Button("Submit");
        submitButton.setMinSize(200, 50);
        submitButton.setOnAction(_ -> createSubmitButtonFunctionality(selectedBodyParts, dislikedBodyParts, selectedEquipment, minutesInputField));

        Button backButton = new Button("Back");
        backButton.setMinSize(200, 50);
        backButton.setOnAction(_ -> ViewController.goBack());

        HBox backAndSubmitButton = new HBox();
        backAndSubmitButton.setSpacing(20);
        backAndSubmitButton.setPadding(new Insets(0,0,20,0));
        backAndSubmitButton.getChildren().addAll(backButton, submitButton);
        backAndSubmitButton.setAlignment(Pos.CENTER);

        mainVBox.getChildren().addAll(bodyPartsScrollPane, inputAndEquipBox, backAndSubmitButton);

        return new Scene(mainVBox);
    }

    private static ScrollPane createEquipmentSelectorScrollPane(List<Equipment> selectedEquipment) {
        GridPane equipmentSelectorGridPane = new GridPane();
        equipmentSelectorGridPane.setHgap(16);
        equipmentSelectorGridPane.setVgap(10);
        equipmentSelectorGridPane.setAlignment(Pos.CENTER);

        Label selectEquipmentText = new Label("Select Equipment");
        selectEquipmentText.setStyle("-fx-font-weight: bold");
        equipmentSelectorGridPane.add(selectEquipmentText, 0, 0, 3, 1);

        List<CheckBox> equipmentCheckBoxes = new ArrayList<>();

        CheckBox allCheckbox = new CheckBox("All");
        allCheckbox.setSelected(true);
        equipmentCheckBoxes.add(allCheckbox);
        equipmentSelectorGridPane.add(allCheckbox, 0, 1, 3, 1);

        int index = 0;
        int columns = 3;
        for (Equipment equipment : Equipment.values()) {
            CheckBox equipmentCheckbox = new CheckBox(equipment.name());
            equipmentCheckBoxes.add(equipmentCheckbox);

            int column = index % columns;
            int row = (index / columns) + 2;

            equipmentSelectorGridPane.add(equipmentCheckbox, column, row);

            index++;
        }

        createEquipmentCheckboxFunctionality(allCheckbox, equipmentCheckBoxes, selectedEquipment);

        ScrollPane equipmentSelectorScrollPane = new ScrollPane(equipmentSelectorGridPane);
        equipmentSelectorScrollPane.setFitToWidth(true);
        equipmentSelectorScrollPane.setMinSize(350, 100);

        return equipmentSelectorScrollPane;
    }

    private static void createEquipmentCheckboxFunctionality(CheckBox allCheckbox, List<CheckBox> equipmentCheckBoxes, List<Equipment> selectedEquipment) {
        allCheckbox.setOnAction(_ -> {
            if (allCheckbox.isSelected()) {
                selectedEquipment.clear();
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
                        selectedEquipment.remove(Equipment.valueOf(checkbox.getText()));
                        if (!selectedEquipment.contains(Equipment.valueOf(checkbox.getText()))) selectedEquipment.add(Equipment.valueOf(checkbox.getText()));
                    } else {
                        selectedEquipment.remove(Equipment.valueOf(checkbox.getText()));
                    }
                });
            }
        }
    }

    private static ScrollPane createBodyPartsSelectorScrollPane(List<BodyPart> selectedBodyParts) {
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
            bodyPartToggleButton.setOnAction(_ -> {
                if (bodyPartToggleButton.isSelected()) {
                    selectedBodyParts.add(bodyPart);
                }
                else {
                    selectedBodyParts.remove(bodyPart);
                }
            });

            columns++;
            if (columns > 2) {
                columns = 0;
                rows++;
            }
        }

        ScrollPane bodyPartsScrollPane = new ScrollPane(bodyPartsGridPane);
        bodyPartsScrollPane.setFitToWidth(true);
        return bodyPartsScrollPane;
    }

    private static ToggleButton createBodyPartToggleButton(BodyPart bodyPart) {
        Image bodyPartImage;
        try {
            bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/" + bodyPart.toString() + ".png")));
        }catch (Exception _) {
            bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/empty.png")));
        }


        ImageView bodyPartImageView = new ImageView(bodyPartImage);
        bodyPartImageView.setFitHeight(50);
        bodyPartImageView.setFitWidth(50);
        bodyPartImageView.setPreserveRatio(true);

        Label bodyPartName = new Label(bodyPart.toString());
        bodyPartName.setMinWidth(60);
        bodyPartName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        bodyPartName.setAlignment(Pos.CENTER_LEFT);

        HBox imageAndTextButtons = new HBox(10, bodyPartImageView, bodyPartName);
        imageAndTextButtons.setPadding(new Insets(3,5,3,3));
        imageAndTextButtons.setAlignment(Pos.CENTER_LEFT);

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
        ViewController.setScene(WorkoutView.createScene(WorkoutAlgorithm.createWorkoutFromExercises(selectedBodyParts, dislikedBodyParts, selectedEquipment, timeInMinutes)));
    }
}
