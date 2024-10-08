package Group15;

import Group15.Api.Api;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Api.getWorkout();
        ViewController.init();
    }
}
