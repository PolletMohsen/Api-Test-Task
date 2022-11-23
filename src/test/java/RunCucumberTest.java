import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"})
public class RunCucumberTest {
}