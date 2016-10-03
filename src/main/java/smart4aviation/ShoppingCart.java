package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart {
    private static final String TERMSOFSERVICE = "termsofservice";
    private static final String CHECKOUT = "checkout";
    private static final String PRODUCT_NAME = "product-name";
    private WebDriver webDriver;
    private WebDriverWait wait;

    public ShoppingCart(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public Checkout goToCheckout() throws InterruptedException {
        System.out.println(" goToCheckout selecting aggre terms starts ");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(TERMSOFSERVICE)));
        webDriver.findElement(By.id(TERMSOFSERVICE)).click();
        webDriver.findElement(By.id(CHECKOUT)).click();
        System.out.println("goToCheckout selecting aggre terms ends ");
        return new Checkout(webDriver);
    }

    public String getProductsInShoppingCart() throws InterruptedException {
        Thread.sleep(2000);// to czekanie ponizej cos nie dziala chyba
        wait.until(ExpectedConditions.elementToBeClickable(By.className(PRODUCT_NAME)));
        return webDriver.findElement(By.className(PRODUCT_NAME)).getText();
    }
}
