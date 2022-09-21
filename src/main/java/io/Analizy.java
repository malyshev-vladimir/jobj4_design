package io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean hasStartLine = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String s = in.readLine();
            String tmpString = "";
            while (s != null) {
                boolean startsWith400or500 = s.startsWith("500") || s.startsWith("400");
                if (!hasStartLine) {
                    if (startsWith400or500) {
                        tmpString = s.substring(s.indexOf(' ') + 1) + ";";
                        hasStartLine = true;
                    }
                } else {
                    if (!startsWith400or500) {
                        out.println(tmpString + s.substring(s.indexOf(' ') + 1) + ";");
                        hasStartLine = false;
                    }
                }
                s = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}