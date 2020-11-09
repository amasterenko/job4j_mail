package ru.job4j;

import java.util.*;

public class Handler {
    /**
     * Class User is used to store information about vertex of user's graph.
     * Method getUsersAndLinks fills the "adjacency list" linkedUsers for each user.
     * Method mergeByUser returns a list of users merged by the same emails (it uses breadth-first search).
     * Method mergeAll finds all merged users and puts them into the output map.
     */
    private static class User {
        private String name;
        private Set<User> linkedUsers;
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

    public static Map<String, Set<String>> mergeAll(Map<String, Set<String>> inputMap) {
        Map<String, Set<String>> outputMap = new LinkedHashMap<>();
        Set<User> users = getUsersAndLinks(inputMap);
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            List<User> mergedUsers = mergeByUser(user);
            Set<String> emails = new LinkedHashSet<>();
            for (User u: mergedUsers) {
                emails.addAll(inputMap.get(u.getName()));
                users.remove(u);
            }
            outputMap.put(user.name, emails);
            it = users.iterator();
        }
        return outputMap;
    }

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

    private static Set<User> getUsersAndLinks(Map<String, Set<String>> inputMap) {
        Set<User> setUsers = new LinkedHashSet<>();
        Map<String, User> emailToUser = new LinkedHashMap<>();
        for (Map.Entry<String, Set<String>> entry: inputMap.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("User name or email's set can not be null!");
            }
            User user = new User(entry.getKey(), new LinkedHashSet<>());
            for (String email : entry.getValue()) {
                if (email == null) {
                    throw new IllegalArgumentException("Email can not be null!");
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
