package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProjectWrapper
{
    public Set<CommunityMember> assignment;
    public int time;
    public int timeWanted;
    public String role;
    public Project project;

    public ProjectWrapper()
    {
        assignment =new HashSet<CommunityMember>();
    }
    public ProjectWrapper(Project p, String r)
    {
        assignment = new HashSet<CommunityMember>();
        project = p;
        role = r;
        timeWanted = p.getRoles().get(r).intValue();
    }

    public ProjectWrapper(int t, Project p, String r)
    {
        assignment = new HashSet<CommunityMember>();
        time = t;
        project = p;
        role = r;
    }

    public void addPerson(CommunityMember person)
    {
        assignment.add(person);
    }

    public void addPeople(Set<CommunityMember> people)
    {
        assignment.addAll(people);
    }
}