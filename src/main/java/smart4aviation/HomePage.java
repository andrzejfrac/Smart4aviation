package smart4aviation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Andy on 9/17/2016.
 */
public class HomePage {
    private WebDriver webDriver;
    private static final String ADDRESS = "http://demo.nopcommerce.com";

    //    private NavigationMenu navigationMenu;
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
//        this.navigationMenu = new NavigationMenu(webDriver);

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
        return new RegistrationPage(webDriver,this);
    }

    public SearchResultPage sendToSearchBox(String keys) throws InterruptedException {
        System.out.println("HomePage sendToSearchBox starts");
        Thread.sleep(2000);
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        webDriver.findElement(By.className("search-box-text")).sendKeys(keys);
        webDriver.findElement(By.className("button-1")).click();
        Thread.sleep(1000);
//        Thread.sleep(100);
//        webDriver.findElement(By.className("product-box-add-to-cart-button")).click();
        System.out.println("HomePage sendToSearchBox ends");
        return new SearchResultPage(webDriver);
    }
//    public NavigationMenu getNavigationMenu(){
//        return navigationMenu;

}
