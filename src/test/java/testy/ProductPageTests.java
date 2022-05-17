package testy;

import org.testng.annotations.Test;

public class ProductPageTests {
    @Test(groups = {"SmokeTests"})
//    definicja grup w celu możliwości uruchamiania różnych zestawów testów (suite)
    public void checkProductName(){
        System.out.println("Sprawdzam nazwe produktu");
    }

    @Test
    public void checkProductPrice(){
        System.out.println("Sprawdzam cene produktu");
    }
}
