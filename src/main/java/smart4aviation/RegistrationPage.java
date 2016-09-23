package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andy on 9/18/2016.
 */
public class RegistrationPage {
    private WebDriver webDriver;
    private HomePage homePage;

    public RegistrationPage(WebDriver webDriver,HomePage homePage) {
        this.webDriver = webDriver;
        this.homePage=homePage;
    }
    public HomePage register(String firstName, String lastName,String email, String password) throws InterruptedException {
        System.out.println("poczatek goToRegistration");
        webDriver.findElement(By.id("FirstName")).sendKeys(firstName);
        webDriver.findElement(By.id("LastName")).sendKeys(lastName);
        webDriver.findElement(By.id("Email")).sendKeys(email);
        webDriver.findElement(By.id("Password")).sendKeys(password);
        webDriver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        webDriver.findElement(By.id("register-button")).click();
        Thread.sleep(500);
        webDriver.findElement(By.className("button-1")).click();
        System.out.println("koniec goToRegistration");
        return homePage;
    }
}
