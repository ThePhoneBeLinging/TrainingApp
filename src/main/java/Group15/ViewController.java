package Group15;

import Group15.Api.ApiUtils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController
{
    private static Stage stage;

    public static void init(Stage stage)
    {
        ApiUtils.getAllExercises();
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

}
