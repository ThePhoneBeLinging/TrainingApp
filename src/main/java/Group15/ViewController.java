package Group15;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController
{
    private static Stage stage;

    public static void init()
    {
        ViewController.stage = new Stage();
        ViewController.stage.show();
    }
    public static void setScene(Scene scene)
    {
        ViewController.stage.setScene(scene);
    }
}
