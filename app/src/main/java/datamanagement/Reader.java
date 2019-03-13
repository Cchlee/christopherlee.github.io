package datamanagement;

import java.util.HashMap;
import java.util.TreeSet;

import data.Community;
import data.User;

public interface Reader {
    HashMap<String, Community> getCommunities();
    TreeSet<User> getUsers();
}
