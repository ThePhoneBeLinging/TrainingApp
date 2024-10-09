package Group15;

import Group15.Api.BodyPart;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewController
{
    private static Stage stage;
    private static final int MAX_BUTTON_SELECTION = 5;

    public static void init(Stage stage)
    {
        ViewController.stage = new Stage();
        setScene(createMuscleSelector());
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

    public static Scene createMuscleSelector() {
        VBox vBox = new VBox();
        List<ToggleButton> toggleButtons = new ArrayList<>();

        for (BodyPart bodyPart : BodyPart.values()) {
            ToggleButton toggleButton = new ToggleButton(bodyPart.toString());
            toggleButtons.add(toggleButton);
            vBox.getChildren().add(toggleButton);

            toggleButton.setOnAction(e -> {
                long amountSelected = toggleButtons.stream().filter(ToggleButton::isSelected).count();

                if(amountSelected > MAX_BUTTON_SELECTION) {
                    toggleButton.setSelected(false);
                    System.out.println("You have exceeded max selection: " + MAX_BUTTON_SELECTION);
                }
            });
        }

        Button submitButton = new Button("Submit");
        VBox.setMargin(submitButton, new Insets(50, 0, 0, 0));
        vBox.getChildren().add(submitButton);

        submitButton.setOnAction(e -> {
            List<String> selectedButtonNames = new ArrayList<>();
            for (ToggleButton toggleButton : toggleButtons) {
                if (toggleButton.isSelected()) {
                    selectedButtonNames.add(toggleButton.getText().toUpperCase());
                }
            }
            System.out.println("Selected buttons: " + String.join(", ", selectedButtonNames));
        });

        return new Scene(vBox, 600,600);
    }
}
