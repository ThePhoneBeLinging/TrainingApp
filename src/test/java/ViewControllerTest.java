import Group15.MainApp;
import Group15.ViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ViewControllerTest
{
    private Scene scene;
    @Before
    public void setUp()
    {
        MainApp mainApp = new MainApp();
        mainApp.start(new Stage());
        var group = new Group();
        Scene scene = new Scene(group);
        ViewController.setScene(scene);
    }

    @Test
    public void testSetUpFunction()
    {
        Assert.assertEquals(ViewController.getScene(), scene);
    }
}
