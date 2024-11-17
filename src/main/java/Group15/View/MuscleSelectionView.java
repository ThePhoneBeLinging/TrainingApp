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
    private static final List<String> errorList = new ArrayList<>();

    private static String workoutName;
    private static TextField minutesInputField;
    private static VBox errorVBox;
    private static TextArea errorMessageTextArea;

    public static String getWorkoutName() {
        return workoutName;
    }

    public static void setWorkoutName(String workoutName) {
        MuscleSelectionView.workoutName = workoutName;
    }

    private enum BodyPartButtonStates {
        DESELECT, SELECT, DISLIKE
    }

    public static Scene createMuscleSelectionScene() {
        VBox mainVBox = new VBox(20);
        mainVBox.setAlignment(Pos.CENTER);

        HBox inputAndEquipBox = createInputAndEquipBox();
        HBox backAndSubmitButton = createBackAndSubmitBox();
        ScrollPane bodyPartsScrollPane = createBodyPartsSelectorScrollPane(selectedBodyParts);

        mainVBox.getChildren().addAll(bodyPartsScrollPane, inputAndEquipBox, backAndSubmitButton);

        return new Scene(mainVBox);
    }

    private static HBox createBackAndSubmitBox () {
        Button submitButton = new Button("Submit");
        submitButton.setMinSize(200, 50);
        submitButton.setOnAction(_ -> createSubmitButtonFunctionality(MuscleSelectionView.minutesInputField));

        Button backButton = new Button("Back");
        backButton.setMinSize(200, 50);
        backButton.setOnAction(_ -> ViewController.goBack());

        HBox backAndSubmitButton = new HBox();
        backAndSubmitButton.setSpacing(20);
        backAndSubmitButton.setPadding(new Insets(0,0,20,0));
        backAndSubmitButton.getChildren().addAll(backButton, submitButton);
        backAndSubmitButton.setAlignment(Pos.CENTER);

        return backAndSubmitButton;
    }

    private static HBox createInputAndEquipBox() {
        HBox inputAndEquipBox = new HBox(20);
        inputAndEquipBox.setAlignment(Pos.CENTER);

        MuscleSelectionView.minutesInputField = new TextField();
        MuscleSelectionView.minutesInputField.setMinSize(210,40);
        MuscleSelectionView.minutesInputField.setMaxSize(210,40);
        MuscleSelectionView.minutesInputField.setPromptText("Input how many minutes to workout");

        ScrollPane equipmentSelectorScrollPane = createEquipmentSelectorScrollPane();
        errorVBox = createErrorVBox();
        inputAndEquipBox.getChildren().addAll(errorVBox, minutesInputField, equipmentSelectorScrollPane);

        return inputAndEquipBox;
    }

    private static VBox createErrorVBox() {
        VBox errorVBox = new VBox(20);
        errorVBox.setAlignment(Pos.CENTER);

        errorMessageTextArea = new TextArea();
        errorMessageTextArea.setEditable(false);
        errorMessageTextArea.setWrapText(true);

        updateErrorMessageTextArea();
        errorVBox.getChildren().add(errorMessageTextArea);

        return errorVBox;
    }

    private static void updateErrorMessageTextArea () {
        if (errorMessageTextArea != null) {
            errorMessageTextArea.clear();

            for(String errorMessage : errorList) {
                errorMessageTextArea.appendText(" - " + errorMessage + "\n");
            }

        }
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

        createEquipmentCheckboxFunctionality(allCheckbox, equipmentCheckBoxes);

        ScrollPane equipmentSelectorScrollPane = new ScrollPane(equipmentSelectorGridPane);
        equipmentSelectorScrollPane.setFitToWidth(true);
        equipmentSelectorScrollPane.setMinSize(350, 100);

        return equipmentSelectorScrollPane;
    }

    private static void createEquipmentCheckboxFunctionality(CheckBox allCheckbox, List<CheckBox> equipmentCheckBoxes) {
        allCheckbox.setOnAction(_ -> {
            if (allCheckbox.isSelected()) {
                MuscleSelectionView.selectedEquipment.clear();
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
                        MuscleSelectionView.selectedEquipment.remove(Equipment.valueOf(checkbox.getText()));
                        if (!MuscleSelectionView.selectedEquipment.contains(Equipment.valueOf(checkbox.getText()))) MuscleSelectionView.selectedEquipment.add(Equipment.valueOf(checkbox.getText()));
                    } else {
                        MuscleSelectionView.selectedEquipment.remove(Equipment.valueOf(checkbox.getText()));
                    }
                });
            }
        }
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

        return createBodyPartToggleButtonFunctionality(bodyPart, imageAndTextButtons);
    }

    private static Button createBodyPartToggleButtonFunctionality(BodyPart bodyPart, HBox imageAndTextButtons) {
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
        MuscleSelectionView.errorList.clear();

        if (minutesInputField.getText() == null || minutesInputField.getText().isEmpty() || !minutesInputField.getText().matches("\\d+")) {
            MuscleSelectionView.errorList.add("Invalid Input for Time!");
            updateErrorMessageTextArea();
        }
        if(selectedBodyParts.isEmpty()) {
            MuscleSelectionView.errorList.add("No Bodyparts Selected!");
            updateErrorMessageTextArea();
        }
        if(selectedEquipment.isEmpty()) {
            MuscleSelectionView.errorList.add("No Equipment Selected");
            updateErrorMessageTextArea();
        }
        if(!errorList.isEmpty()) {
            updateErrorMessageTextArea();
            return;
        }

        int timeInMinutes = Integer.parseInt(minutesInputField.getText());

        Dialog<String> workoutNameDialog = createWorkoutNameDialog();

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

    private static Dialog<String> createWorkoutNameDialog () {
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

        return workoutNameDialog;
    }
}
