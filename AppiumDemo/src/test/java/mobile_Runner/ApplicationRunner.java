package mobile_Runner;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features    = ".//featureFile_Folder/Login.feature", 	
		glue        ={"mobile_StepDefinitions"},	
		dryRun      = false,
		monochrome  = true,
		plugin      = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		tags        = {"@sanity"} )

public class ApplicationRunner extends AbstractTestNGCucumberTests {
	
	@AfterSuite
	public void tearDown() throws Exception {

		CucumberDetailedResults result = new CucumberDetailedResults();
		result.setOutputDirectory("target/");
		result.setOutputName("cucumber");
		result.setSourceFile("./target/cucumber.json");
		result.execute(true);
	}

}
