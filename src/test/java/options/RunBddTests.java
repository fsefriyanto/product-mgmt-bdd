package options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/destination","json:target/cucumber.json"},
		glue = {"stepdefs"},
		features = {"src/test/features"})
public class RunBddTests {}
