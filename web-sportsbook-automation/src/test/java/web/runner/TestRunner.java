package web.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources"
        , glue = {"web/steps"}
        , monochrome = true
        , plugin = {"pretty", "html:target/cucumber-reports/web"}
        , tags = "@Regression"
        , dryRun = false
)
public class TestRunner {
}
