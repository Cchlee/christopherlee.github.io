package data;
import java.io.Serializable;
import java.util.*;

public class Project implements Serializable {
    private String name;
    private String communityCreatedIn;
    private String description;
    private int timeslot;
    private Map<String, Integer> roles; // role types and number of people per role required
    private TreeSet<User> users;
    private Map<String, List<User>> userAssignment;

    public Project(String name, String description, String communityCreatedIn,
                   Map<String, Integer> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
        this.communityCreatedIn = communityCreatedIn;
        users = new TreeSet<User>();
        userAssignment = new HashMap<String, List<User>> ();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }

    public Map<String, Integer> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Integer> roles) {
        this.roles = roles;
    }

    public TreeSet<User> getUsers() {
        return users;
    }

    public void setUsers(TreeSet<User> users) {
        this.users = users;
    }

    public Map<String, List<User>> getUserAssignment() {
        return userAssignment;
    }

    public void setUserAssignment(Map<String, List<User>> userAssignment) {
        this.userAssignment = userAssignment;
    }

    public String getCommunityCreatedIn() {
        return communityCreatedIn;
    }

    public void setCommunityCreatedIn(String communityCreatedIn) {
        this.communityCreatedIn = communityCreatedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return name.equals(project.name) &&
                communityCreatedIn.equals(project.communityCreatedIn);
    }

}
