package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Tokenize {

    // Method to tokenize input text
    public void tokenizeText(String text) {
        // Get the StanfordCoreNLP pipeline from the Pipeline class
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

        // Create a CoreDocument with the input text
        CoreDocument coreDocument = new CoreDocument(text);

        // Annotate the document with the pipeline
        stanfordCoreNLP.annotate(coreDocument);

        // Get the list of tokens (CoreLabel objects)
        List<CoreLabel> coreLabelList = coreDocument.tokens();

        // Print tokenization results
        System.out.println("-----------------------Tokenization-----------------------:");
        for (CoreLabel coreLabel : coreLabelList) {
            System.out.println(coreLabel.originalText());
        }
        System.out.println();
    }
}
