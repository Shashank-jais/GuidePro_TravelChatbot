//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        // Get input from the user
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the text to be processed:");
//        String text = scanner.nextLine();
//
//        // Preprocessing preprocessing = new Preprocessing();
//        // text = preprocessing.preprocess(text);
//
//        // Tokenization
//        Tokenize tokenize = new Tokenize();
//        tokenize.tokenizeText(text);
//
//        // Sentence Recognition
//        SentenceRecog sentenceRecog = new SentenceRecog();
//        sentenceRecog.sentenceRecognition(text);
//
//        // NER
//        NER ner = new NER();
//        ner.performNER(text);
//
//        // POS Tagging
//        POS pos = new POS();
//        pos.posTagText(text);
//
//        // Lemmatization
//        Lemmatization lemmatization = new Lemmatization();
//        String lemmatizedText = lemmatization.lemmatizeText(text);
//
//        Intent intent = new Intent();
//        Intent.IntentType classifiedIntent = intent.classifyIntent(lemmatizedText);  // Pass the lemmatized text to Intent classification
//        System.out.println("Classified Intent: " + classifiedIntent);
//
//
//
//        // Sentiment Analysis
//        SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
//        sentimentAnalysis.analyzeSentiment(text);
//    }
//}
