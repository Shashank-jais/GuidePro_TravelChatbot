package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class NER {

    // Method to perform NER and return entities classified as LOCATION or CITY
    public List<String> getLocations(String text) {

        // Get the StanfordCoreNLP pipeline
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        // Create a CoreDocument with the input text
        CoreDocument coreDocument = new CoreDocument(text);

        // Annotate the document with NER
        stanfordCoreNLP.annotate(coreDocument);

        // Get the list of tokens (words) from the document
        List<CoreLabel> coreLabels = coreDocument.tokens();

        // List to store entities tagged as LOCATION or CITY
        List<String> locations = new ArrayList<>();

        System.out.println("-----------------------Named Entity Recognition-----------------------");

        // Iterate over each token and print the word alongside its entity
        for (CoreLabel coreLabel : coreLabels) {
            String word = coreLabel.originalText(); // The actual word
            String namedEntity = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class); // Its entity tag

            // Print all entities recognized
            System.out.println(word + " = " + namedEntity);

            // Add to the list if the entity is LOCATION or CITY
            if ("LOCATION".equals(namedEntity) || "CITY".equals(namedEntity)) {
                locations.add(word);
            }
        }

        return locations;
    }
}
