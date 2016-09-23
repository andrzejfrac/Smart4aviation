package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    private WebDriver webDriver;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SearchResultPage getItem(String item) throws InterruptedException {
        System.out.println("gettingItem starts");
        Thread.sleep(1000);
        webDriver.findElement(By.linkText(item)).click();
        Thread.sleep(1000);
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
