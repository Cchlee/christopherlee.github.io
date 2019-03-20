package data;
import java.io.Serializable;
import java.util.*;

public class Community implements Serializable {
    private String name;
    private String description;
    private TreeSet<Project> projects;
    private TreeSet<CommunityMember> members;

    public Community(String name, String description) {
        this.name = name;
        this.description = description;
        projects = new TreeSet<Project>();
        members = new TreeSet<CommunityMember>();
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

    public TreeSet<CommunityMember> getMembers() {
        return members;
    }

    public void setMembers(TreeSet<CommunityMember> members) {
        this.members = members;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return name.equals(community.name);
    }

}
