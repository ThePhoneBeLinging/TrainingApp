package Group15;

import Group15.Api.BodyPart;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewController
{
    private static Stage stage;

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
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox,650,650);
        for(BodyPart bodyPart : BodyPart.values()) {
            Button btn = new Button(bodyPart.toString());
            btn.setOnAction(e -> {
                System.out.println("I am body part: " + bodyPart.toString());
            });
            vbox.getChildren().add(btn);
        }


        return scene;
    }
}
