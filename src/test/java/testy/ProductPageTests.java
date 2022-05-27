package testy;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductPageTests extends BaseTest{
    @Test(groups = {"SmokeTests"})
//    definicja grup w celu możliwości uruchamiania różnych zestawów testów (suite)
    public void checkProductName(){
        System.out.println("Sprawdzam nazwe produktu");
    }

    @Test
    public void checkProductPrice(){
        System.out.println("Sprawdzam cene produktu");
    }

    @Test(priority = 1)
    public void checkButtonColor(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@data-button-action=\"add-to-cart\"]"));
        String buttonColor = addToCartButton.getCssValue("background-color");
//        Pobieranie wartości koloru tła z CSS dla przycisku
        System.out.println(buttonColor);
        String buttonColorHex = Color.fromString(buttonColor).asHex();
//        Ponieważ kolor zwracany przez selenium ma inny zapis niż ten w Developer Tools, musimy go przekonwertować.
//        Biblioteka Selenium zawiera odpowiednią klasę do tego celu
        System.out.println(buttonColorHex);

        Assert.assertEquals(buttonColorHex, "#2fb5d2");
    }

    @Test(priority = 2)
    public void checkAssuranceFont(){
        WebElement assuranceTextElement = driver.findElement(By.cssSelector(".blockreassurance_product .block-title"));
        String fontFamilyValue = assuranceTextElement.getCssValue("font-family");
//        Pobranie definicji czcionek dla danego elementu

        Assert.assertTrue(fontFamilyValue.startsWith("Noto Sans"));
    }

    @Test(priority = 3)
    public void checkAttributeNameForQuantity(){
        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        String quantityName = quantityInput.getAttribute("name");
//        Możliwe jest również pobieranie atrybutów tagów HTML
        System.out.println(quantityName);

        Assert.assertEquals(quantityName, "qty");
    }

    @Test
    public void checkColorSelection(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
        List<WebElement> colorInputElements = driver.findElements(By.cssSelector(".input-color"));

        System.out.println(colorInputElements.get(0).getAttribute("title"));
        Assert.assertTrue(colorInputElements.get(0).isSelected());
        Assert.assertFalse(colorInputElements.get(1).isSelected());
//        Sprawdzamy czy radio buttony są prawidłowo zaznaczone. Ponieważ w danej grupie może być zaznaczony tylko jeden
//        co jest dla nas oczekiwane (chcemy zamówić konkretny kolor)
        takeScreenshot("screenshot1");
//        zapisujemy screenshot przed wyborem koloru (z domyślnym kolorem)

        colorInputElements.get(1).click();
        Assert.assertTrue(colorInputElements.get(1).isSelected());
        Assert.assertFalse(colorInputElements.get(0).isSelected());
//        System.out.println(colorInputElements.get(1).getAttribute("checked"));
//        System.out.println(colorInputElements.get(0).getAttribute("checked"));
//        Assert.assertEquals(colorInputElements.get(1).getAttribute("checked"), "checked");
//        Assert.assertEquals(colorInputElements.get(0).getAttribute("checked"), "checked");
//        W niektórych realizacjach, pola po zaznaczeniu otrzymują atrybut "checked". Więc można też sprawdzać
//        z jego wykorzystaniem. Jednak powyższy zapis będzie działał niepoprawnie. Pomimo, że argument checked
//        otrzymuje wartość tekstową 'checked' czyli 'checked=checked' to po pobraniu atrybutu dostajemy 'true'

        takeScreenshot("screenshot2");
//        zbieramy screenshot z wybranym nowym kolorem
    }

    public void takeScreenshot(String fileName){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./target/screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        zapis screenshota tak na prawdę sprowadza się do dwóch linijek i dodania obsługi wyjątku IOException
//        Docelowo screenshot powinien zbierać się z unikalną nazwą - np. nazwa testu + timestamp
//        I głównie w przypadku testu zakończonego niepowodzeniem
    }
}
