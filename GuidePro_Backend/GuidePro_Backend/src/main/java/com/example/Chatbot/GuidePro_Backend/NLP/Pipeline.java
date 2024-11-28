package com.example.Chatbot.GuidePro_Backend.NLP;
import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Pipeline {

    private static StanfordCoreNLP stanfordCoreNLP;
    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner, parse, sentiment ";
    private static Properties properties;

    private Pipeline() {
        // private constructor to prevent instantiation
    }

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if (stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
