package io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    static ArgsName checkedArgs;

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
        checkedArgs = ArgsName.of(args);
        if (!checkedArgs.getValues().containsKey("d")) {
            throw new IllegalArgumentException("The directory, which we want to archive, was not found.");
        }
        if (!checkedArgs.getValues().containsKey("e")) {
            throw new IllegalArgumentException("The exclusion extension was not found.");
        }
        if (!checkedArgs.getValues().containsKey("o")) {
            throw new IllegalArgumentException("The archive file is not named.");
        }
    }

    public static List<File> searchFiles(String[] args) throws IOException {
        checkArgs(args);
        return Search
                .search(Paths.get(checkedArgs.get("d")), p -> !p.toFile().getName().endsWith(checkedArgs.get("e")))
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
        zipAll.packFiles(searchFiles(args), new File("./zipAll.zip"));
    }
}