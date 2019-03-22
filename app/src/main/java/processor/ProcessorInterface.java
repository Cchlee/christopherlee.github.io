package processor;

import java.util.TreeSet;

import data.Community;
import data.CommunityMember;
import data.Project;
import data.User;

public interface ProcessorInterface {

    // for all methods, return True if it was successful

    boolean validateLogin(String username, String password);
    java.util.List<Community> getCommunities();
    java.util.Set<User> getUsers();

    boolean createCommunity(Community community);
    boolean createProject(Project project);
    boolean createUser(User user);

    boolean removeCommunity(Community community);
    boolean removeProject(Project project);
    boolean removeUser(User user);

    boolean addUserToCommunity(String community, User user);
    boolean removeUserFromCommunity(String community, User user);

    boolean addUserToProject(String community, User user);
    boolean removeUserFromProject(String community, User user);

    boolean runScheduler(Community community);
    TreeSet<Community> findCommunity(String prefixname);
}
