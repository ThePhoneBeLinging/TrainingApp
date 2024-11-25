package Group15.MockData;

import Group15.Model.Workout;

import java.util.ArrayList;
import java.util.List;

public class MockWorkouts {
    private List<Workout> workouts = new ArrayList<>();
    private Workout workout1 = new Workout();
    private Workout workout2 = new Workout();

    public MockWorkouts() {
        workout1.setName("Workout 1");
        workout2.setName("Workout 2");
        workouts.add(workout1);
        workouts.add(workout2);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
