package ru.job4j;

import java.util.*;

/**
 * Class implements the main functionality.
 */
public class Handler {
    /**
     * Stores information about a vertex of the users graph.
     */
    private static class User {
        private final String name;
        private final Set<User> linkedUsers;
        boolean isMerged;

        public User(String name, Set<User> linkedUsers) {
            this.name = name;
            this.linkedUsers = linkedUsers;
            this.isMerged = false;
        }

        public String getName() {
            return name;
        }

        public Set<User> getLinkedUsers() {
            return linkedUsers;
        }

        public void addLinkedUser(User user) {
            linkedUsers.add(user);
        }

        public boolean isMerged() {
            return isMerged;
        }

        public void setMerged(boolean isMerged) {
            this.isMerged = isMerged;
        }
    }

    /**
     * Finds all merged users and puts them into the output map.
     * @param inputMap, a map with user's name and their emails.
     * @return a map with merged user's name and their emails.
     */
    public static Map<String, Set<String>> mergeAll(Map<String, Set<String>> inputMap) {
        Map<String, Set<String>> outputMap = new LinkedHashMap<>();
        /*get "adjacency list"*/
        Set<User> users = getUsersAndLinks(inputMap);
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            List<User> mergedUsers = mergeByUser(user);
            Set<String> emails = new LinkedHashSet<>();
            /*collect all the emails from the merged users*/
            for (User u: mergedUsers) {
                emails.addAll(inputMap.get(u.getName()));
                /*delete the already merged user from the initial list of users*/
                users.remove(u);
            }
            outputMap.put(user.name, emails);
            /*get new iterator from the initial list of users*/
            it = users.iterator();
        }
        return outputMap;
    }

    /**
     * Returns a list of users merged by the same emails. Method uses breadth-first search.
     * @param user target user.
     * @return list of merged users ("component" in graph theory).
     */
    private static List<User> mergeByUser(User user) {
        Queue<User> queue = new LinkedList<>();
        List<User> result = new ArrayList<>();
        user.setMerged(true);
        queue.add(user);
        result.add(user);
        while (!queue.isEmpty()) {
            User curUser = queue.remove();
            for (User linkedUser : curUser.getLinkedUsers()) {
                if (!linkedUser.isMerged()) {
                    linkedUser.setMerged(true);
                    queue.add(linkedUser);
                    result.add(linkedUser);
                }
            }
        }
        return result;
    }

    /**
     * Fills the "adjacency list" - set of users with filled linkedUsers field.
     * @param inputMap input data - map of users and their emails.
     * @return "adjacency list" - set of users, each user contains their adjacent users.
     */
    private static Set<User> getUsersAndLinks(Map<String, Set<String>> inputMap) {
        Set<User> setUsers = new LinkedHashSet<>();
        Map<String, User> emailToUser = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> entry: inputMap.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("User name or email's set cannot be null!");
            }
            User user = new User(entry.getKey(), new LinkedHashSet<>());
            for (String email : entry.getValue()) {
                if (email == null) {
                    throw new IllegalArgumentException("Email cannot be null!");
                }
                User existedUser = emailToUser.putIfAbsent(email, user);
                if (existedUser != null) {
                    user.addLinkedUser(existedUser);
                    existedUser.addLinkedUser(user);
                }
            }
            setUsers.add(user);
        }
        return setUsers;
    }
}
