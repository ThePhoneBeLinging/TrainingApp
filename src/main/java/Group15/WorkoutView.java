package Group15;

import Group15.Api.Exercise; //Should be replaced with Workout


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class WorkoutView {

    //Exercise have been used to test the view, should be replaced with workout
    public static Scene createScene(){
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 1000, 800);
    }
}
