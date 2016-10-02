package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public ShoppingCart(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public Checkout goToCheckout() throws InterruptedException {
        System.out.println(" goToCheckout selecting aggre terms starts ");
//        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("termsofservice")));
        webDriver.findElement(By.id("termsofservice")).click();
        webDriver.findElement(By.id("checkout")).click();
        System.out.println("goToCheckout selecting aggre terms ends ");
        return new Checkout(webDriver);
    }
    public String getProductsInShoppingCart() throws InterruptedException {
        Thread.sleep(2000);// to czekanie ponizej cos nie dziala chyba
        wait.until(ExpectedConditions.elementToBeClickable(By.className("product-name")));
        return webDriver.findElement(By.className("product-name")).getText();
    }
}
