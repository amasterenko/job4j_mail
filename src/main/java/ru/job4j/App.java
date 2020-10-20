package ru.job4j;

import java.io.*;
import java.util.*;

public class App {
    private final Map<String, Set<String>> inputMap = new LinkedHashMap<>();
    private Map<String, Set<String>> outputMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Specify input file file path as a parameter!");
            return;
        }
        App app = new App();
        app.readInputFile(args[0]);
        app.outputMap = Handler.mergeAll(app.inputMap);
        app.writeResult();
    }

    private void writeResult() {
        File file = new File("result.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Set<String>> e : outputMap.entrySet()) {
                writer.write(e.getKey() + "->");
                writer.write(String.join(",", e.getValue()));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readInputFile(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] userAndEmails = line.split("->");
                if (userAndEmails.length != 2) {
                    throw new IllegalArgumentException("Wrong format of the input file!");
                }
                Set<String> emails = new LinkedHashSet<>(Arrays.asList(userAndEmails[1].split(",")));
                String user = userAndEmails[0];
                inputMap.put(user, emails);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
