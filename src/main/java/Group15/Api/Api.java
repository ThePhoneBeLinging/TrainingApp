package Group15.Api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Api
{
    public static List<Exercise> getAllExercises() {
            String URL = "https://158.179.205.63/api/v3/get/exercise/all";
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Exercise>>() {
                        });
                        List<Exercise> exercises = response.getBody();

                        return exercises;
            } catch (Exception e) {
                System.out.println("Exception occured: " + e.getMessage());
            }
        return null;
    }
}
