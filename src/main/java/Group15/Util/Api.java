package Group15.Util;

import Group15.Model.BodyPart;
import Group15.Model.Exercise;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Api
{
    private static final String URL = "https://158.179.205.63/api/v3/get/exercise/all";

    private static List<Exercise> exercises;
    private static List<Exercise> abdominal = new ArrayList<>();
    private static List<Exercise> biceps = new ArrayList<>();
    private static List<Exercise> calves = new ArrayList<>();
    private static List<Exercise> chest = new ArrayList<>();
    private static List<Exercise> forearms = new ArrayList<>();
    private static List<Exercise> glutes = new ArrayList<>();
    private static List<Exercise> hamstrings = new ArrayList<>();
    private static List<Exercise> lowerBack = new ArrayList<>();
    private static List<Exercise> quadriceps = new ArrayList<>();
    private static List<Exercise> shoulders = new ArrayList<>();
    private static List<Exercise> triceps = new ArrayList<>();
    private static List<Exercise> upperBack = new ArrayList<>();

    static
    {
        getAllExercises();
    }

    public static List<Exercise> getAllExercises()
    {
        if (exercises == null)
        {
            exercises = getExercisesFromAPI();
            sortExercises();
        }
        return exercises;
    }

    public static List<Exercise> getExercisesForBodyPart(BodyPart bodyPart) {
        switch (bodyPart) {
            case Abdominal -> { return abdominal; }
            case Biceps -> { return biceps; }
            case Calves -> { return calves; }
            case Chest -> { return chest; }
            case Forearms -> { return forearms; }
            case Glutes -> { return glutes; }
            case Hamstrings -> { return hamstrings; }
            case LowerBack -> { return lowerBack; }
            case Quadriceps -> { return quadriceps; }
            case Shoulders -> { return shoulders; }
            case Triceps -> { return triceps; }
            case UpperBack -> { return upperBack; }
            default -> { return List.of(); }
        }
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

    private static void sortExercises() {
        for (Exercise exercise : exercises) {
            switch (exercise.bodyParts.getFirst()) {
                case Abdominal -> abdominal.add(exercise);
                case Biceps -> biceps.add(exercise);
                case Calves -> calves.add(exercise);
                case Chest -> chest.add(exercise);
                case Forearms -> forearms.add(exercise);
                case Glutes -> glutes.add(exercise);
                case Hamstrings -> hamstrings.add(exercise);
                case LowerBack -> lowerBack.add(exercise);
                case Quadriceps -> quadriceps.add(exercise);
                case Shoulders -> shoulders.add(exercise);
                case Triceps -> triceps.add(exercise);
                case UpperBack -> upperBack.add(exercise);
            }
        }
    }
}