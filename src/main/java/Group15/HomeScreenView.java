package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class HomeScreenView {

    private String title = "Workout App";
    private static String[] buttons = {"New Workout", "My Workouts", "Exit"};

    public static Scene getScene() {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(createButtons());
        return new Scene(layout, 1000, 800);
    }

    private static Pane createButtons() {
        VBox buttonLayout = new VBox();
        buttonLayout.setAlignment(Pos.CENTER);
        for (String button : buttons) {
            buttonLayout.getChildren().add(new Label(button));
            buttonLayout.setSpacing(20);
        }

        return buttonLayout;
    }

}
