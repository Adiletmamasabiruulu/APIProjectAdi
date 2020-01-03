package Runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberHTML-report","pretty","json:target/cucumber/json"},
        features = {"src/test/resources/UIFeatures"},
        glue= "stepDefinitions",
        dryRun =false,
        tags = "@API"


)
public class UiTestRunner {








}
