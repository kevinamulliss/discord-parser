package com.kevinmulliss;

import com.kevinmulliss.models.Message;
import com.kevinmulliss.services.Parser;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    private static String INPUT_FILE = "D:\\Discord Output\\Fellow Altamira Employees - main channels - trivia [1090096420016640071].json";

    public static void main(String[] args) {
        try {
            List<Message> messages = Parser.parseMessages(INPUT_FILE);
            for (Message message : messages) {
                System.out.println(message.getAuthor().getNickname() + ": " + message.getContent());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}