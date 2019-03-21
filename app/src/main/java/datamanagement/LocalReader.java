package datamanagement;

import java.util.TreeSet;

import data.Community;
import data.User;
import java.util.HashMap;

public class LocalReader implements Reader {
    private java.util.HashSet<User> users;
    private HashMap<String,Community> communities;

    public LocalReader() {
        users = new java.util.HashSet<>();
        communities = new HashMap<>();

        //initialize dummy data below
        users.add(new User("Prafful", "password1234"));
        users.add(new User("Yunhee", "anotherpassword"));
        users.add(new User("Tasya", "123"));

        communities.put("Community 1", new Community("Community 1", "let's see if this works"));
        communities.put("Community 2", new Community("Community 2", "Cultural, International Group"));
        communities.put("Swingeroos", new Community("Swingeroos", "Swing Dance Group"));
        communities.put("Nacho and Salsa", new Community("Nacho and Salsa", "Salsa Dance"));
        communities.put("Community 3", new Community("Community 3", "Description"));
        communities.put("Community 4", new Community("Community 4", "Description"));
        communities.put("Community 5", new Community("Community 5", "Description"));
        communities.put("Community 6", new Community("Community 6", "Description"));
        communities.put("Community 7", new Community("Community 7", "Description"));
        communities.put("Community 8", new Community("Community 8", "Description"));
        communities.put("Community 9", new Community("Community 9", "Description"));
        communities.put("Community 10", new Community("Community 10", "Description"));
        communities.put("Community 11", new Community("Community 11", "Description"));

    }

    @Override
    public HashMap<String, Community> getCommunities() {
        return communities;
    }

    @Override
    public java.util.HashSet<User> getUsers() {
        return users;
    }
}
