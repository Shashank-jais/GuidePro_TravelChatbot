//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.CoreSentence;
//
//import java.util.List;
//
//public class SentenceRecog {
//    // Method to perform sentence recognition
//    public void sentenceRecognition(String text) {
//        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
//        CoreDocument coreDocument = new CoreDocument(text);
//        stanfordCoreNLP.annotate(coreDocument);
//        List<CoreSentence> sentences = coreDocument.sentences();
//
//        System.out.println("-----------------------Sentence Recognition-----------------------");
//        for (CoreSentence sentence : sentences) {
//            System.out.println(sentence.toString());
//        }
//    }
//}