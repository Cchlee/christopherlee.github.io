package datamanagement;

import java.util.TreeSet;

import data.Community;
import data.User;
import java.util.HashMap;

public class LocalReader implements Reader {
    private TreeSet<User> users;
    private HashMap<String,Community> communities;
    private Reader reader;

    public LocalReader(Reader reader) {
        users = new TreeSet<>();
        communities = new HashMap<>();

        //initialize dummy data below
        users.add(new User("Prafful", "password1234"));
        users.add(new User("Yunhee", "anotherpassword"));
        users.add(new User("Tasya", "123"));

        communities.put("Test Community", new Community("Test Community", "let's see if this works"));

    }

    @Override
    public HashMap<String, Community> getCommunities() {
        return communities;
    }

    @Override
    public TreeSet<User> getUsers() {
        return users;
    }
}
