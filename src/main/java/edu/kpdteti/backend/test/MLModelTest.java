package edu.kpdteti.backend.test;

import edu.kpdteti.backend.util.MLModelUtil;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class MLModelTest {
    public static void main(String[] args) throws JAXBException, IOException, SAXException, URISyntaxException {
        String test = "E-commerce can be used to increase companies or sellers’ profits. For consumers, e-commerce can help them shop faster. " +
                "The weakness of e-commerce is that there is too much product information presented in the catalog which in turn makes consumers confused. " +
                "The solution is by providing product recommendations.";
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
        String text3 = "The Effect of the COVID-19 Pandemic and Google Trends on the Forecasting of International Tourist Arrivals in Indonesia " +
                "The tourism sector is a strategic industrial pillar that contributes to a country's economy. In future tourism development efforts, " +
                "accurate tourism forecasting is needed. Despite its importance, tourism is also one of the most vulnerable industries. " +
                "Since COVID-19 was declared a pandemic by WHO, social distancing has significantly impacted tourism development. " +
                "It can be explored more deeply by including the COVID-19 pandemic in the forecast. In addition, it is necessary to include Google Trends, " +
                "which is a product of the largest search engine in the world and is proven to improve forecasting accuracy. This study aimed to analyze the " +
                "effect of the COVID-19 pandemic and search query data on the forecasting of foreign tourists to Indonesia. " +
                "The methods used are ARIMAX and SARIMAX with the endogenous variables of foreign tourist visits to Indonesia. " +
                "Meanwhile, the exogenous variables are Google Trends search query data and the COVID-19 pandemic. " +
                "The performance of the two methods is then compared with the ARIMA and SARIMA methods, which do not use exogenous variables in forecasting. " +
                "This study indicates that the exogenous variables increase the forecasting accuracy. " +
                "Forecasting with the best accuracy is obtained by the SARIMAX method with the exogenous variable Google Trends. " +
                "This method outperformed the other methods with MAPE = 5.4556, RMSE = 11041.0510 and MAE = 8479.6116. " +
                "In addition, in this study, a framework was created to build a composite search index for Google Trends to improve forecasting accuracy. " +
                "COVID-19; forecasting; Google Trends; pandemic; SARIMAX; time series; tourism Search engines; Tourism; ARIMAX; COVID-19; " +
                "Exogenous variables; Forecasting accuracy; Google trends; Indonesia; Pandemic; SARIMAX; Times series; Tourism development; Forecasting";
        String text4 = "Improving the accuracy of text classification using stemming method, a case of non-formal Indonesian conversation " +
                "Background: Stemming has long been used in data pre-processing to retrieve information by tracking affixed words back into their root. " +
                "In an Indonesian setting, existing stemming methods have been observed, and the existing stemming methods are proven to result in high accuracy level. " +
                "However, there are not many stemming methods for non-formal Indonesian text processing. This study introduces a new stemming method to solve problems in the non-formal Indonesian text data pre-processing. " +
                "Furthermore, this study aims to improve the accuracy of text classifier models by strengthening stemming method. Using the Support Vector Machine algorithm, a text classifier model is developed, and its accuracy is checked. " +
                "The experimental evaluation was done by testing 550 datasets in Indonesian using two different stemming methods. Findings: The results show that using the proposed stemming method, " +
                "the text classifier model has higher accuracy than the existing methods with a score of 0.85 and 0.73, respectively. These results indicate that the proposed stemming methods " +
                "produces a classifier model with a small error rate, so it will be more accurate to predict a class of objects. Conclusion: The existing Indonesian stemming methods are still oriented towards Indonesian formal sentences, " +
                "therefore the method has limitations to be used in Indonesian non-formal sentences. This phenomenon underlies the suggestion of developing a corpus by normalizing Indonesian non-formal into formal to be used as a better " +
                "stemming method. The impact of using the corpus as a stemming method is that it can improve the accuracy of the classifier model. In the future, the proposed corpus and stemming methods can be used for various purposes including text clustering, " +
                "summarizing, detecting hate speech, and other text processing applications in Indonesian. © 2021, The Author(s). " +
                "Accuracy; Classification; Indonesian; Stemming; Text processing";
        String text5 = "Risk model development for information security in organization environment based on business perspectives Digital information plays an essential role in supporting organizational business. " +
                "However, incidents of sensitive information leakage often happen in organization environment. Therefore, risk analysis needs to be performed to recognize the impact of information security threat in organization. " +
                "In order to carry out those risk analyses, risk model is needed to map risk of information security threat. The selection of proper risk model provides proper result related to risk analysis. The proper risk model must have objectivity and appropriate context. " +
                "However, most of the existing risk models focus on the technical approach and use expert judgment as a weighting method. Meanwhile, organizations use business perspectives to determine decisions. " +
                "Therefore, this study has the objective to fill the needs of organizations by developing a new risk model. The proposed risk model focuses on business aspects involvement and reducing subjective methods. " +
                "The proposed risk model also uses three processes to result output, i.e., adaptable classification data, data measurement and cross-label analysis. Test mining and categorical clustering are involved to handle those three processes. " +
                "Testing of the proposed model is carried out to define ability and limitation of model by involving 30 targets. The result states that the proposed model has advantages in objectivity, context approach and detailed output, " +
                "while the limited scope of work becomes weakness of these models. © 2020, Springer-Verlag GmbH Germany, part of Springer Nature. Categorical clustering; Information security; Risk model; " +
                "Text mining Ability testing; Risk analysis; Security of data; Security systems; Business aspects; Business perspective; Categorical clustering; Data measurements; Digital information; Information security threats; Sensitive informations; Subjective methods; Risk assessment";
        MLModelUtil mlModelUtil = new MLModelUtil();
        Map<String, ?> testResult = mlModelUtil.predictText(test);
        long start1 = System.currentTimeMillis();
        Map<String, ?> text1PredictResult = mlModelUtil.predictText(text1);
        System.out.println("Text 1 prediction: " + text1PredictResult);
        System.out.println("Text 1 label: " + text1PredictResult.get("Label"));
        long end1 = System.currentTimeMillis();
        Map<String, ?> text2PredictResult = mlModelUtil.predictText(text2);
        System.out.println("Text 2 prediction: " + text2PredictResult);
        System.out.println("Text 2 label: " + text2PredictResult.get("Label"));
        long end2 = System.currentTimeMillis();
        Map<String, ?> text3PredictResult = mlModelUtil.predictText(text3);
        System.out.println("Text 3 prediction: " + text3PredictResult);
        System.out.println("Text 3 label: " + text3PredictResult.get("Label"));
        long end3 = System.currentTimeMillis();
        Map<String, ?> text4PredictResult = mlModelUtil.predictText(text4);
        System.out.println("Text 4 prediction: " + text4PredictResult);
        System.out.println("Text 4 label: " + text4PredictResult.get("Label"));
        long end4 = System.currentTimeMillis();
        Map<String, ?> text5PredictResult = mlModelUtil.predictText(text5);
        System.out.println("Text 3 prediction: " + text5PredictResult);
        System.out.println("Text 3 label: " + text5PredictResult.get("Label"));
        long end5 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds for first text: " + (end1 - start1));
        System.out.println("Elapsed Time in milli seconds for second text: " + (end2 - end1));
        System.out.println("Elapsed Time in milli seconds for third text: " + (end3 - end2));
        System.out.println("Elapsed Time in milli seconds for fourth text: " + (end4 - end3));
        System.out.println("Elapsed Time in milli seconds for fifth text: " + (end5 - end4));
    }
}
