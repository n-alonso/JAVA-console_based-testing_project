package org.example.persistence;

import org.example.models.User;

import java.util.*;

public class UserRepository {

    /**
     * 2 Maps are used to enforce uniqueness of both id and email.
     * This behaviour is not incorporated in the equals() method of the User model because it could be difficult to
     * enforce uniqueness of both keys.
     * Map was chosen for uniqueness instead of Set for O(1) lookup complexity.
     * LinkedHashMap implementation is chosen to preserve the order in which they were added.
     */

    private static UserRepository instance;
    private static Map<Integer, User> usersById;
    private static Map<String, User> usersByEmail;

    private UserRepository() {
        usersById = new LinkedHashMap<>();
        usersByEmail = new LinkedHashMap<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    public String addUser(User user) {
        if (usersById.containsKey(user.getId())) return "This id is already taken.";
        if (usersByEmail.containsKey(user.getEmail())) return "This email is already taken.";

        usersById.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
        return "The student has been added.";
    }

    public void removeUser(User user) {
        usersById.remove(user.getId());
        usersByEmail.remove(user.getEmail());
    }

    public boolean updateUserById(User user) {
        if (usersById.containsKey(user.getId()) && usersByEmail.containsKey(user.getEmail())) {
            usersById.put(user.getId(), user);
            usersByEmail.put(user.getEmail(), user);
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return usersById.get(id);
    }

    public List<User> getUsers() {
        List<User> users = new LinkedList<>();

        for (Map.Entry<Integer, User> row : usersById.entrySet()) {
            users.add(row.getValue());
        }

        return users;
    }

    public int getUsersCount() {
        return usersById.size();
    }
}
