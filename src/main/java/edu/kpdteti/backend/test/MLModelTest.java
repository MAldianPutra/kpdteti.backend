package edu.kpdteti.backend.test;

import edu.kpdteti.backend.util.MLModelUtil;
import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.*;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MLModelTest {
    public static void main(String[] args) throws JAXBException, IOException, SAXException, URISyntaxException {
        String text1 = "Tourism Demand Time Series Forecasting: A Systematic Literature Review, " +
                "The tourism industry is one of the economic sectors that is overgrowing throughout the world. " +
                "Accurate tourism demand forecasting is needed for proper strategic planning, decision making, and advanced research. " +
                "Although there are several review papers on tourism demand forecasting in the literature, " +
                "they are limited to only a few aspects. The purpose of this paper is to provide a comprehensive, " +
                "structured analysis, not only method but also data source, destination, date range, information type, data frequency, " +
                "and measurement accuracy. This paper also identified some remaining problems to be solved as the future direction " +
                "for tourism demand forecasting research. " +
                "There are 21 recent studies in tourism demand forecasting from 2017 to 2019 in the form of a systematic literature review. " +
                "This will contribute to research in stimulating tourism demand prediction researchers and practitioners to " +
                "undertake more work and developments in the field of time series forecasting.";
        String text2 = "Long latency branch how much do they Matter dynamic branch prediction play a key role in deliver high " +
                "performance in the modern microprocessor the cycle between the prediction of a branch and its execution constitute the " +
                "branch misprediction penalty because a misprediction can be detect only after the branch execute Branch misprediction " +
                "penalty depend not only on the depth of the pipeline but also on the availability of branch operand fetch branch belong " +
                "to the dependence chain of load that miss in the l1 datum cache exhibit very high misprediction penalty due to the delay " +
                "in the execution result from unavailability of operand we call these the long latency branch it have be speculate that " +
                "predict such branch accurately or identify such mispredict branch before they execute would be beneficial in this paper " +
                "we show that in a traditional pipeline the frequency of mispredict long latency branch be extremely small therefore predict " +
                "all these branch correctly do not offer any performance improvement architecture that allow checkpoint assist speculative " +
                "load retirement fetch a large number of branch belong to the dependence chain of the speculatively retire load accurate prediction " +
                "of these branch be extremely important for stay on the correct path we show that even if all the branch belong to the dependence chain " +
                "of the load that miss in the l1 datum cache be predict correctly only four application out of twelve control speculation sensitive " +
                "application select from the specint2000 and BioBench suite exhibit visible performance improvement this be a upper bind on the " +
                "achievable performance improvement in these architecture this article conclude that it may not be worth design specialized hardware " +
                "to improve the prediction accuracy of the long latency branch";
        MLModelUtil mlModelUtil = new MLModelUtil();
        System.out.println("Text 1 prediction: " + mlModelUtil.predictText(text1));
        System.out.println("Text 2 prediction: " + mlModelUtil.predictText(text2));

    }
}
