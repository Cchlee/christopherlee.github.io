package data;

import java.util.Objects;

public class TimeSlot {
    private int startingTime; // All times are in 24 hour mode
    private int duration;

    public TimeSlot(int startingTime, int duration) {
        this.startingTime = startingTime;
        this.duration = duration;
    }

    public int getEndingTime() {
        return startingTime + duration;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return startingTime == timeSlot.startingTime &&
                duration == timeSlot.duration;
    }

}
