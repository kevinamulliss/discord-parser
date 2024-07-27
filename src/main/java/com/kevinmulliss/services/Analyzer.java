package com.kevinmulliss.services;

import com.kevinmulliss.models.Author;
import com.kevinmulliss.models.Dictionary;
import com.kevinmulliss.models.Message;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.*;

public class Analyzer {

    public static void messageCount(List<Message> messages, String word) {
        Map<Author, Integer> wordCount = new HashMap<Author, Integer>();
        Map<Author, Integer> messageCount = new HashMap<Author, Integer>();
        double totalMessageCount = 0;

        for (Message message : messages) {
            int multiTokenCheck = 0;
            int singleTokenCheck = 0;

            for (String token : Analyzer.getWords(message.getContent())) {
                if (token.toLowerCase().equals(word.toLowerCase())) {
                    singleTokenCheck++;
                }
            }

            if (message.getContent().toLowerCase().contains(word.toLowerCase())) {
                totalMessageCount++;
                if (wordCount.containsKey(message.getAuthor())) {
                    wordCount.put(message.getAuthor(), wordCount.get(message.getAuthor()) + 1);
                } else {
                    wordCount.put(message.getAuthor(), 1);
                }
            }

            if (messageCount.containsKey(message.getAuthor())) {
                messageCount.put(message.getAuthor(), messageCount.get(message.getAuthor()) + 1);
            } else {
                messageCount.put(message.getAuthor(), 1);
            }
        }

        Map<Author, Integer> sortedWordCount = Analyzer.sortByValue(wordCount);
        Map<Author, Integer> sortedMessageCount = Analyzer.sortByValue(messageCount);

        System.out.println("word: " + word + "(used " + totalMessageCount + "  times)");
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.printf("%-22s%-22s%-22s%-22s\n","name","count","percent of uses", "percent of user messages");
        for (Author author : sortedWordCount.keySet()) {
            double percentWord = (double) sortedWordCount.get(author) / totalMessageCount * 100.0;
            double percentOwn = (double) sortedWordCount.get(author) / (double) sortedMessageCount.get(author) * 100.0;

            System.out.printf("%-22s%-22s%-22s%-22s\n", author.getName(), sortedWordCount.get(author), df.format(percentWord), df.format(percentOwn));

        }

        System.out.println("---------------------------------------");
    }

    private static Map<Author, Integer> sortByValue(Map<Author, Integer> map) {
        List<Map.Entry<Author, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<Author, Integer>comparingByValue().reversed());

        Map<Author, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Author, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private static Map<Integer, Integer> sortIntegerMapByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());

        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static void printAuthorMessages(List<Message> messages, String username) {
        for (Message message : messages) {
            if (message.getAuthor().getName().equals(username)) {
                System.out.println(message.getAuthor().getName() + ":\t" + message.getContent());
            }
        }
    }

    public static void messageShare(List<Message> messages) {
        Map<Author, Integer> messageCounts = new HashMap<Author, Integer>();
        int messageCount = 0;
        for (Message message : messages) {
            messageCount++;
            if (messageCounts.containsKey(message.getAuthor())) {
                messageCounts.put(message.getAuthor(), messageCounts.get(message.getAuthor()) + 1);
            } else {
                messageCounts.put(message.getAuthor(), 1);
            }
        }
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.printf("%-22s%-22s%-22s\n","name","messages/total","percent");
        for (Author author : messageCounts.keySet()) {
            double percentWord = (double) messageCounts.get(author) / (double) messageCount * 100.0;
            System.out.printf("%-22s%-22s%-22s\n", author.getName(), messageCounts.get(author) + "/" + messageCount, df.format(percentWord));
        }
        System.out.println("---------------------");
    }

    public static void wordCheck(List<Message> messages, Dictionary dictionary, int n) {
//        Dictionary aidsDictionary = new Dictionary("aids");
        int count = 0;
        for (Message message : messages) {
            for (String token : Analyzer.getWords(message.getContent())) {
                count++;
                if (dictionary.containsWord(token)) {
                    dictionary.countWord(token);
                }
            }
        }
        Analyzer.printUses(dictionary, n, count);
//        Analyzer.printUses(aidsDictionary, n, count);
    }

    private static void printUses(Dictionary dictionary, int n, int count) {
        Map<String, Integer> sortedDictionaryCounts = dictionary.sorted();
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.println("Top " + n + " most used words in " + dictionary.getName() + " dictionary:");
        System.out.printf("%-22s%-22s%-22s%-22s\n","word","uses","% of dict words used", "% of all words used");
        int i = 1;
        for (String word : sortedDictionaryCounts.keySet()) {
            double percentDict = (double) sortedDictionaryCounts.get(word) / (double) dictionary.getTotalCount() * 100.0;
            double percentAll = (double) sortedDictionaryCounts.get(word) / (double) count * 100.0;
            System.out.printf("%-22s%-22s%-22s%-22s\n", i + ". " + word, sortedDictionaryCounts.get(word), df.format(percentDict), df.format(percentAll));

            i++;
            if (i > n) {
                break;
            }
        }

        System.out.println("-------------------");
    }

    public static void blazeCheck(List<Message> messages) {
        Map<Author, Integer> blazeCounts = new HashMap<Author, Integer>();
        Map<Author, Integer> trueBlazeCounts = new HashMap<Author, Integer>();
        Map<Author, Integer> perfectBlazeCounts = new HashMap<Author, Integer>();

        double blazeCount = 0;
        double trueBlazeCount = 0;
        double perfectBlazeCount = 0;

        for (Message message : messages) {
            boolean blazeCheck = message.getContent().toLowerCase().contains("blaze");
            boolean timeCheck = message.getTimestamp().getHours() == 16 && message.getTimestamp().getMinutes() == 20;
            if (blazeCheck) {
                blazeCounts.merge(message.getAuthor(), 1, Integer::sum);
                blazeCount++;
            }

            if (timeCheck) {
                trueBlazeCounts.merge(message.getAuthor(), 1, Integer::sum);
                trueBlazeCount++;
            }

            if (blazeCheck && timeCheck) {
                perfectBlazeCounts.merge(message.getAuthor(), 1, Integer::sum);
                perfectBlazeCount++;
            }
        }

        Map<Author, Integer> sortedBlazes = Analyzer.sortByValue(blazeCounts);
        Map<Author, Integer> sortedTrueBlazes = Analyzer.sortByValue(trueBlazeCounts);
        Map<Author, Integer> sortedPerfectBlazes = Analyzer.sortByValue(perfectBlazeCounts);

        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("Blaze Stats:");
        System.out.printf("%-10s%-22s%-22s%-22s%-22s\n","name","blazes","true blazes", "perfect blazes", "accuracy");
        for (Author author : sortedBlazes.keySet()) {
            double authorBlazes = sortedBlazes.getOrDefault(author, 0);
            double authorTrueBlazes = sortedTrueBlazes.getOrDefault(author, 0);
            double authorPerfectBlazes = sortedPerfectBlazes.getOrDefault(author, 0);
            double percentBlaze = authorBlazes / blazeCount * 100.0;
            double percentTrueBlaze = authorTrueBlazes / trueBlazeCount * 100.0;
            double percentPerfectBlaze = authorPerfectBlazes / perfectBlazeCount * 100.0;
            double percentAccurate = authorPerfectBlazes / authorBlazes * 100.0;
            System.out.printf("%-10s%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t\n",
                    author.getName(),
                    (int) authorBlazes, (int) blazeCount, df.format(percentBlaze),
                    (int) authorTrueBlazes, (int) trueBlazeCount, df.format(percentTrueBlaze),
                    (int) authorPerfectBlazes, (int) perfectBlazeCount, df.format(percentPerfectBlaze),
                    (int) authorPerfectBlazes, (int) authorBlazes, df.format(percentAccurate));
        }
    }

    public static void lastCheck(List<Message> messages) {
        // keep track of what day we're looking at
        int currentDate = messages.get(0).getTimestamp().getDate();

        // how many times each person has said first
        Map<Author, Integer> firsts = new HashMap<Author, Integer>();
        // how many times someone actually got the first message
        Map<Author, Integer> trueFirsts = new HashMap<Author, Integer>();
        // how many times someone go the first message with the text "first" in the message
        Map<Author, Integer> perfectFirsts = new HashMap<Author, Integer>();

        double firstCount = 0;
        double trueFirstCount = 0;
        double perfectFirstCount = 0;

        // first message in chat a true first, but not be a perfect first or contain first text
        trueFirsts.put(messages.get(0).getAuthor(), 1);
        trueFirstCount++;
        if (messages.get(0).getContent().contains("last")) {
            firsts.put(messages.get(0).getAuthor(), 1);
            perfectFirsts.put(messages.get(0).getAuthor(), 1);
            firstCount++;
            perfectFirstCount++;
        }

        List<Message> reversedMessages = new ArrayList<Message>(messages);
        Collections.reverse(reversedMessages);
        for (Message message : reversedMessages) {
            // save off whether this message contained first at all
            boolean saidFirst = message.getContent().toLowerCase().contains("last");
            if (saidFirst) {
                firsts.merge(message.getAuthor(), 1, Integer::sum);
                firstCount++;
            }
            // if we have a new day, we have a true first
            if (message.getTimestamp().getDate() != currentDate) {
                // track that we've moved on to a new day
                currentDate = message.getTimestamp().getDate();
                // increment or store true first
                trueFirsts.merge(message.getAuthor(), 1, Integer::sum);
                trueFirstCount++;
                // if they actually said first, then its a perfect first as well
                if (saidFirst) {
                    perfectFirsts.merge(message.getAuthor(), 1, Integer::sum);
                    perfectFirstCount++;
                }
            }
        }

        Map<Author, Integer> sortedFirsts = Analyzer.sortByValue(firsts);
        Map<Author, Integer> sortedTrueFirsts = Analyzer.sortByValue(trueFirsts);
        Map<Author, Integer> sortedPerfectFirsts = Analyzer.sortByValue(perfectFirsts);

        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("Last Stats:");
        System.out.printf("%-10s%-22s%-22s%-22s%-22s\n","name","lasts","true lasts", "perfect lasts", "accuracy");
        for (Author author : sortedFirsts.keySet()) {
            double authorFirsts = sortedFirsts.getOrDefault(author, 0);
            double authorTrueFirsts = sortedTrueFirsts.getOrDefault(author, 0);
            double authorPerfectFirsts = sortedPerfectFirsts.getOrDefault(author, 0);
            double percentFirst = authorFirsts / firstCount * 100.0;
            double percentTrueFirst = authorTrueFirsts / trueFirstCount * 100.0;
            double percentPerfectFirst = authorPerfectFirsts / perfectFirstCount * 100.0;
            double percentAccurate = authorPerfectFirsts / authorFirsts * 100.0;
            String firstString = authorFirsts + "/" + firstCount + " (" + df.format(percentFirst) + ")";
            String trueFirstString = authorTrueFirsts + "/" + trueFirstCount + " (" + df.format(percentTrueFirst) + ")";
            String perfectFirstString = authorPerfectFirsts + "/" + perfectFirstCount + " (" + df.format(percentPerfectFirst) + ")";
            String accurateString = authorTrueFirsts + "/" + authorFirsts + " (" + df.format(percentAccurate) + ")";
            System.out.printf("%-10s%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t\n",
                    author.getName(),
                    (int) authorFirsts, (int) firstCount, df.format(percentFirst),
                    (int) authorTrueFirsts, (int) trueFirstCount, df.format(percentTrueFirst),
                    (int) authorPerfectFirsts, (int) perfectFirstCount, df.format(percentPerfectFirst),
                    (int) authorPerfectFirsts, (int) authorFirsts, df.format(percentAccurate));
        }
    }

    public static void wordUseByYear(List<Message> messages, String word) {
        Map<Integer, Integer> usesByYear = new HashMap<Integer, Integer>();
        Map<Integer, Integer> wordsByYear = new HashMap<Integer, Integer>();
        int totalUses = 0;
        int totalWords = 0;

        DecimalFormat df = new DecimalFormat("00.00");


        for (Message message : messages) {
            for (String messageWord : Analyzer.getWords(message.getContent())) {
                if (messageWord.toLowerCase().contains(word.toLowerCase())) {
                    usesByYear.merge(message.getTimestamp().getYear(), 1, Integer::sum);
                    totalUses++;
                }

                wordsByYear.merge(message.getTimestamp().getYear(), 1, Integer::sum);
                totalWords++;
            }
        }

        System.out.println("word use over time stats for " + word);
        System.out.printf("%-10s%-10s%-20s%-20s\n", "year", "count", "% of uses", "% of year's words");

        for (Integer year : usesByYear.keySet()) {
            double percentUse = (double) usesByYear.get(year) / (double) totalUses * 100.0;
            double percentYear = (double) usesByYear.get(year) / (double) wordsByYear.get(year) * 100.0;
            System.out.printf("%-10s%-10s%-20s%-20s\n", 1900 + year, usesByYear.get(year) , df.format(percentUse), df.format(percentYear));
        }
    }

    public static void firstCheck(List<Message> messages) {
        // keep track of what day we're looking at
        int currentDate = messages.get(0).getTimestamp().getDate();

        // how many times each person has said first
        Map<Author, Integer> firsts = new HashMap<Author, Integer>();
        // how many times someone actually got the first message
        Map<Author, Integer> trueFirsts = new HashMap<Author, Integer>();
        // how many times someone go the first message with the text "first" in the message
        Map<Author, Integer> perfectFirsts = new HashMap<Author, Integer>();

        double firstCount = 0;
        double trueFirstCount = 0;
        double perfectFirstCount = 0;

        // first message in chat a true first, but not be a perfect first or contain first text
        trueFirsts.put(messages.get(0).getAuthor(), 1);
        trueFirstCount++;
        if (messages.get(0).getContent().contains("first")) {
            firsts.put(messages.get(0).getAuthor(), 1);
            perfectFirsts.put(messages.get(0).getAuthor(), 1);
            firstCount++;
            perfectFirstCount++;
        }

        for (Message message : messages) {
            // save off whether this message contained first at all
            boolean saidFirst = message.getContent().toLowerCase().contains("first");
            if (saidFirst) {
                firsts.merge(message.getAuthor(), 1, Integer::sum);
                firstCount++;
            }
            // if we have a new day, we have a true first
            if (message.getTimestamp().getDate() != currentDate) {
                // track that we've moved on to a new day
                currentDate = message.getTimestamp().getDate();
                // increment or store true first
                trueFirsts.merge(message.getAuthor(), 1, Integer::sum);
                trueFirstCount++;
                // if they actually said first, then its a perfect first as well
                if (saidFirst) {
                    perfectFirsts.merge(message.getAuthor(), 1, Integer::sum);
                    perfectFirstCount++;
                }
            }
        }

        Map<Author, Integer> sortedFirsts = Analyzer.sortByValue(firsts);
        Map<Author, Integer> sortedTrueFirsts = Analyzer.sortByValue(trueFirsts);
        Map<Author, Integer> sortedPerfectFirsts = Analyzer.sortByValue(perfectFirsts);

        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("First Stats:");
        System.out.printf("%-10s%-22s%-22s%-22s%-22s\n","name","firsts","true firsts", "perfect firsts", "accuracy");
        for (Author author : sortedFirsts.keySet()) {
            double authorFirsts = sortedFirsts.getOrDefault(author, 0);
            double authorTrueFirsts = sortedTrueFirsts.getOrDefault(author, 0);
            double authorPerfectFirsts = sortedPerfectFirsts.getOrDefault(author, 0);
            double percentFirst = authorFirsts / firstCount * 100.0;
            double percentTrueFirst = authorTrueFirsts / trueFirstCount * 100.0;
            double percentPerfectFirst = authorPerfectFirsts / perfectFirstCount * 100.0;
            double percentAccurate = authorPerfectFirsts / authorFirsts * 100.0;
            String firstString = authorFirsts + "/" + firstCount + " (" + df.format(percentFirst) + ")";
            String trueFirstString = authorTrueFirsts + "/" + trueFirstCount + " (" + df.format(percentTrueFirst) + ")";
            String perfectFirstString = authorPerfectFirsts + "/" + perfectFirstCount + " (" + df.format(percentPerfectFirst) + ")";
            String accurateString = authorTrueFirsts + "/" + authorFirsts + " (" + df.format(percentAccurate) + ")";
            System.out.printf("%-10s%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t%4s/%s (%s%%)\t\n",
                    author.getName(),
                    (int) authorFirsts, (int) firstCount, df.format(percentFirst),
                    (int) authorTrueFirsts, (int) trueFirstCount, df.format(percentTrueFirst),
                    (int) authorPerfectFirsts, (int) perfectFirstCount, df.format(percentPerfectFirst),
                    (int) authorPerfectFirsts, (int) authorFirsts, df.format(percentAccurate));
        }
    }

    public static void messageLength(List<Message> messages) {
        Map<Integer, Integer> lengths = new HashMap<Integer, Integer>();
        for (Message message : messages) {
            lengths.merge(Analyzer.getWords(message.getContent()).size(), 1, Integer::sum);
            if (Analyzer.getWords(message.getContent()).size() == 68) {
                System.out.println("wow!");
            }
        }

        Map<Integer, Integer> sortedLengths = Analyzer.sortIntegerMapByValue(lengths);
        for (Integer key : sortedLengths.keySet()) {
            System.out.println("message word length: " + key + "\t message frequency: " + sortedLengths.get(key));
        }
    }

    private static List<String> getWords(String text) {
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex));
            }
        }

        return words;
    }
}
