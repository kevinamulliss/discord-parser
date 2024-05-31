package com.kevinmulliss.services;

import com.kevinmulliss.models.Author;
import com.kevinmulliss.models.LineAuthor;
import com.kevinmulliss.models.LineMessage;
import com.kevinmulliss.models.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineParser implements Parser {
    @Override
    public List<Message> parse(String filePath) {
//        Scanner scanner = new Scanner(new FileReader(filePath));

        List<Message> messages = new ArrayList<Message>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Pattern dateRegex = Pattern.compile("^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), \\d\\d/\\d\\d/\\d\\d\\d\\d");
            Pattern messageRegex = Pattern.compile("^\\d\\d:\\d\\d(PM|AM)\\t");
            Pattern inviteRegex = Pattern.compile("^\\d\\d:\\d\\d(PM|AM)\\t\\t");
            DateTimeFormatter lineDateFormatter = DateTimeFormatter.ofPattern("EEE, MM/dd/yyyy hh:mma");

            Map<String, Author> authors = new HashMap<String, Author>();
            Map<String, String> nameMerger  = new HashMap<String, String>();
            nameMerger.put("Blake", "Blake");
            nameMerger.put("Blake Jarrell", "Blake");
            nameMerger.put("fABLE", "Blake");
            nameMerger.put("Sam Keller", "Sam");
            nameMerger.put("William", "William");
            nameMerger.put("Dayton", "Dayton");
            nameMerger.put("Gehrig", "Gehrig");
            nameMerger.put("Gehrig Faircloth", "Gehrig");
            nameMerger.put("Kevin", "Kevin");
            nameMerger.put("Kevin Mulliss", "Kevin");
            nameMerger.put("Conner McGraw", "Conner");
            nameMerger.put("Conner", "Conner");
            nameMerger.put("Conoinoinoinoi", "Conner");
            nameMerger.put("Thegoldenasian", "Kaung");
            nameMerger.put("Oliver Queen", "Kaung");
            nameMerger.put("Dylan \uD83D\uDD25\uD83D\uDD25", "Dylan");
            nameMerger.put("Aidan Kierans (FHS)", "Aidan");
            nameMerger.put("Tim", "Tim");
            nameMerger.put("LeLion", "Josh");
            nameMerger.put("Josh", "Josh");
            nameMerger.put("Joey", "Joey");
            nameMerger.put("Jordan", "Jordan");
            nameMerger.put("ViGeeMan", "Stephen");

            boolean messageStarted = false;
            String dateString = null;
            String timeString = null;
            String runningMessage = null;
            String authorName = null;
            String line;
//            while (in.available() > 0) {
            while ((line = reader.readLine()) != null) {
//                String line = scanner.nextLine();
//                String line = in.readUTF();
                Matcher dateRegexMatcher = dateRegex.matcher(line);
                Matcher messageRegexMatcher = messageRegex.matcher(line);
                Matcher inviteRegexMatcher = inviteRegex.matcher(line);

                boolean isDate = dateRegexMatcher.find();
                boolean isMessage = messageRegexMatcher.find();
                boolean isInvite = inviteRegexMatcher.find();

                // if this message is a date
                if (isDate && !isMessage && !isInvite) {
                    // save the date
                    dateString = dateRegexMatcher.group(0);
                }
                // if this is the end of a message
                if ((!isDate && isMessage && !isInvite && messageStarted) || line.isEmpty()) {
                    // save off message
                    // ensure we have all necessary fields
                    if (dateString != null && timeString != null && runningMessage != null && authorName != null) {
                        // if we haven't seen this author yet, save them
                        if (!authors.containsKey(authorName)) {
                            authors.put(authorName, new LineAuthor(authorName));
                        }

                        // build the time and then use that to create message
                        messages.add(new LineMessage(
                                Date.from(LocalDateTime.parse(dateString + " " + timeString, lineDateFormatter)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()), runningMessage, authors.get(authorName)));
                    }

                    // clear out running values
                    timeString = null;
                    runningMessage = null;
                    authorName = null;
                    messageStarted = false;
                }
                // if this is the start of a message
                if (!isDate && isMessage && !isInvite && !messageStarted) {
                    // ensure that this line has three tab delineated tokens (aka, it is proper message format)
                    String[] tokens = line.split("\\t");
                    if (tokens.length == 3) {
                        timeString = tokens[0];
                        authorName = nameMerger.get(tokens[1]);
                        runningMessage = tokens[2];
                        messageStarted = true;
                    }
                }
                // if this is not an invite and a message has been started
                if (!isInvite && !isMessage && !isDate && messageStarted) {
                    runningMessage += line;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return messages;
    }
}
