package edu.kpdteti.backend.test;

import edu.kpdteti.backend.util.TextPreprocessingUtil;
import org.springframework.stereotype.Service;

@Service
public class TextPreprocessingTest {
    public static void main(String[] args) {
        String title = "Long-Latency Branches: How Much Do They Matter?";
        String abstractText = "Dynamic branch prediction plays a key role in delivering high performance in the modern\n" +
                "microprocessors. The cycles between the prediction of a branch and its execution constitute\n" +
                "the branch misprediction penalty because a misprediction can be detected only after\n" +
                "the branch executes. Branch misprediction penalty depends not only on the depth of\n" +
                "the pipeline, but also on the availability of branch operands. Fetched branches belonging\n" +
                "to the dependence chains of loads that miss in the L1 data cache exhibit very high\n" +
                "misprediction penalty due to the delay in the execution resulting from unavailability\n" +
                "of operands. We call these the long-latency branches. It has been speculated that\n" +
                "predicting such branches accurately or identifying such mispredicted branches before\n" +
                "they execute would be beneficial. In this paper, we show that in a traditional pipeline\n" +
                "the frequency of mispredicted long-latency branches is extremely small. Therefore,\n" +
                "predicting all these branches correctly does not offer any performance improvement.\n" +
                "Architectures that allow checkpoint-assisted speculative load retirement fetch a large\n" +
                "number of branches belonging to the dependence chains of the speculatively retired\n" +
                "loads. Accurate prediction of these branches is extremely important for staying on\n" +
                "the correct path. We show that even if all the branches belonging to the dependence\n" +
                "chains of the loads that miss in the L1 data cache are predicted correctly, only four\n" +
                "applications out of twelve control speculation-sensitive applications selected from\n" +
                "the SPECInt2000 and BioBench suites exhibit visible performance improvement. This\n" +
                "is an upper bound on the achievable performance improvement in these architectures.\n" +
                "This article concludes that it may not be worth designing specialized hardware to\n";
        String keywords = "Improve the prediction accuracy of the long-latency branches";
        TextPreprocessingUtil textPreprocessingUtil = new TextPreprocessingUtil();

        String text_concat = textPreprocessingUtil.concatAndLowercaseText(title, abstractText, keywords);
        System.out.println(text_concat);
        String text_symbol_removed = textPreprocessingUtil.symbolRemover(text_concat);
        System.out.println("\n" + text_symbol_removed);
        String text_lemmatized = textPreprocessingUtil.lemmatizer(text_symbol_removed);
        System.out.println("\n" + text_lemmatized);
    }
}
