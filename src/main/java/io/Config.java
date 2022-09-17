package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> s.length() != 0 && !s.startsWith("#"))
                    .map(s -> s.split("=", 2))
                    .map(line -> {
                        if (line.length != 2) {
                            throw new IllegalArgumentException();
                        } else if (line[0].trim().length() == 0) {
                            throw new IllegalArgumentException();
                        }  else if (line[1].trim().length() == 0) {
                            throw new IllegalArgumentException();
                        } else {
                            return line;
                        }
                    })
                    .forEach(l -> values.put(l[0].trim(), l[1].trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}