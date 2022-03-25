package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.json.SpinJsonNode;
import org.camunda.spin.plugin.variable.value.JsonValue;
import javax.inject.Named;
import static org.camunda.spin.Spin.JSON;

@Named("csvPostProcessorDelegate")
public class CsvPostProcessorDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //Now we take the json we created in the previous activity
        JsonValue jsonObject = execution.getVariableTyped("originalJsonObject");
        SpinJsonNode json = jsonObject.getValue();

        //Create a new empty JSON
        SpinJsonNode jsonRootNode = JSON("[]");
        //Process the original JSON, this is verbose but ony for teaching purposes, i.e. here we create a copy of the json, but we show how to iterate over the json elements
        for(SpinJsonNode node : json.elements()){
            SpinJsonNode jsonChildNode = JSON("{}")
                    .prop("item", node.prop("item"))
                    .prop("quantity", node.prop("quantity").numberValue());
            jsonRootNode.append(jsonChildNode);
        }
        //we save in the process the copy of the json object we created with a different name
        execution.setVariable("processedCollection", jsonRootNode);

        //We create a new empty JSON, this will help us collect the individual results of the DMN table evaluation
        //the collection of the individual results happens inside a task listener of type end using javascript, it is documented in the BPMN file itself
        SpinJsonNode jsonCombinedResult = JSON("{\"values\":[] }");
        execution.setVariable("combinedResult", jsonCombinedResult);
    }
}
