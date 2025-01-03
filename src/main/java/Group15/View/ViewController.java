package Group15.View;

import Group15.Util.Api;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class ViewController
{
    private static Stage stage;
    private static Stack<Scene> navigationStack = new Stack<>();

    public static void init(Stage stage)
    {
        Api.getAllExercises();
        ViewController.stage = stage;
        ViewController.stage.setHeight(800);
        ViewController.stage.setWidth(1000);
        navigationStack.push(HomeScreenView.createScene());
        ViewController.stage.setScene(navigationStack.peek());
        ViewController.stage.show();
    }

    public static Scene getScene()
    {
        return stage.getScene();
    }

    public static void setScene(Scene scene)
    {
        navigationStack.push(scene);
        stage.setScene(navigationStack.peek());
    }

    public static void applyChanges(Scene scene)
    {
        navigationStack.pop();
        navigationStack.pop();
        navigationStack.push(scene);
        stage.setScene(navigationStack.peek());
    }

    public static void goBack()
    {
        navigationStack.pop();
        stage.setScene(navigationStack.peek());
    }

    public static void goHome()
    {
        navigationStack.clear();
        navigationStack.push(HomeScreenView.createScene());
        stage.setScene(navigationStack.peek());
    }

    public static void updateScene(Scene scene)
    {
        navigationStack.pop();
        navigationStack.push(scene);
        stage.setScene(navigationStack.peek());
    }
}
