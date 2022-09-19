package io;

import java.io.*;

import static java.lang.System.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean hasStartLine = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String s = in.readLine();
            String tmpString = "";
            while (s != null) {
                if (!hasStartLine && (s.startsWith("500") || s.startsWith("400"))) {
                    tmpString = s.substring(s.indexOf(' ') + 1) + ";";
                    hasStartLine = true;
                }
                if (hasStartLine && (s.startsWith("300") || s.startsWith("200"))) {
                    tmpString += s.substring(s.indexOf(' ') + 1) + ";" + lineSeparator();
                    out.print(tmpString);
                    hasStartLine = false;
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