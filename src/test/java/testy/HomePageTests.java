package testy;

import Pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    @Test(groups = {"SmokeTests"})
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, "Automation Sample Shop");
    }

    @Test
    public void checkProductPrice(){
        HomePage homePage = new HomePage(driver);
//        tworzymy instancję obiektu strony

        homePage.openHomePage();
//        otwieramy stronę (przechodzimy do odpowiedniego URL)
        String actualPrice = homePage.getPriceOfFirstElement();
//        Odwołując się do metody tej strony pobieramy cenę
        Assert.assertEquals("10", actualPrice);
    }

//    @Test
//    public void checkProductsList(){
//        HomePage.getListOfProducts();
//        promoPage.getListOfProducts();
//        categoryPage.getListOfProducts();
//
//        Assert.compareListOfProducts();
//    }
//    Przykład kodu do pytania, które padło w trakcie zajęć. Metody nie są w praktyce zaimplementowane
}
