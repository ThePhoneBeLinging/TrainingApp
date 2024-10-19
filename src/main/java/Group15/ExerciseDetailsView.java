package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class ExerciseDetailsView {

    public static Scene createScene() {
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().add(createExerciseImagePane());
        layout.getChildren().add(createExerciseInfoPane());
        layout.getChildren().add(createButtonPane());

        return new Scene(layout, 1000, 800);
    }

    private static VBox createExerciseImagePane() {
        VBox imagePane = new VBox();
        return imagePane;
    }

    private static VBox createExerciseInfoPane() {
        VBox infoPane = new VBox();
        return infoPane;
    }

    private static VBox createButtonPane() {
        VBox buttonPane = new VBox();
        return buttonPane;
    }
}