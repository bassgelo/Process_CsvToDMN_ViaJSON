package org.example;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.plugin.variable.SpinValues;
import org.camunda.spin.plugin.variable.value.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Named;
import java.io.File;

/**
 * This class reads data from a csv files and saves it as process variables
 */

@Named("csvLoaderDelegate")
public class CsvLoaderDelegate implements JavaDelegate{

    Logger logger = LoggerFactory.getLogger(CsvLoaderDelegate.class);

    public void execute(DelegateExecution execution) throws Exception {
        MappingIterator<CsvDTO> csvFileLines;

        //complete file as JSON
        csvFileLines = readCsvFile("src/main/resources/file.csv");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(csvFileLines.readAll());
        logger.info("JSON as String: "+ jsonString); //Watch out, here we print the String not the json
        JsonValue jsonObject = SpinValues.jsonValue(jsonString).create(); //Now lets convert the String to an actual json object
        execution.setVariable("originalJsonObject", jsonObject);
    }
    
    private MappingIterator readCsvFile(String path) throws Exception{
        //read the csv file and map it to DTO
        CsvSchema csvFileSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        return csvMapper.readerFor(CsvDTO.class)
                .with(csvFileSchema)
                .readValues(new File(path));
    }
}
