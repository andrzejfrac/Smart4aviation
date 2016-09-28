package smart4aviation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver webDriver;
    private HomePage homePage;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver webDriver, HomePage homePage) {
        this.webDriver = webDriver;
        this.homePage = homePage;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public HomePage register(String firstName, String lastName, String email, String password) throws InterruptedException {
        System.out.println("poczatek goToRegistration");
        webDriver.findElement(By.id("FirstName")).sendKeys(firstName);
        webDriver.findElement(By.id("LastName")).sendKeys(lastName);
        webDriver.findElement(By.id("Email")).sendKeys(email);
        webDriver.findElement(By.id("Password")).sendKeys(password);
        webDriver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        webDriver.findElement(By.id("register-button")).click();
//        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("button-1")));
        webDriver.findElement(By.className("button-1")).click();
        wait.until(ExpectedConditions.alertIsPresent()); //elementToBeClickable(By.className("product-box-add-to-cart-button")));
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        System.out.println("koniec goToRegistration");
        return homePage;
    }
}
