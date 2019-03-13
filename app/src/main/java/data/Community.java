package data;
import java.util.*;

public class Community {
    private String name;
    private String description;
    private TreeSet<Project> projects;
    private TreeSet<User> users;

    public Community(String name, String description) {
        this.name = name;
        this.description = description;
        projects = new TreeSet<Project>();
        users = new TreeSet<User>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TreeSet<Project> getProjects() {
        return projects;
    }

    public void setProjects(TreeSet<Project> projects) {
        this.projects = projects;
    }

    public TreeSet<User> getUsers() {
        return users;
    }

    public void setUsers(TreeSet<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return name.equals(community.name);
    }

}
