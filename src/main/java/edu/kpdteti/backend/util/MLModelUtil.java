package edu.kpdteti.backend.util;

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
public class MLModelUtil {

    public Map<String, ?> predictText(String completeText) throws JAXBException, SAXException, IOException, URISyntaxException {

        /* Get the model */
        URL modelURL = MLModelUtil.class.getClassLoader().getResource("model/lr_model_tier_one_with_tfidf.pmml");
        File file = Paths.get(modelURL.toURI()).toFile();
        Path modelPath = Paths.get(file.getAbsolutePath());
        Evaluator evaluator = new LoadingModelEvaluatorBuilder()
                .load(modelPath.toFile()).build();

        /* Get the text input */
        FieldName targetName = evaluator.getTargetFields().get(0).getName();
        List<InputField> inputFields = evaluator.getInputFields();
        Map<String, String> features = new HashMap<>();
        features.put("Text", completeText);

        Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
        for (InputField inputField : inputFields) {
            FieldName inputName = inputField.getName();
            String text = features.get(inputName.toString());
            FieldValue inputValue = inputField.prepare(text);
            arguments.put(inputName, inputValue);
        }

        /* Predict a text and return the list */
        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        return EvaluatorUtil.decodeAll(results);
    }

}
