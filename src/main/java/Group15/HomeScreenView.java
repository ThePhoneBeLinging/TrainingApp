package Group15;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeScreenView {

    private static String title = "Workout App";
    private static String[] buttons = {"New Workout", "My Workouts", "Exit"};

    public static Scene getScene() {
        VBox layout = new VBox();
        layout.setSpacing(250);

        Pane titlePane = createTitlePane();
        layout.getChildren().add(titlePane);

        Pane buttonPane = createButtonPane();
        layout.getChildren().add(buttonPane);

        return new Scene(layout, 1000, 800);
    }

    private static Pane createTitlePane() {
        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titlePane.getChildren().add(titleLabel);
        return titlePane;
    }

    private static Pane createButtonPane() {
        VBox buttonPane = new VBox();
        buttonPane.setAlignment(Pos.CENTER);
        for (String button : buttons) {
            Button newButton = new Button(button);
            newButton.setPrefSize(200, 50);
            newButton.setOnAction(e -> {
                if (button.equals("New Workout")) {
                    System.out.println("New Workout pressed");
                } else if (button.equals("My Workouts")) {
                    System.out.println("My Workouts pressed");
                } else if (button.equals("Exit")) {
                    System.exit(0);
                }
            });

            buttonPane.getChildren().add(newButton);
            buttonPane.setSpacing(20);
        }

        return buttonPane;
    }

}
