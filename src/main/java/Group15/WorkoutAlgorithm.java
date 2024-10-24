package Group15;

import Group15.Api.Exercise;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class WorkoutAlgorithm {
    private static final String FILE_NAME = "exercises.json";

    public Workout createWorkoutFromExercises(String bodyPart) {
        List<Exercise> exercises = loadExercisesFromFile();
        Workout workout = new Workout();

        if(exercises != null) {
            for(int i = 0; i < exercises.size(); i++) {
                if(exercises.get(i).bodyPart.equals(bodyPart)) {
                    workout.addExercise(exercises.get(i));
                    System.out.println(i + ". " + exercises.get(i).title);
                }
            }
        }
        return workout;
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
