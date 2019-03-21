package data;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String displayName;
    private String password;
    private boolean isLeader;
    private HashMap<String, List<Integer>> schedule; // schedule is represented as a mapping of the
                                                    // day of the week to the list of available hours


    public User(String displayName, String password, boolean isLeader) {
        this.displayName = displayName;
        this.password = password;
        this.isLeader = isLeader;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword(){return password;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return displayName.equals(user.displayName);
    }

    public HashMap<String, List<Integer>> getSchedule() {
        return schedule;
    }

    public void setSchedule(HashMap<String, List<Integer>> schedule) {
        this.schedule = schedule;
    }
}
