//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import java.util.List;
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//
//public class Lemmatization {
//    // Method to perform lemmatization and return lemmatized string
//    public String lemmatizeText(String text) {
//        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
//        CoreDocument coreDocument = new CoreDocument(text);
//        stanfordCoreNLP.annotate(coreDocument);
//        List<CoreLabel> coreLabelList = coreDocument.tokens();
//
//        StringBuilder lemmatizedText = new StringBuilder();
//
//        System.out.println("-----------------------Lemmatizing-----------------------");
//        for (CoreLabel coreLabel : coreLabelList) {
//            String lemma = coreLabel.get(CoreAnnotations.LemmaAnnotation.class);
//            lemmatizedText.append(lemma).append(" ");
//            System.out.println(coreLabel.originalText() + " = " + lemma);
//        }
//
//        // Return the lemmatized string
//        return lemmatizedText.toString().trim();
//    }
//}
