package model;

import java.io.Serializable;

public abstract class WorkoutPartBase implements Serializable {

    private int duration;

    public WorkoutPartBase(int p_duration) {
        this.duration = p_duration;
    }

    public int getDuration() {
        return duration;
    }

    public abstract String getActivity_type();
}
