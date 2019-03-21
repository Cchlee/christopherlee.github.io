package processor;

import java.util.*;
import data.Community;
import data.CommunityMember;
import data.Project;
import data.User;
import datamanagement.Reader;

public class Processor implements ProcessorInterface {
    private TreeSet<User> users;
    private HashMap<String, Community> communities;

    public Processor(Reader reader) {
        users = reader.getUsers();
        communities = reader.getCommunities();
    }

    @Override
    public boolean createCommunity(Community community) {
        if (communities.containsKey(community.getName())) {
            return false;
        }

        communities.put(community.getName(), community);
        return true;
    }

    @Override
    public boolean createProject(Project project) {
        String projectCommunity = project.getCommunityCreatedIn();
        if (communities.containsKey(projectCommunity)) {
            Community community = communities.get(projectCommunity);
            community.addProject(project);
            communities.put(projectCommunity, community);
            return true;
        }

        return false;
    }

    @Override
    public boolean createUser(User user) {
        if (users.contains(user)) {
            return false;
        }

        users.add(user);
        return true;
    }


    // Transforms User to Community member depending on how user preferences are recorded ->
    // see method in CommunityMember called addPreference
    @Override
    public boolean addUserToCommunity(String community, User user) {
        return false;
    }

    @Override
    public boolean removeUserFromCommunity(String community, User user) {
        return false;
    }

    @Override
    public boolean addUserToProject(String community, User user) {
        return false;
    }

    @Override
    public boolean removeUserFromProject(String community, User user) {
        return false;
    }

    @Override
    public boolean removeCommunity(Community community) {
        if (communities.containsKey(community.getName())) {
            communities.remove(community.getName());
            return true;
        }

        return false;
    }

    @Override
    public boolean removeProject(Project project) {
        String projectCommunity = project.getCommunityCreatedIn();
        if (communities.containsKey(projectCommunity)) {
            Community community = communities.get(projectCommunity);
            community.removeProject(project);
            communities.put(projectCommunity, community);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean runScheduler(Community community) {
        return false;
    }

    @Override
    public TreeSet<Community> findCommunity(String prefixname) {
        TreeSet<Community> output = new TreeSet<>();
        for (Map.Entry<String, Community> entry : communities.entrySet()) {
            String name = entry.getKey();
            Community community = entry.getValue();

            if (name.startsWith(prefixname)) {
                output.add(community);
            }
        }
        return output;
    }
}
