package io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("src/main/java/io/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int num = Integer.parseInt(line);
                if (num % 2 == 0) {
                    System.out.println(line + " - even");
                } else {
                    System.out.println(line + " - not even");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}