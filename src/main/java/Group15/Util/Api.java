package Group15.Util;

import Group15.Model.Exercise;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Api
{
    private static final String URL = "https://158.179.205.63/api/v3/get/exercise/all";

    private static List<Exercise> exercises;

    static
    {
        getAllExercises();
    }

    public static List<Exercise> getAllExercises()
    {
        if (exercises == null)
        {
            exercises = getExercisesFromAPI();
        }
        return exercises;
    }

    private static List<Exercise> getExercisesFromAPI()
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Exercise>>()
                    {
                    });
            if (response.getBody() != null) {
                return response.getBody();
            } else {
                System.out.println("API returned no exercises.");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while fetching exercises: " + e.getMessage());
            e.printStackTrace();
        }
        return List.of();
    }

}
