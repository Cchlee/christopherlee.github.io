package datamanagement;

import java.util.HashMap;
import java.util.TreeSet;

import data.Community;
import data.User;

public interface Reader {
    java.util.Map<String, Community> getCommunities();
    java.util.Set<User> getUsers();
}
