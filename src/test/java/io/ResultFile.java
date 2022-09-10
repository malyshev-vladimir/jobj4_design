package io;

import java.io.FileOutputStream;


public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(multiple(5).getBytes());
        } catch (Exception e) {
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