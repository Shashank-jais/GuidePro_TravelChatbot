//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import java.util.List;
//
//public class POS {
//    // Method to perform Part-of-Speech tagging
//    public void posTagText(String text) {
//        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
//        CoreDocument coreDocument = new CoreDocument(text);
//        stanfordCoreNLP.annotate(coreDocument);
//        List<CoreLabel> coreLabelList = coreDocument.tokens();
//
//        System.out.println("-----------------------Part of Speech Tagging-----------------------");
//        for (CoreLabel coreLabel : coreLabelList) {
//
//            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//            System.out.println(coreLabel.originalText() + " = " + pos);
//        }
//    }
//}
