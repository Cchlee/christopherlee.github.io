package data;

import java.util.HashMap;
import java.util.Map;

public class CommunityMember extends User {
    private Map<String, Integer> preferences;

    public CommunityMember(String displayName, String password) {
        super(displayName, password);
        this.preferences = new HashMap<>();
    }

    public CommunityMember(String displayName, String password, Map<String, Integer> preferences) {
        super(displayName, password);
        this.preferences = preferences;
    }

    public boolean addPreference(String roleType, int hours) {
        preferences.put(roleType, hours);
        return true;
    }

    public boolean removePreference(String roleType) {
        preferences.remove(roleType);
        return true;
    }


}