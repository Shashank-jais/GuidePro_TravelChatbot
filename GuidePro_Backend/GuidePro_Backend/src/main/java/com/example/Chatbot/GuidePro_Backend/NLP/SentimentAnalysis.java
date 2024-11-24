//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.CoreSentence;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//
//import java.util.List;
//
//public class SentimentAnalysis {
//
//    // Method to analyze sentiment of the input text
//    public void analyzeSentiment(String text) {
//        // Get the StanfordCoreNLP pipeline from the Pipeline class
//        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
//
//        // Create a CoreDocument with the input text
//        CoreDocument coreDocument = new CoreDocument(text);
//
//        // Annotate the document with the pipeline
//        stanfordCoreNLP.annotate(coreDocument);
//
//        // Get the list of sentences in the document
//        List<CoreSentence> sentences = coreDocument.sentences();
//
//        System.out.println("-----------------------Sentiment Analysis-----------------------");
//        // Iterate through the sentences and print each sentiment and sentence
//        for (CoreSentence sentence : sentences) {
//            String sentiment = sentence.sentiment();
//            System.out.println("Sentiment: " + sentiment + "\tSentence: " + sentence.text());
//        }
//    }
//}
