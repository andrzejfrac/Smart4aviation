package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public SearchResultPage getItem(String item) throws InterruptedException {
        System.out.println("gettingItem starts");
//        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(item)));
        webDriver.findElement(By.linkText(item)).click();
//        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("product-box-add-to-cart-button")));
        webDriver.findElement(By.className("product-box-add-to-cart-button")).click();
        System.out.println("gettingItem ends");
        return this;
    }

    public ShoppingCart navigateToCart() {
        System.out.println("navigating to cart starts ");
        webDriver.findElement(By.className("cart-label")).click();
        System.out.println("navigating to cart ends");
        return new ShoppingCart(webDriver);
    }
}
