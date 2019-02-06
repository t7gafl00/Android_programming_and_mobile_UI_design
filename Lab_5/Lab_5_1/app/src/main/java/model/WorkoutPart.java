package model;

public class WorkoutPart extends WorkoutPartBase {

    public WorkoutPart(int p_duration) {
        super(p_duration);
    }

    @Override
    public String getActivity_type() {
        return "Workout";
    }
}
