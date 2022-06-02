package testy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    final static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        LOGGER.info("Uruchamiam metodę Setup dla klasy");
        LOGGER.info("Wybrany driver to " + System.getProperty("TestDriver"));
//        Logowanie informacji na temat wybranej do testów (przekazanej przez Maven) wersji drivera
        WebDriverManager.firefoxdriver().setup();
//        Konfiguracja drivera z wykorzystaniem WebDriverManagera

//        FirefoxOptions options = setFirefoxOptions();
//        driver = new FirefoxDriver(options);
//        Powyższe linijki realizują dokładnie to samo zadanie co poniższa
        driver = new FirefoxDriver(setFirefoxOptions());

//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        W równie prosty sposób możemy skorzystać z drivera dla innej przeglądarki

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static FirefoxOptions setFirefoxOptions(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        return options;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        LOGGER.info("Uruchamiam metodę TearDown dla klasy");
        driver.quit();
    }

    @BeforeMethod
    public void methodSetUp(ITestResult result){
        LOGGER.info("Uruchamiam metodę testową " + result.getMethod().getMethodName());
//        Wyświetlam informację o uruchomieniu metody testowej wraz z jej nazwą pobraną z ITestResult dostarczanych
//        przez TestNG
    }

    @AfterMethod
    public void methodTearDown(ITestResult result){
        String methodName = result.getMethod().getMethodName();
//        Zapisuję nazwę metody na potrzeby logowania i zapisu screenshota

        if(result.getStatus() != ITestResult.SUCCESS){
//            Uruchamiam odpowiedni kod na podstawie wyniku testu pobranego z ITestResults
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            long timestampMillis = result.getEndMillis();
            String timestamp = sdf.format(timestampMillis);
            String fileName = methodName + "_" + timestamp;
//            Dorzuciłem dwie linijki związane z formatowaniem czasu z milliseconds na bardziej czytelny format
            ScreenshotUtil.takeScreenshot(driver, fileName);
        }
        LOGGER.info("Kończę metodę testową " + methodName);
    }
}
