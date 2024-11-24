//package com.example.Chatbot.GuidePro_Backend.NLP;
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//
//import java.util.List;
//
//public class NER {
//
//    // Method that performs NER on the input text
//    public void performNER(String text) {
//
//        // Get the StanfordCoreNLP pipeline
//        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
//
//        // Create a CoreDocument with the input text
//        CoreDocument coreDocument = new CoreDocument(text);
//
//        // Annotate the document with NER
//        stanfordCoreNLP.annotate(coreDocument);
//
//        // Get the list of tokens (words) from the document
//        List<CoreLabel> coreLabels = coreDocument.tokens();
//
//        System.out.println("-----------------------Named Entity Recognition-----------------------");
//        // Iterate over each token and print the word with its named entity
//        for (CoreLabel coreLabel : coreLabels) {
//
//            String namedEntity = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//            System.out.println(coreLabel.originalText() + " = " + namedEntity);
//        }
//    }
//}
