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
    private static List<BodyPart> selectedBodyParts = new ArrayList<>();
    private static final List<BodyPart> dislikedBodyParts = new ArrayList<>();
    private static final List<Equipment> selectedEquipment = new ArrayList<>();

    private static String workoutName;

    public static String getWorkoutName() {
        return workoutName;
    }

    public static void setWorkoutName(String workoutName) {
        MuscleSelectionView.workoutName = workoutName;
    }

    public static Scene createMuscleSelectorScene() {
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);

        HBox inputAndEquipBox = new HBox(20);
        inputAndEquipBox.setAlignment(Pos.CENTER);

        TextField minutesInputField = new TextField();
        minutesInputField.setMinSize(210,40);
        minutesInputField.setMaxSize(210,40);
        minutesInputField.setPromptText("Input how many minutes to workout");

        ScrollPane equipmentSelectorScrollPane = createEquipmentSelectorScrollPane();
        inputAndEquipBox.getChildren().addAll(minutesInputField, equipmentSelectorScrollPane);

        ScrollPane bodyPartsScrollPane = createBodyPartsSelectorScrollPane(selectedBodyParts);

        Button submitButton = new Button("Submit");
        submitButton.setMinSize(200, 50);
        submitButton.setOnAction(_ -> createSubmitButtonFunctionality(minutesInputField));

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

    private static ScrollPane createEquipmentSelectorScrollPane() {
        GridPane equipmentSelectorGridPane = new GridPane();
        equipmentSelectorGridPane.setHgap(16);
        equipmentSelectorGridPane.setVgap(10);
        equipmentSelectorGridPane.setAlignment(Pos.CENTER);

        Label selectEquipmentText = new Label("Select Equipment");
        selectEquipmentText.setStyle("-fx-font-weight: bold");
        equipmentSelectorGridPane.add(selectEquipmentText, 0, 0, 3, 1);

        List<CheckBox> equipmentCheckBoxes = new ArrayList<>();

        Button selectAllButton = new Button("All");
        Button deselectAllButton = new Button("None");
        equipmentSelectorGridPane.add(selectAllButton, 0, 1, 3, 1);
        equipmentSelectorGridPane.add(deselectAllButton, 1, 1, 3, 1);

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

        createEquipmentCheckboxFunctionality(selectAllButton, deselectAllButton, equipmentCheckBoxes);

        ScrollPane equipmentSelectorScrollPane = new ScrollPane(equipmentSelectorGridPane);
        equipmentSelectorScrollPane.setFitToWidth(true);
        equipmentSelectorScrollPane.setMinSize(350, 140);

        return equipmentSelectorScrollPane;
    }

    private static void createEquipmentCheckboxFunctionality(Button allButton, Button noneButton, List<CheckBox> equipmentCheckBoxes) {
        allButton.setOnAction(_ -> {
            for (Equipment equipment : Equipment.values()) {
                if(!selectedEquipment.contains(equipment)) {
                    MuscleSelectionView.selectedEquipment.add(equipment);

                } else {
                    MuscleSelectionView.selectedEquipment.remove(equipment);

                }
            }
            for (CheckBox checkBox : equipmentCheckBoxes) {
                checkBox.setSelected(true);
            }
        });

        noneButton.setOnAction(_ -> {
            for (Equipment equipment : Equipment.values()) {
                if(selectedEquipment.contains(equipment)) {
                    MuscleSelectionView.selectedEquipment.remove(equipment);

                } else {
                    MuscleSelectionView.selectedEquipment.add(equipment);

                }
            }
            for (CheckBox checkBox : equipmentCheckBoxes) {
                checkBox.setSelected(false);
            }
        });

        for (CheckBox checkBox : equipmentCheckBoxes) {
            checkBox.setOnAction(_ -> {
                if(checkBox.isSelected()) {
                    MuscleSelectionView.selectedEquipment.add(fromTextToEquipment(checkBox.getText()));

                } else {
                    MuscleSelectionView.selectedEquipment.remove(fromTextToEquipment(checkBox.getText()));

                }
            });
        }
    }

    private static Equipment fromTextToEquipment (String checkBoxText) {
        if (checkBoxText == null || checkBoxText.isEmpty()) return null;

        if("EZBar".equalsIgnoreCase(checkBoxText)) {
            return Equipment.EZBar;
        }
        if ("SmithMachine".equalsIgnoreCase(checkBoxText)) {
            return Equipment.SmithMachine;
        }

        String caseMatchedText = caseMatchText(checkBoxText);

        for (Equipment equipment : Equipment.values()) {
            if (equipment.name().equals(caseMatchedText)) {
                return equipment;
            }
        }
        return null;
    }

    private static String caseMatchText(String text) {
        if(text == null || text.isEmpty()) {
            return null;
        }
        char firstChar = text.charAt(0);

        return Character.toUpperCase(firstChar) + text.substring(1).toLowerCase();
    }

    private static ScrollPane createBodyPartsSelectorScrollPane(List<BodyPart> selectedBodyParts) {
        MuscleSelectionView.selectedBodyParts = selectedBodyParts;
        GridPane bodyPartsGridPane = new GridPane();
        bodyPartsGridPane.setHgap(20);
        bodyPartsGridPane.setVgap(20);
        bodyPartsGridPane.setAlignment(Pos.CENTER);

        List<Button> bodyPartToggleButtons = new ArrayList<>();
        int columns = 0;
        int rows = 0;

        for (BodyPart bodyPart : BodyPart.values()) {
            Button bodyPartToggleButton = createBodyPartToggleButton(bodyPart);
            bodyPartToggleButtons.add(bodyPartToggleButton);
            bodyPartsGridPane.add(bodyPartToggleButton, columns, rows);

            columns++;
            if (columns > 1) {
                columns = 0;
                rows++;
            }
        }

        ScrollPane bodyPartsScrollPane = new ScrollPane(bodyPartsGridPane);
        bodyPartsScrollPane.setFitToWidth(true);
        return bodyPartsScrollPane;
    }

    private enum BodyPartButtonStates {
        DESELECT, SELECT, DISLIKE
    }

    private static Button createBodyPartToggleButton(BodyPart bodyPart) {
        Image bodyPartImage;
        try {
            bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/" + bodyPart.name() + ".png")));
        }catch (Exception _) {
            bodyPartImage = new Image(Objects.requireNonNull(MuscleSelectionView.class.getResourceAsStream("/bodyparts/empty.png")));
        }

        ImageView bodyPartImageView = new ImageView(bodyPartImage);
        bodyPartImageView.setFitHeight(200);
        bodyPartImageView.setFitWidth(200);
        bodyPartImageView.setPreserveRatio(true);

        Label bodyPartName = new Label(bodyPart.toString());
        bodyPartName.setMinWidth(60);
        bodyPartName.setPadding(new Insets(0,0,0,30));
        bodyPartName.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        bodyPartName.setAlignment(Pos.CENTER_LEFT);

        HBox imageAndTextButtons = new HBox(10, bodyPartImageView, bodyPartName);
        imageAndTextButtons.setPadding(new Insets(3,5,3,3));
        imageAndTextButtons.setAlignment(Pos.CENTER_LEFT);

        Button bodyPartToggleButton = new Button();
        bodyPartToggleButton.setGraphic(imageAndTextButtons);
        bodyPartToggleButton.setMinSize(300, 150);

        bodyPartToggleButton.setUserData(BodyPartButtonStates.DESELECT);
        bodyPartToggleButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        bodyPartToggleButton.setOnAction(_ -> {
            BodyPartButtonStates currentState = (BodyPartButtonStates) bodyPartToggleButton.getUserData();

            if (currentState == BodyPartButtonStates.DESELECT) {
                bodyPartToggleButton.setUserData(BodyPartButtonStates.SELECT);
                bodyPartToggleButton.setStyle("-fx-border-color: green; -fx-border-width: 2;");
            } else if (currentState == BodyPartButtonStates.SELECT) {
                bodyPartToggleButton.setUserData(BodyPartButtonStates.DISLIKE);
                bodyPartToggleButton.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            } else {
                bodyPartToggleButton.setUserData(BodyPartButtonStates.DESELECT);
                bodyPartToggleButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            }
            updateBodyPartLists(bodyPart, (BodyPartButtonStates) bodyPartToggleButton.getUserData());
        });

        return bodyPartToggleButton;
    }

    private static void updateBodyPartLists(BodyPart bodyPart, BodyPartButtonStates currenState) {
        if(currenState == BodyPartButtonStates.SELECT) {
            if(!selectedBodyParts.contains(bodyPart)) {
                selectedBodyParts.add(bodyPart);
            }
        } else if (currenState == BodyPartButtonStates.DESELECT) {
            selectedBodyParts.remove(bodyPart);
        }

        if(currenState == BodyPartButtonStates.DISLIKE) {
            if(!dislikedBodyParts.contains(bodyPart)) {
                dislikedBodyParts.add(bodyPart);
            }
        } else if (currenState == BodyPartButtonStates.DESELECT) {
            dislikedBodyParts.remove(bodyPart);
        }
    }

    private static void createSubmitButtonFunctionality(TextField minutesInputField) {
        if (minutesInputField.getText() == null || minutesInputField.getText().isEmpty() || !minutesInputField.getText().matches("\\d+")) {
            System.out.println("Invalid input");
            return;
        }

        int timeInMinutes = Integer.parseInt(minutesInputField.getText());

        Dialog<String> workoutNameDialog = new Dialog<>();
        workoutNameDialog.setTitle("!");

        Label giveWorkoutNameLabel = new Label("Give your workout a name:");
        giveWorkoutNameLabel.setStyle("-fx-font-weight: bold");

        TextField workoutNameInputField = new TextField();
        workoutNameInputField.setPromptText("Workout");

        VBox dialogVBox = new VBox(10, giveWorkoutNameLabel, workoutNameInputField);
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setPadding(new Insets(10));

        workoutNameDialog.getDialogPane().setContent(dialogVBox);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        workoutNameDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        workoutNameDialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return workoutNameInputField.getText();
            }
            return null;
        });

        workoutNameDialog.showAndWait().ifPresentOrElse(
                workoutName -> {
                    String finalWorkoutName = workoutName.trim().isEmpty() ? "Workout" : workoutName.trim();
                    MuscleSelectionView.setWorkoutName(finalWorkoutName);
                    ViewController.setScene(WorkoutView.createScene(
                            WorkoutAlgorithm.createWorkoutFromExercises(selectedBodyParts, dislikedBodyParts, selectedEquipment, timeInMinutes)
                    ));
                },
                () -> {
                    MuscleSelectionView.setWorkoutName("Workout");
                    ViewController.setScene(WorkoutView.createScene(
                            WorkoutAlgorithm.createWorkoutFromExercises(selectedBodyParts, dislikedBodyParts, selectedEquipment, timeInMinutes)
                    ));
                }
        );

    }
}
