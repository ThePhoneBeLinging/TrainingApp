package Group15;

import Group15.Api.Exercise;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController
{
    private static Stage stage;

    public static void init()
    {
        ViewController.stage = new Stage();
        ViewController.stage.setScene(ExerciseDetailsView.createScene(new Exercise(
                "Bench Press",
                "This is a description of bench press. It is a compound exercise that works multiple muscle groups including the chest, shoulders, and triceps. Just press Bro!",
                "Compound",
                "Chest, Triceps, Shoulders",
                "Barbell, Bench",
                "Intermediate",
                "file:src/main/resources/benchPress.png"
        )));
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
