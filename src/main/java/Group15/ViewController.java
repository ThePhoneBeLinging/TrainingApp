package Group15;

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
        int buttonAmount = 7;
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox,650,650);
        for(int i = 0; i < buttonAmount; i++) {
            Button btn = new Button("Button " + i);
            vbox.getChildren().add(btn);
        }


        return scene;
    }
}
