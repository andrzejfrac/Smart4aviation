package smart4aviation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver webDriver;
    private static final String ADDRESS = "http://demo.nopcommerce.com";
    private WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    //    @Parameters({"URLAddress"})
    public HomePage openAddress() {
        System.out.println("openAddres starts");
        webDriver.get(ADDRESS);
        System.out.println("openAddres ends");
        return this;
    }

    public void login(String email, String password) {
        System.out.println("login starts");
        webDriver.findElement(By.className("ico-login")).click();
        webDriver.findElement(By.className("email")).sendKeys(email);
        webDriver.findElement(By.className("password")).sendKeys("password");
        webDriver.findElement(By.className("login-button")).click();
        System.out.println("login ends");
    }

    public RegistrationPage goToRegistration() {
        webDriver.findElement(By.className("ico-register")).click();
        return new RegistrationPage(webDriver, this);
    }

    public SearchResultPage sendToSearchBox(String keys) throws InterruptedException {
        System.out.println("HomePage sendToSearchBox starts");
//        Thread.sleep(2000);
        wait.until(ExpectedConditions.alertIsPresent()); //elementToBeClickable(By.className("product-box-add-to-cart-button")));
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        webDriver.findElement(By.className("search-box-text")).sendKeys(keys);
        webDriver.findElement(By.className("button-1")).click();
//        Thread.sleep(1000);
//        Thread.sleep(100);
//        webDriver.findElement(By.className("product-box-add-to-cart-button")).click();
        System.out.println("HomePage sendToSearchBox ends");
        return new SearchResultPage(webDriver);
    }

}
