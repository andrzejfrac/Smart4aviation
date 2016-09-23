package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCart {
    private WebDriver webDriver;

    public ShoppingCart(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Checkout goToCheckout() throws InterruptedException {
        System.out.println(" goToCheckout selecting aggre terms starts ");
        Thread.sleep(2000);
        webDriver.findElement(By.id("termsofservice")).click();
        webDriver.findElement(By.id("checkout")).click();
        System.out.println("goToCheckout selecting aggre terms ends ");
        return new Checkout(webDriver);
    }
}
