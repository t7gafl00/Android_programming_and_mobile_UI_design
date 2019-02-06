package model;

public class PausePart extends WorkoutPartBase {

    public PausePart(int duration) {
        super(duration);
    }

    @Override
    public String getActivity_type() {
        return "Pause";
    }
}
