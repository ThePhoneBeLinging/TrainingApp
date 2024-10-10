package Group15.Api;

import javafx.application.Platform;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.awt.Desktop;
import java.net.URI;

public class Api
{
    public static void getWorkout()
    {
        new Thread(() ->
            {
            String URL = "https://158.179.205.63/api/v3/get/exercise/byBodyPart?bodyPart=Quadriceps&amountOfExercises=5";
            try
            {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Exercise>>()
                        {
                        });
                List<Exercise> exercise = response.getBody();
                Platform.runLater(() ->
                    {
                    System.out.println(exercise.getFirst().title);
                    });
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
            }
            }).start();
    }

    public static void getWorkoutFromInterface(String bodyPart, int exerciseAmout) {
        String uBody = bodyPart.substring(0,1).toUpperCase() + bodyPart.substring(1);
        new Thread(() -> {
            String URL = "https://158.179.205.63/api/v3/get/exercise/byBodyPart?bodyPart=" + uBody + "&amountOfExercises=" + Integer.toString(exerciseAmout);
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Exercise>>() {
                        });
                        List<Exercise> exercises = response.getBody();
                        Platform.runLater(() -> {

                            System.out.println("Exercise: " + exercises.getFirst().title + "\n");
                        });
            } catch (Exception e) {
                System.out.println("Exception occured: " + e.getMessage());
            }

        }).start();
    }
}
