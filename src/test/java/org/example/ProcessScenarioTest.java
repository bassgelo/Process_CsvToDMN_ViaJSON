package org.example;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.PostConstruct;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessScenarioTest {

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule @ClassRule
  public static ProcessEngineRule rule;

  @PostConstruct
  void initRule() {
    rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
  }

  @Before
  public void setup() {
    init(processEngine);
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private ProcessScenario myProcess;

  @Test
  @Deployment(resources="process.bpmn") // only required for process test coverage
  public void testHappyPath() {
    // Test class empty, only the skeleton is provided as this project was created using a Maven archetype
    // Define scenarios by using camunda-bpm-assert-scenario
  }

}
