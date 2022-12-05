package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
//        Wykorzystujemy PageFactory, dlatego musimy zainicjalizować WebElementy zadeklarowane z wykorzystaniem
//        @FindBy, @FindBys oraz @FindAll
    }

    By colorWhiteBy = By.xpath("//input[@title=\"Biały\"]");
    By colorBlackBy = By.xpath("//input[@title=\"czarny\"]");
    By selectSizeBy = By.id("group_1");
    By numberOfPiecesBy = By.xpath("//input[@name=\"qty\"]");
    By addToBasketBy = By.cssSelector(".add button");
//      Deklaracja lokalizatorów WebElementów - stworzone przed wykorzytaniem PageFactory

    @FindBy(css=".add button")
    WebElement addToBasketButtonElement;
//    WebElement deklarowany w oparciu o PageFactory


    public void openProductPage(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
//        Otwieramy oczekwianą stronę. W docelowym środowisku ten zapis nie będzie oczywiście poprawny ponieważ
//        otwieramy za każdym razem tę samą stronę. Tu należałoby stworzyć mechanizm, który otwiera stronę produktu
//        przekazanego jako parametr, lub pozostawić otwieranie strony produktu logice testowej.
//        Ta forma została stworzona jedynie dla zobrazowania metody otwierania strony.
    }

    public void selectColor(String color){
        if(color.equals("white")){
            driver.findElement(colorWhiteBy).click();
        } else if (color.equals("black")) {
            driver.findElement(colorBlackBy).click();
        }
    }

    public void selectSize(String size){
        WebElement selectElement = driver.findElement(selectSizeBy);
        Select selectSize = new Select(selectElement);
        selectSize.selectByVisibleText("M");
    }

    public void setNumberOfPieces(String number){
        WebElement numberOfPiecesElement = driver.findElement(numberOfPiecesBy);
        numberOfPiecesElement.sendKeys(number);
    }

    public void clickAddToBasketButton(){
////        WebElement addToBasketButton = driver.findElement(addToBasketBy);
////        addToBasketButton.click();
//        driver.findElement(addToBasketBy).click();
//        Pierwsze dwie linijki odpowiadają trzeciej, jednak są zapisane w bardziej czytelny sposób.

        addToBasketButtonElement.click();
//        Ten zapis, bazujący na elemencie stworzonym w oparciu o PageFactory, odpowiada powyższemu. Mamy tu jednak
//        bardziej czytelny zapis i nie musimy za każdym razem odwoływać się do drivera oraz findElement. Dodatkowo
//        możemy skorzystać z @CacheLookUp i cache'ować wynik wyszukiwania co wpłynie pozytywnie na wydajność testów.
//        Należy jednak pamiętać, że ten zabieg zadziała dobrze jedynie w przypadku elementów w pełni statycznych.
    }

    public void checkAddToBasketSummary(){
//        waitForElementVisibility(.....);
//        Przykład wykorzystania metody służącej do oczekiwania na określony element, zadeklarowanej w klasie bazowej.
//        Jest to jedynie rozpoczęty przykład, który wymaga dalszej implementacji.
    }
}
