package Group15.Api;

import javafx.application.Platform;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ApiUtils
{
    public static List<Exercise> getExercisesFromBodypart(String bodyPart, int exerciseAmount) {
        String upperCaseBodyPart = bodyPart.substring(0,1).toUpperCase() + bodyPart.substring(1);

            String URL = "https://158.179.205.63/api/v3/get/exercise/byBodyPart?bodyPart=" + upperCaseBodyPart + "&amountOfExercises=" + exerciseAmount;
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Exercise>>() {
                        });
                        List<Exercise> exercises = response.getBody();
                        System.out.println(exercises);
                        return exercises;
            } catch (Exception e) {
                System.out.println("Exception occured: " + e.getMessage());
            }
        return null;
    }
}
