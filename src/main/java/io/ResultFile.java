package io;

import java.io.FileOutputStream;
import java.io.IOException;


public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("src/main/java/io/resultOfResultFile.txt")) {
            out.write(multiple(5).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String multiple(int size) {
        String result = "";
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                result = result + (row + 1) * (cell + 1) + " ";
            }
            result = result + System.lineSeparator();
        }
        return result;
    }
}