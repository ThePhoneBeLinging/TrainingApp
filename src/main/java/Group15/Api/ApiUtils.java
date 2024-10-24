package Group15.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

public class ApiUtils
{
    private static final String URL = "https://158.179.205.63/api/v3/get/exercise/all";

    public static List<Exercise> getAllExercises() {
        return getExercisesFromAPI();
    }

    private static List<Exercise> getExercisesFromAPI() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Exercise>>() {
                    });
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
        return null;
    }
}
