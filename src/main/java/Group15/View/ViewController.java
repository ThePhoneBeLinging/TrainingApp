package Group15.View;

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
        ViewController.stage.setHeight(800);
        ViewController.stage.setWidth(1000);
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
