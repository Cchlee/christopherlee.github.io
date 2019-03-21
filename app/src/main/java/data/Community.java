package data;
import com.example.scheduler.R;

import java.io.Serializable;
import java.util.*;

public class Community implements Serializable {
    private int imageResource;
    private String name;
    private String description;
    private TreeSet<Project> projects;
    private TreeSet<CommunityMember> members;

    public Community(String name, String description) {
        setImageResource();
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

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(){
        ArrayList<Integer> listOfVectors = new ArrayList<>();
        listOfVectors.add(R.drawable.ic_people_black);
        listOfVectors.add(R.drawable.ic_public_black);
        listOfVectors.add(R.drawable.ic_camera_black_24dp);
        listOfVectors.add(R.drawable.ic_business_center_black_24dp);
        imageResource = listOfVectors.get((int)(Math.random() * listOfVectors.size()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Community community = (Community) o;
        return name.equals(community.name);
    }

}
