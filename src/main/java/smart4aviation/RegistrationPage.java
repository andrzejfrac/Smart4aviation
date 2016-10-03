package smart4aviation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String CONFIRM_PASSWORD = "ConfirmPassword";
    public static final String REGISTER_BUTTON = "register-button";
    public static final String rEGISTER_NEXT_STEP_BUTTON = "button-1";
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
        webDriver.findElement(By.id(FIRST_NAME)).sendKeys(firstName);
        webDriver.findElement(By.id(LAST_NAME)).sendKeys(lastName);
        webDriver.findElement(By.id(EMAIL)).sendKeys(email);
        webDriver.findElement(By.id(PASSWORD)).sendKeys(password);
        webDriver.findElement(By.id(CONFIRM_PASSWORD)).sendKeys(password);
        webDriver.findElement(By.id(REGISTER_BUTTON)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className(rEGISTER_NEXT_STEP_BUTTON)));
        webDriver.findElement(By.className(rEGISTER_NEXT_STEP_BUTTON)).click();
        wait.until(ExpectedConditions.alertIsPresent()); //button-1 register-next-step-buttonelementToBeClickable(By.className("product-box-add-to-cart-button")));
        webDriver.switchTo().alert().accept();
        System.out.println("koniec goToRegistration");
        return homePage;
    }
}
