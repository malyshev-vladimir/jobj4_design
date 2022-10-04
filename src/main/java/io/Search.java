package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        if (!args[0].equals(".")) {
            throw new IllegalArgumentException("The first argument is not a start-folder.");
        }
        if (!args[1].startsWith(".") || args[1].substring(1).isBlank()) {
            throw new IllegalArgumentException("The second argument is not in the correct format");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}