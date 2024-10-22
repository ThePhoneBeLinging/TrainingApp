package Group15.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

public class Api
{
    private static final String URL = "https://158.179.205.63/api/v3/get/exercise/all";
    private static final String FILE_NAME = "exercises.json";

    public static List<Exercise> getAllExercises() {
        File file = new File(FILE_NAME);
        if(file.exists()) {
            return loadFromFile(file);
        }
        else {
            List<Exercise> exercises = getExercisesFromAPI();
            saveToFile(exercises, file);
            return exercises;
        }
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

    private static void saveToFile(List<Exercise> exercises, File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, exercises);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Exercise> loadFromFile(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, new TypeReference<List<Exercise>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
