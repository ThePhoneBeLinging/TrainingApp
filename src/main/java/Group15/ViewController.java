package Group15;

import Group15.Api.BodyPart;
import Group15.Api.ApiUtils;
import Group15.Api.Exercise;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class ViewController
{
    private static Stage stage;

    public static void init(Stage stage)
    {
        ViewController.stage = stage;
        ViewController.stage.setScene(HomeScreenView.createScene());
        ViewController.stage.show();
    }

    public static Scene getScene()
    {
        return ViewController.stage.getScene();
    }

    public static void setScene(Scene scene)
    {
        ViewController.stage.setScene(scene);
    }

    public static Scene createMuscleSelectorScene() {
        VBox vBox = new VBox();
        List<ToggleButton> toggleButtons = new ArrayList<>();

        for (BodyPart bodyPart : BodyPart.values()) {
            ToggleButton toggleButton = new ToggleButton(bodyPart.toString());
            toggleButtons.add(toggleButton);
            vBox.getChildren().add(toggleButton);
        }

        Button submitButton = new Button("Submit");
        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));
        vBox.getChildren().add(submitButton);

        submitButton.setOnAction(e -> {
            List<String> selectedButtonNames = new ArrayList<>();
            for (ToggleButton toggleButton : toggleButtons) {
                if (toggleButton.isSelected()) {
                    ApiUtils.getExercisesFromBodypart(toggleButton.getText(), 1);
                }
            }
            System.out.println("Selected buttons: " + String.join(", ", selectedButtonNames));
        });

        return new Scene(vBox, 600,600);
    }

}
