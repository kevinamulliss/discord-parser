package com.kevinmulliss.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {

    private Set<String> words;
    private Map<String, Integer> counts;
    private int totalCount;
    private String name;

    public Dictionary() {
        this(new HashSet<String>(), new HashMap<String, Integer>(), "dictionary");
    }

    public Dictionary(Set<String> words, Map<String, Integer> counts, String name) {
        this.words = words;
        this.counts = counts;
        this.name = name;
    }

    public Dictionary(String name) {
        this(new HashSet<String>(), new HashMap<String, Integer>(), name);
    }

    public Dictionary(String filePath, String name) throws FileNotFoundException {
        this(name);
        Scanner dictionaryScanner = new Scanner(new File(filePath));
        while (dictionaryScanner.hasNextLine()) {
            this.addWord(dictionaryScanner.nextLine());
        }
    }

    public String getName() {
        return name;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void countWord(String word) {
        if (this.words.contains(word.toLowerCase())) {
            this.counts.put(word.toLowerCase(), this.counts.get(word.toLowerCase()) + 1);
            this.totalCount++;
        }
    }

    public void addWord(String word) {
        if (!this.words.contains(word.toLowerCase())) {
            this.words.add(word.toLowerCase());
            this.counts.put(word.toLowerCase(), 0);
        }
    }

    public int getDictionarySize() {
        return this.words.size();
    }

    public int getWordCount(String word) {
        return this.counts.get(word.toLowerCase());
    }

    public boolean containsWord(String word) {
        return this.words.contains(word.toLowerCase());
    }
    public void printAll() {
        for (String word : this.words) {
            if (this.counts.containsKey(word.toLowerCase())) {
                int count = this.counts.get(word.toLowerCase());
                if (count > 1) {
                    System.out.println(word + ": " + count);
                }

            }
        }
    }

    public Map<String, Integer> sorted() {
        return this.sortByValue(this.counts);
    }

    private Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
