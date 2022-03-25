# Camunda Spring Boot Application
Spring Boot Application using [Camunda](http://docs.camunda.org).

## Show me the important parts!
[BPMN Process](src/main/resources/process.bpmn)

[DMN](src/main/resources/decision_table.dmn)

[CSV file](src/main/resources/file.csv)

## How does it work?
This project takes a CSV file and converts it to JSON. 
It uses a simple inventory replenishment example.
A business user can deliver a CSV file that contains the rules for checking when to replenish the inventory.
An IT team can take the file and create a DMN table. The CSV file serves as well as the input for the table.

With that in mind, the project shows some capabilities of not only DMN but of the whole Camunda Platform.
More details are to be found as comments in the code and as annotations in the BPMN file.

### Running the application
You can build and run the process application with Spring Boot.

#### Manually
1. Build the application using:

```bash
mvn clean package
```
2. Run the *.jar file from the `target` directory using:

```bash
java -jar target/${project-artifactId}.jar
```

#### Maven Spring Boot Plugin
1. Build and deploy the process application using:

```bash
mvn clean package spring-boot:run
```

#### Your Java IDE
1. Run the project as a Java application in your IDE using CamundaApplication as the main class.

### Run and Inspect with Tasklist and Cockpit
Once you deployed the application you can run it using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)

There, start a new process instance of the process **'Process_csv'**

## Environment Restrictions
Built and tested against Camunda BPM version 7.13.0.

As of March 2022, the project runs without issues, but the following warning is shown:

Warning: Nashorn engine is planned to be removed from a future JDK release

This can be resolved with little research online, by adding a new library.

## Known Limitations

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
