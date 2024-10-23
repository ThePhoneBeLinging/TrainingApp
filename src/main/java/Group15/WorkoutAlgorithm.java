package Group15;

import Group15.Api.Api;
import Group15.Api.Exercise;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class WorkoutAlgorithm {
    private static final String FILE_NAME = "exercises.json";

    //TODO: Should return a workout, when class is implemented
    public void createWorkoutFromExercises(String bodyPart) {
        List<Exercise> exercises = loadExercisesFromFile();

        if(exercises != null) {
            for(Exercise exercise : exercises) {
                if(exercise.bodyPart.equals(bodyPart)) {
                    System.out.println("Found Body Part!");
                }
            }
        }
    }

    private static List<Exercise> loadExercisesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(FILE_NAME);

        try {
            return objectMapper.readValue(file, new TypeReference<List<Exercise>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
