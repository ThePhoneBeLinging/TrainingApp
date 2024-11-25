package Group15.View;

import Group15.Model.Workout;

import Group15.Model.WorkoutExercise;
import Group15.Util.WorkoutUtils;
import Group15.WorkoutPdfGenerator;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;

public class WorkoutView {
    private  static Workout workout;
    private static final String title = "Workout Details";
    private static final String[] buttons = {"Back", "Edit Workout", "Save"};

    public static Scene createScene(Workout workoutToEdit) {
        workout = workoutToEdit;
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));

        Pane titlePane = createTitlePane();
        titlePane.setPadding(new Insets(10));
        layout.setTop(titlePane);

        Node WorkoutPane = createWorkoutPane(workout);
        layout.setCenter(WorkoutPane);

        Pane buttonPane = createButtonPane();
        buttonPane.setPadding(new Insets(20, 0, 0, 0));
        layout.setBottom(buttonPane);

        return new Scene(layout);
    }

    private static Pane createTitlePane() {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        titlePane.getChildren().add(titleLabel);

        return titlePane;
    }

    private static Node createWorkoutPane(Workout workout) {
        VBox workoutPane = new VBox();
        workoutPane.setAlignment(Pos.TOP_CENTER);
        workoutPane.setSpacing(20);
        workoutPane.setPrefSize(640, 600);
        workoutPane.setMaxWidth(Region.USE_PREF_SIZE);

        Node workoutTitleNode = createWorkoutTitleNode();
        workoutPane.getChildren().add(workoutTitleNode);
        Label breakField = new Label("" + Workout.BREAK_BETWEEN_SETS / 1000 + " seconds break between sets");
        String breakFieldString = Workout.BREAK_BETWEEN_SETS / 1000 + " seconds break between sets" + " | " + Workout.BREAK_BETWEEN_EXERCISES / 1000 + " seconds break between exercises";
        breakField.setText(breakFieldString);
        workoutPane.getChildren().add(breakField);
        breakField.setFont(Font.font("Arial", FontWeight.MEDIUM, FontPosture.ITALIC, 18));

        for (WorkoutExercise workoutExercise : workout.getExercises()) {
            ImageView imageView = null;

            try {
                Image image = new Image(WorkoutView.class.getResource("/images/" + workoutExercise.getExercise().title + ".png").toExternalForm(), 100, 100, true, true);
                imageView = new ImageView(image);
            } catch (Exception e) {
                System.out.println("Error loading image for exercise: " + workoutExercise.getExercise().title);
                imageView = new ImageView();
            }

            Label exerciseLabel1 = new Label(workoutExercise.getExercise().title + ": ");
            Label exerciseSetsLabel = new Label(" Sets: " + workoutExercise.getSets());
            Label exerciseRepsLabel = new Label(" Reps: " + workoutExercise.getRepsPerSet());
            exerciseLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            exerciseSetsLabel.setFont(Font.font("Arial", FontWeight.LIGHT, 16));
            exerciseRepsLabel.setFont(Font.font("Arial", FontWeight.LIGHT, 16));

            EventHandler<MouseEvent> clickAction = _ ->
                {
                System.out.println("Image or title clicked for exercise: " + workoutExercise.getExercise().title);
                Scene exerciseDetailsScene = ExerciseDetailsView.createScene(workoutExercise.getExercise());
                ViewController.setScene(exerciseDetailsScene);
            };
            HBox exerciseBox = new HBox();
            exerciseBox.setOnMouseClicked(clickAction);
            exerciseBox.setSpacing(10);
            exerciseBox.setAlignment(Pos.CENTER_LEFT);
            exerciseBox.setBackground(Background.fill(Color.LIGHTGRAY));
            exerciseBox.setPadding(new Insets(10));

            exerciseBox.getChildren().addAll(imageView, exerciseLabel1, exerciseSetsLabel, exerciseRepsLabel);

            workoutPane.getChildren().add(exerciseBox);
        }

        ScrollPane scrollPane = new ScrollPane(workoutPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400);
        scrollPane.setMaxWidth(Region.USE_PREF_SIZE);
        scrollPane.setPadding(new Insets(10));

        return scrollPane;
    }

    private static Node createWorkoutTitleNode () {
        HBox workoutTitleHBox = new HBox();
        workoutTitleHBox.setAlignment(Pos.CENTER);
        workoutTitleHBox.setSpacing(10);

        Label workoutTitleLabel = new Label(workout.getName());
        workoutTitleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 24));

        Button editNameButton = new Button("Edit Name");
        editNameButton.setOnAction(_ -> {
            Dialog<String> editNameDialog = new Dialog<>();
            editNameDialog.setTitle("Edit Workout Name");

            Label instructionLabel = new Label("Enter a new name for your workout:");
            TextField workoutNameInput = new TextField(workout.getName());
            VBox dialogContent = new VBox(10, instructionLabel, workoutNameInput);
            dialogContent.setAlignment(Pos.CENTER);
            dialogContent.setPadding(new Insets(10));

            editNameDialog.getDialogPane().setContent(dialogContent);
            editNameDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            editNameDialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return workoutNameInput.getText().trim();
                }
                return null;
            });

            editNameDialog.showAndWait().ifPresent(newWorkoutName -> {
                if (!newWorkoutName.isEmpty()) {
                    workout.setName(newWorkoutName);
                    workoutTitleLabel.setText(newWorkoutName);
                }
            });
        });

        Label workoutDurationLabel = new Label(formatWorkoutDuration(workout.calculateWorkoutDuration()));
        workoutDurationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        workoutDurationLabel.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(workoutDurationLabel, Priority.ALWAYS);

        workoutTitleHBox.getChildren().addAll(workoutTitleLabel, editNameButton, workoutDurationLabel);

        return workoutTitleHBox;
    }

    private static String formatWorkoutDuration(int durationInMilli) {
        int totalSeconds = durationInMilli / 1000;
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("Duration: %02d:%02d:%02d", hours, minutes, seconds);
    }

    private static void createSuccessSaveDialog() {
        Dialog<String> succesDialog = new Dialog<>();
        succesDialog.setTitle("!");

        Label successSaveLabel = new Label("Succesfully saved workout as PDF");
        successSaveLabel.setStyle("-fx-font-weight: bold");

        VBox dialogVBox = new VBox(10, successSaveLabel);
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setPadding(new Insets(10));

        succesDialog.getDialogPane().setContent(dialogVBox);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        succesDialog.getDialogPane().getButtonTypes().addAll(okButtonType);

        succesDialog.showAndWait();

    }

    private static Pane createButtonPane(){
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);

        for (String button : buttons) {
            Button newButton = new Button(button);
            newButton.setPrefSize(200, 50);
            newButton.setOnAction(_ -> {
                switch (button) {
                    case "Back" -> ViewController.goBack();
                    case "Edit Workout" -> ViewController.setScene(EditWorkoutView.createScene(WorkoutView.workout));
                    case "Save" -> handleSaveAction();
                }
            });
            buttonPane.getChildren().add(newButton);
        }
        buttonPane.setSpacing(20);

        return buttonPane;
    }

    private static void handleSaveAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Workout");
        alert.setHeaderText("Choose an option");
        alert.setContentText("Would you like to save the workout or print it to PDF?");

        ButtonType buttonTypeSave = new ButtonType("Save Workout");
        ButtonType buttonTypePDF = new ButtonType("Print to PDF");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeSave, buttonTypePDF, buttonTypeCancel);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeSave) {
                if (!workout.getIsSaved()) {
                    workout.setIsSaved(true);
                    WorkoutUtils.addWorkout(WorkoutView.workout);
                } else {
                    WorkoutUtils.writeToFile();
                }
                ViewController.goHome();
            } else if (type == buttonTypePDF) {
                try {
                    WorkoutPdfGenerator.saveWorkoutAsPdf(WorkoutView.workout, "workout.pdf");
                    createSuccessSaveDialog();
                } catch (FileNotFoundException e) {
                    // TODO: Show error message to user instead of printing in console
                    System.err.println("Failed to save workout as PDF: " + e.getMessage());
                }
            }
        });
    }
}