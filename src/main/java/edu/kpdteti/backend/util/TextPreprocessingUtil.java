package edu.kpdteti.backend.util;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class TextPreprocessingUtil {

    public String concatAndLowercaseText(String title, String abstractText, String keywords) {
        return (title + " " + abstractText + " " + keywords).toLowerCase();
    }

    public String symbolRemover(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", " ");
    }

    public String lemmatizer(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = pipeline.processToCoreDocument(text);
        StringBuilder sb = new StringBuilder();
        for (CoreLabel tok : document.tokens()) {
            sb.append(tok.lemma());
            sb.append(" ");
        }
        return sb.toString();
    }

}
