package ru.job4j;

import java.io.*;
import java.util.*;

/**
 * Reads an input file, invokes the handler method, writes results to a text file.
 */
public class App {
    private final Map<String, Set<String>> inputMap = new LinkedHashMap<>();
    private Map<String, Set<String>> outputMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please, specify input file path as a parameter!");
            return;
        }
        App app = new App();
        app.readInputFile(args[0]);
        app.outputMap = Handler.mergeAll(app.inputMap);
        app.writeResult();
        System.out.println("Done. The results can be found inside the file result.txt .");
    }

    private void writeResult() {
        File file = new File("result.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, Set<String>> e : outputMap.entrySet()) {
                writer.write(e.getKey() + ",");
                writer.write(String.join(",", e.getValue()));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void readInputFile(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] userAndEmails = line.split(",");
                if (userAndEmails.length < 2) {
                    throw new IllegalArgumentException(
                            "Wrong format of the input file! Must be comma separated csv (user1,email1,...,emailN)");
                }
                Set<String> emails = new LinkedHashSet<>(
                        Arrays.asList(Arrays.copyOfRange(userAndEmails, 1, userAndEmails.length)));
                String user = userAndEmails[0];
                inputMap.put(user, emails);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
