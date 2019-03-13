package processor;

import java.util.*;
import data.Community;
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

    @Override
    public boolean deleteCommunity(Community community) {
        if (communities.containsKey(community.getName())) {
            communities.remove(community.getName());
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteProject(Project project) {
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
    public boolean deleteUser(User user) {
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
