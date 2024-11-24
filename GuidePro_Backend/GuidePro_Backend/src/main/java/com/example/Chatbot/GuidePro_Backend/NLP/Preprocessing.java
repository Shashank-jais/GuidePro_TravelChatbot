package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Preprocessing {

    // Set of stop words
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
        "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", 
        "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", 
        "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", 
        "theirs", "themselves", "what", "which", "who", "whom", "this", "that", 
        "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", 
        "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", 
        "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", 
        "at", "by", "for", "with", "about", "against", "between", "into", "through", 
        "during", "before", "after", "above", "below", "to", "from", "up", "down", 
        "in", "out", "on", "off", "over", "under", "again", "further", "then", 
        "once", "here", "there", "when", "where", "why", "how", "all", "any", 
        "both", "each", "few", "more", "most", "other", "some", "such", "no", 
        "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", 
        "t", "can", "will", "just", "don", "should", "now"
    ));

    // Regular expression to match non-word characters
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("[^\\w\\s]");

    public String preprocess(String text) {

        System.out.println("Input String: "+ text);
        System.out.println("");

        // Convert text to lowercase
        String lowerCaseText = text.toLowerCase();
        
        // Remove non-word characters
        String cleanedText = NON_WORD_PATTERN.matcher(lowerCaseText).replaceAll("");

        // Split the cleaned text into words
        String[] words = cleanedText.split("\\s+");

        // Filter out stop words
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!STOP_WORDS.contains(word) && !word.isEmpty()) {
                result.append(word).append(" ");
            }
        }

        System.out.println("Preprocessed String: "+ result.toString().trim());
        System.out.println("");
        return result.toString().trim(); // Return the processed text
    }
}
