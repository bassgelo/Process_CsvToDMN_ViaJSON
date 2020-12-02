package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

import static org.camunda.spin.Spin.JSON;
import static org.camunda.spin.Spin.S;

@Named("csvPostProcessorDelegate")
public class CsvPostProcessorDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(CsvLoaderDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //Now we take the json we created before -> 'jsonObject'
        JsonValue jsonObject = execution.getVariableTyped("originalJsonObject");
        SpinJsonNode json = jsonObject.getValue();

        //Create a new empty JSON document
        SpinJsonNode jsonRootNode = JSON("[]");
        //Process the original JSON, this is verbose but ony for teaching purposes
        for(SpinJsonNode node : json.elements()){
            SpinJsonNode jsonChildNode = JSON("{}")
                    .prop("item", node.prop("item"))
                    .prop("quantity", node.prop("quantity").numberValue());
            jsonRootNode.append(jsonChildNode);
        }
        execution.setVariable("processedCollection", jsonRootNode);

        SpinJsonNode jsonCombinedResult = JSON("{\"values\":[] }");
        execution.setVariable("combinedResult", jsonCombinedResult);
    }
}
