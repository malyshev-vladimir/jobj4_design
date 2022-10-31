package io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVReader {
    public static void validate(String source, String delimiter, String target, String filter) {
        Path path = Paths.get(source);
        if (source == null || source.isEmpty() || !Files.exists(path) || !path.toFile().isFile()) {
            throw new IllegalArgumentException("Отсутствует файл для чтения");
        } else if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Oтсутствуют название файла для записи");
        } else if (delimiter == null || delimiter.isEmpty()) {
            throw new IllegalArgumentException("Oтсутствуют разделитель файла для чтения");
        } else if (filter == null || filter.isEmpty()) {
            throw new IllegalArgumentException("Oтсутствуют фильтры, необходимые для выделения столбцов."
                    + " Проверти правильно ли указан разделитель.");
        }
    }

    public static void handle(ArgsName argsName) {
        String source = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String target = argsName.get("out");
        String filter = argsName.get("filter");
        validate(source, delimiter, target, filter);

        String[] filters = filter.split(",");
        int[] indexes = new int[filters.length];

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             Scanner scanner = new Scanner(Paths.get(source))) {
            while (scanner.hasNextLine()) {
                StringBuilder builder = new StringBuilder();
                String[] lines = scanner.nextLine().split(delimiter);
                for (int i = 0; i < filters.length; i++) {
                    for (int j = 0; j < lines.length; j++) {
                        if (filters[i].equals(lines[j])) {
                            indexes[i] = j;
                            break;
                        }
                    }
                }
                for (int i = 0; i < indexes.length; i++) {
                    builder.append(lines[indexes[i]])
                            .append(i == indexes.length - 1 ? "" : delimiter);
                }
                if ("stdout".equals(target)) {
                    System.out.println(builder);
                } else {
                    out.println(builder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}