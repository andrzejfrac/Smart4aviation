package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private static final String PRODUCT_BOX_ADD_TO_CART_BUTTON = "product-box-add-to-cart-button";
    private static final String CART_LABEL = "cart-label";
    private WebDriver webDriver;
    private WebDriverWait wait;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public SearchResultPage getItem(String item) throws InterruptedException {
        System.out.println("gettingItem starts");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(item)));
        webDriver.findElement(By.linkText(item)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className(PRODUCT_BOX_ADD_TO_CART_BUTTON)));
        webDriver.findElement(By.className(PRODUCT_BOX_ADD_TO_CART_BUTTON)).click();
//        Thread.sleep(1000); potrzebuje do IE
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='bar-notification']/span")));
//        webDriver.findElement(By.xpath(".//*[@id='bar-notification']/span")).click();
        System.out.println("gettingItem ends");
        return this;
    }

    public ShoppingCart navigateToCart() {
        System.out.println("navigating to cart starts ");
        webDriver.findElement(By.className(CART_LABEL)).click();
        System.out.println("navigating to cart ends");
        return new ShoppingCart(webDriver);
    }
}