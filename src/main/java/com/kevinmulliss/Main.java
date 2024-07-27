package com.kevinmulliss;

import com.kevinmulliss.models.Dictionary;
import com.kevinmulliss.models.Message;
import com.kevinmulliss.services.Analyzer;
import com.kevinmulliss.services.DiscordParser;
import com.kevinmulliss.services.LineParser;
import com.kevinmulliss.services.Parser;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

//    private static String INPUT_FILE = "D:\\Discord Output\\Fellow Altamira Employees - main channels - trivia [1090096420016640071].json";
    private static String INPUT_FILE = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\ocdou_line.txt";
//    private static String INPUT_FILE = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\athens.json";
//private static String INPUT_FILE = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\bust.json";
//    private final static String ENGLISH_DICTIONARY = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\usa.txt";
private final static String ENGLISH_DICTIONARY = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\words.txt";
    private final static String AIDS_DICTIONARY = "C:\\Users\\stabb\\IdeaProjects\\discord-parser\\test_data\\aids.txt";


    public static void main(String[] args) {
        try {
            Parser parser = new LineParser();
//            Parser parser = new DiscordParser();
            List<Message> messages = parser.parse(INPUT_FILE);
            Dictionary englishDictionary = new Dictionary(ENGLISH_DICTIONARY, "english");
            Dictionary aidsDictionary = new Dictionary(AIDS_DICTIONARY, "aids");
//            Analyzer.messageShare(messages);
//            Analyzer.wordCheck(messages, englishDictionary, 100);
//            Analyzer.wordCheck(messages, aidsDictionary, 345);
//            Analyzer.messageCount(messages, "soup");
//            Analyzer.firstCheck(messages);
//            Analyzer.lastCheck(messages);
//            Analyzer.blazeCheck(messages);
//            Analyzer.messageLength(messages);
            Analyzer.wordUseByYear(messages, "");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}