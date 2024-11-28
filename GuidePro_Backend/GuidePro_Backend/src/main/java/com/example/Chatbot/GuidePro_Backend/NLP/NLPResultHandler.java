package com.example.Chatbot.GuidePro_Backend.NLP;

import java.util.List;

public class NLPResultHandler {
    private NLPResult nlpResult;

    public NLPResultHandler() {
        this.nlpResult = new NLPResult(null, null);
    }

    public void setNLPResult(List<String> locations, Intent.IntentType intent) {
        this.nlpResult = new NLPResult(locations, intent);
    }

    public NLPResult getNLPResult() {
        return this.nlpResult;
    }
}
