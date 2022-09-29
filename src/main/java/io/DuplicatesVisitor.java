package io;

import io.duplicates.FileProperty;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> allFilesWithoutDuplicates = new HashSet<>();
    private final List<FileProperty> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!allFilesWithoutDuplicates.add(fileProperty)) {
            duplicates.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }

    public void outputDuplicates() {
        duplicates.forEach(duplicate -> System.out.printf("File %s is duplicated.%n", duplicate.getName()));
    }
}