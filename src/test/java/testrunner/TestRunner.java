package testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
	    features = "src/test/resources/Feature",  	
	    glue = "stepdefinations",               	
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    tags = "@smoke or @regression",						
	    monochrome = true                         	                           
	)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
