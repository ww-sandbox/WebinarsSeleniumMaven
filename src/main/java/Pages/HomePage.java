package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){super(driver);}
//    Instancja WebDrivera jest przekazywana z testu, w tym miejscu przekazujemy ją dalej do konstruktora klasy bazowej

    By firstProductBy = By.cssSelector("article");
    By firstProductPriceBy = By.cssSelector("article .price");
//    Zebranie lokalizatorów w jednym miejscu

    public void openHomePage(){
        driver.get("http://sampleshop.inqa.pl/");
    }
//    Przejście do odpowiedniej strony. Docelowo nie będziemy wykorzystywać sztywno zapisanych (zahardokodowanych)
//    adresów URL, a raczej pobierali je z jakiegoś pliku konfiguracyjnego

    public void openFirstProduct(){
        driver.findElement(firstProductBy);
    }

    public void checkFirstProductIsDisplayed(){
        WebElement firstProduct = driver.findElement(firstProductBy);
    }
    public String getPriceOfFirstElement(){
        String actualPrice = driver.findElement(firstProductPriceBy).getText();

        return actualPrice;
    }
}
