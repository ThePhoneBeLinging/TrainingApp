package Group15;

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
    private static HBox hBox;
    private static VBox vBox;
    private static String muscleString = "";
    private ArrayList<String> musclesSelected = new ArrayList<>();

    public static void init(Stage stage)
    {
        hBox = new HBox(20);
        vBox = new VBox(30);
        TextField txtField = new TextField();
        Button btn = new Button("Add Muscle");
        btn.setOnAction(e -> {
            muscleString = String.valueOf(txtField.getText());

        });
        hBox.getChildren().addAll(txtField,btn);
        vBox.getChildren().add(hBox);
        Scene sc = new Scene(hBox, 300, 300);
        stage.setScene(sc);
        stage.show();
        //ViewController.stage = new Stage();
        //ViewController.stage.show();
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
