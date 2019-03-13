package processor;

import java.util.TreeSet;

import data.Community;
import data.Project;
import data.User;

public interface ProcessorInterface {

    // for all methods, return True if it was successful

    boolean createCommunity(Community community);
    boolean createProject(Project project);
    boolean createUser(User user);

    boolean deleteCommunity(Community community);
    boolean deleteProject(Project project);
    boolean deleteUser(User user);

    boolean runScheduler(Community community);
    TreeSet<Community> findCommunity(String prefixname);
}
