package io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class LogFilter {
    public List<String> filter(String file) {
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(out::println);
    }
}