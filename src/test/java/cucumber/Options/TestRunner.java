package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
//Here it will execute all the feature files in the features path
//On folder level it picks all the feture files
//If you want run only one feature file then directly give full path until featureFileNAme.feature
@CucumberOptions(features="src/test/java/features", 
glue= {"stepDefinitions"},stepNotifications = true, plugin= "json:target/jsonReports/cucumber-report.json")
//tags= "@DeletePlace"
public class TestRunner {
	

}
//compile phase
//test phase
//verify phase - reporting is done in this
//JsonFile is input to the plugin - explicitly not available
//${project.build.directory} - path to target folder irrespective of project
//