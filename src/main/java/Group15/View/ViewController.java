package Group15.View;

import Group15.Api.ApiUtils;

import Group15.Model.BodyPart;
import Group15.Model.Equipment;
import Group15.Model.Exercise;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class ViewController
{
    private static Stage stage;

    public static void init(Stage stage)
    {
        ApiUtils.getAllExercises();
        ViewController.stage = stage;
        ViewController.stage.setHeight(800);
        ViewController.stage.setWidth(1000);
        ViewController.stage.setScene(ExerciseDetailsView.createScene(new Exercise(
                "Bench Press",
                "A compound exercise that targets the chest, shoulders, and triceps.",
                "Strength",
                Arrays.asList(BodyPart.Chest, BodyPart.Shoulders, BodyPart.Triceps),
                Arrays.asList(Equipment.Barbell),
                "Intermediate",
                "/images/benchPress.png",
                60
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
