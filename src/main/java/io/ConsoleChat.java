package io;

import java.io.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        String inString = "Bot: Hi! It's console chat. Do you want to talk?";
        System.out.println(inString);
        log.add(inString);
        List<String> answers = readPhrases();
        do {
            Scanner in = new Scanner(System.in);
            inString = in.nextLine();
            log.add("You: " + inString);
            if (STOP.equals(inString)) {
                do {
                    in = new Scanner(System.in);
                    inString = in.nextLine();
                    log.add("You: " + inString);
                } while (!CONTINUE.equals(inString));
            }
            String answer = answers.get(new SecureRandom().nextInt(answers.size()));
            System.out.println("Bot: " + answer);
            log.add("Bot: " + answer);
        } while (!OUT.equals(inString));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            result = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logConsoleChat.txt", "./data/botAnswers.txt");
        cc.run();
    }
}