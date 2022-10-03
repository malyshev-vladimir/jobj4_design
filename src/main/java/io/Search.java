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
        if (args.length < 2 || args[0].isBlank() || args[1].isBlank() || !args[1].startsWith(".")) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
}