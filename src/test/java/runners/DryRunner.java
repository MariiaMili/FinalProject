package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="./src/test/resources/features", 
		glue="tests",
		dryRun=true, //when its true, it scans 
		            //the whole feature file 
		            //and finds unimplemented files
		            //if false- turns off
		tags="@invalidCredentialsLogin"
		)
public class DryRunner {

}


