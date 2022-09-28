package io;

import io.duplicates.FileProperty;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> allFilesWithoutDuplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!allFilesWithoutDuplicates.add(fileProperty)) {
            System.out.printf("File %s is duplicated.%n", fileProperty.getName());
        }
        return super.visitFile(file, attrs);
    }

    public static void main(String[] args) throws IOException {
        Path files = Paths.get("/Users/malyshevvova/Documents/MyProjects/projects/");
        Files.walkFileTree(files, new DuplicatesVisitor());
    }
}