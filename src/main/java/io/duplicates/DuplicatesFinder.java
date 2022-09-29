package io.duplicates;

import io.DuplicatesVisitor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Paths.get("/Users/malyshevvova/Documents/MyProjects/projects/"), duplicatesVisitor);
        duplicatesVisitor.outputDuplicates();
    }
}