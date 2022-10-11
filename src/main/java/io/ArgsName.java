package io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key \"" + key + "\" was not found in the database.");
        }
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The arguments are empty");
        }
        Arrays.stream(args)
                .map(s -> {
                    if (!s.startsWith("-")) {
                        throw new IllegalArgumentException("The key is missing");
                    }
                    if (!s.contains("=")) {
                        throw new IllegalArgumentException("The value is missing");
                    }
                    return s;
                })
                .map(s -> s.split("=", 2))
                .map(a -> {
                    if (a[0].substring(1).isEmpty()) {
                        throw new IllegalArgumentException("The key is empty");
                    }
                    if (a[1].isEmpty()) {
                        throw new IllegalArgumentException("The value is empty");
                    }
                    return a;
                })
                .forEach(a -> values.put(a[0].substring(1), a[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}