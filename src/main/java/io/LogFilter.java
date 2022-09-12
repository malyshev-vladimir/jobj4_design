package io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .map(s -> s.split(" "))
                    .filter(s -> s[s.length - 2].equals("404"))
                    .map(s -> String.join(" ", s))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String string: log) {
                out.println(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("src/main/java/io/log.txt");
        save(log, "src/main/java/io/resultOfLogFilterFromLog.txt");
    }
}