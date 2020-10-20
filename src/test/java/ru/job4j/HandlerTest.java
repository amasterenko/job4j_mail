package ru.job4j;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class HandlerTest {
    @Test
    public void whenMergeAllToOneUser() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2", "email3"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email2", "email4"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email3", "email5"));
        Set<String> emails4 = new HashSet<>(Arrays.asList("email2", "email6", "email3"));
        Set<String> emails5 = new HashSet<>(Arrays.asList("email6"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        inputMap.put("user4", emails4);
        inputMap.put("user5", emails5);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2", "email3", "email4", "email5", "email6"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenMergeAllToOneUser2() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2", "email3"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email2", "email7"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email4", "email5"));
        Set<String> emails4 = new HashSet<>(Arrays.asList("email8", "email5", "email7"));
        Set<String> emails5 = new HashSet<>(Arrays.asList("email4"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        inputMap.put("user4", emails4);
        inputMap.put("user5", emails5);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2", "email3", "email7", "email4", "email5", "email8"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenMergeAllToOneUser3() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email3", "email4"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email3", "email5"));
        Set<String> emails4 = new HashSet<>(Arrays.asList("email6", "email7"));
        Set<String> emails5 = new HashSet<>(Arrays.asList("email8", "email7", "email5", "email1"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        inputMap.put("user4", emails4);
        inputMap.put("user5", emails5);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2", "email3", "email4", "email5", "email6", "email7", "email8"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenNoUsersToMerge() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email3", "email4"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email5", "email6"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2"));
            put("user2", Set.of("email3", "email4"));
            put("user3", Set.of("email5", "email6"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenNoUsersToMerge2() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email2"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email3"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1"));
            put("user2", Set.of("email2"));
            put("user3", Set.of("email3"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenMergeAllTo2Users() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email3", "email4", "email5"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email6", "email7"));
        Set<String> emails4 = new HashSet<>(Arrays.asList("email4", "email2"));
        Set<String> emails5 = new HashSet<>(Arrays.asList("email7", "email8"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        inputMap.put("user4", emails4);
        inputMap.put("user5", emails5);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2", "email3", "email4", "email5"));
            put("user3", Set.of("email6", "email7", "email8"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

    @Test
    public void whenMergeAllTo3Users() {
        Set<String> emails1 = new HashSet<>(Arrays.asList("email1", "email2"));
        Set<String> emails2 = new HashSet<>(Arrays.asList("email3", "email2"));
        Set<String> emails3 = new HashSet<>(Arrays.asList("email4", "email5"));
        Set<String> emails4 = new HashSet<>(Arrays.asList("email6", "email4"));
        Set<String> emails5 = new HashSet<>(Arrays.asList("email7", "email8"));
        Map<String, Set<String>> inputMap = new LinkedHashMap<>();
        inputMap.put("user1", emails1);
        inputMap.put("user2", emails2);
        inputMap.put("user3", emails3);
        inputMap.put("user4", emails4);
        inputMap.put("user5", emails5);
        Map<String, Set<String>> expectedMap = new LinkedHashMap<>() {{
            put("user1", Set.of("email1", "email2", "email3"));
            put("user3", Set.of("email4", "email5", "email6"));
            put("user5", Set.of("email7", "email8"));
        }};
        assertThat(Handler.mergeAll(inputMap), is(expectedMap));
    }

}