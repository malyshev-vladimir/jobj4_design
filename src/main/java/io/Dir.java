package io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/malyshevvova/Documents/MyProjects/projects/");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            String[] s = subfile.getAbsoluteFile().toString().split("/");
            System.out.println(s[s.length - 1] + " - size: " + subfile.length());
        }
    }
}