package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static String directory;
    private static String exclude;
    private static String output;

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        directory = argsName.get("d");
        exclude = argsName.get("e");
        output = argsName.get("o");
        if (!Files.exists(Paths.get(directory))) {
            throw new IllegalArgumentException("The directory to archive was not found.");
        }
        if (!exclude.startsWith(".") || exclude.chars().filter(Character::isLetter).count() != exclude.length() - 1) {
            throw new IllegalArgumentException("The exclusion extension was not found.");
        }
        if (output.length() <= 5 || !output.endsWith(".zip")) {
            throw new IllegalArgumentException("The archive file is not named.");
        }
    }

    public static List<File> searchFiles(String[] args) throws IOException {
        checkArgs(args);
        return Search
                .search(Paths.get(directory), p -> !p.toFile().getName().endsWith(exclude))
                .stream()
                .map(Path::toFile)
                .toList();
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        Zip zipAll = new Zip();
        zipAll.packFiles(searchFiles(args), new File(output));
    }
}