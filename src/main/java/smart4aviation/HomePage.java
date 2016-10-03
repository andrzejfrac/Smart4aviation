package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final String ICO_LOGIN = "ico-login";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LOGIN_BUTTON = "login-button";
    private static final String ICO_REGISTER = "ico-register";
    private static final String SEARCH_BOX_TEXT = "search-box-text";
    private static final String SEARCH_BUTTON = "button-1";
    private static final String ICO_ACCOUNT = "ico-account";
    private WebDriver webDriver;
    private static final String ADDRESS = "http://demo.nopcommerce.com";
    private WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public HomePage openAddress(String addresUrl) {
        System.out.println("openAddres starts");
        webDriver.get(ADDRESS);
        System.out.println("openAddres ends");
        return this;
    }

    public void login(String email, String password) {
        System.out.println("login starts");
        webDriver.findElement(By.className(ICO_LOGIN)).click();
        webDriver.findElement(By.className(EMAIL)).sendKeys(email);
        webDriver.findElement(By.className(PASSWORD)).sendKeys(PASSWORD);
        webDriver.findElement(By.className(LOGIN_BUTTON)).click();
        System.out.println("login ends");
    }

    public RegistrationPage goToRegistration() {
        webDriver.findElement(By.className(ICO_REGISTER)).click();
        return new RegistrationPage(webDriver, this);
    }

    public SearchResultPage sendToSearchBox(String keys) throws InterruptedException {
        System.out.println("HomePage sendToSearchBox starts");
        webDriver.findElement(By.className(SEARCH_BOX_TEXT)).sendKeys(keys);
        webDriver.findElement(By.className(SEARCH_BUTTON)).click();
//        Thread.sleep(100);
//        webDriver.findElement(By.className("product-box-add-to-cart-button")).click();
        System.out.println("HomePage sendToSearchBox ends");
        return new SearchResultPage(webDriver);
    }

    public String getUserEmail() throws InterruptedException {
        return webDriver.findElement(By.className(ICO_ACCOUNT)).getText();
    }
}
