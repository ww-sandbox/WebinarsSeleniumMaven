package testy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    @Test(groups = {"SmokeTests"})
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, "Automation Sample Shop");
        driver.close();
    }
}
