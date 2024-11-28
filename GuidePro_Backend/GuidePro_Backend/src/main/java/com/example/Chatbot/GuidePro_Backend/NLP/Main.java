package com.example.Chatbot.GuidePro_Backend.NLP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String LOCATION_CACHE_FILE = "locationCache.txt"; // File to store the destination
    private static String cachedLocation = ""; // Only store one destination

//    public static void main(String[] args) {
//        // Load cached location from the file
//        loadLocationCache();
//
//        // Display cached destination from the last run, if available
//        if (!cachedLocation.isEmpty()) {
//            System.out.println("Previous destination: " + cachedLocation);
//        } else {
//            System.out.println("No previous destination found.");
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter the text to be processed:");
//        String text = scanner.nextLine();
//
//        NLPResult result = processNLP(text);
//
//        System.out.println("-----------------------NLP Result-----------------------");
//        System.out.println(result);
//
//        // Save the updated destination (if any) to the file
//        saveLocationCache();
//
//        scanner.close();
//    }

    public NLPResult processNLP(String text) {
        // Tokenization
        Tokenize tokenize = new Tokenize();
        tokenize.tokenizeText(text);

        // Sentence Recognition
        SentenceRecog sentenceRecog = new SentenceRecog();
        sentenceRecog.sentenceRecognition(text);

        // Named Entity Recognition (NER) - Get locations
        NER ner = new NER();
        List<String> locations = ner.getLocations(text);

        // Update the cached location (only one location stored)
        if (!locations.isEmpty()) {
            cachedLocation = locations.get(0);  // Only store the first location (overwrite existing)
        }

        // Lemmatization
        Lemmatization lemmatization = new Lemmatization();
        String lemmatizedText = lemmatization.lemmatizeText(text);

        // Intent Classification
        Intent intent = new Intent();
        Intent.IntentType classifiedIntent = intent.classifyIntent(lemmatizedText);

        // Create and return the NLPResult
        NLPResultHandler resultHandler = new NLPResultHandler();
        List<String> locationList = new ArrayList<>();
        locationList.add(cachedLocation); // Add the most recent destination
        resultHandler.setNLPResult(locationList, classifiedIntent);

        return resultHandler.getNLPResult();
    }

    private static void loadLocationCache() {
        File file = new File(LOCATION_CACHE_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine(); // Only read the first line
                if (line != null) {
                    cachedLocation = line.trim(); // Store the cached location
                }
            } catch (IOException e) {
                System.err.println("Error loading location cache: " + e.getMessage());
            }
        }
    }

    private static void saveLocationCache() {
        // Save only the latest destination (cachedLocation)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION_CACHE_FILE))) {
            if (!cachedLocation.isEmpty()) {
                writer.write(cachedLocation); // Write only the most recent destination
            }
        } catch (IOException e) {
            System.err.println("Error saving location cache: " + e.getMessage());
        }
    }
}
