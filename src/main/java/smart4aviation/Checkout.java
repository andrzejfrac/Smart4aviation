package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    private static final By CONFIRM_ORDER_NEXT_STEP_BUTTON = By.className("confirm-order-next-step-button");
    private WebDriver webDriver;
    private WebDriverWait wait;

    public Checkout(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 4000);
    }

    public Checkout billingAddress() throws InterruptedException {
        System.out.println("billingAddress starts");
        new Select(webDriver.findElement(By.id("BillingNewAddress_CountryId")))
                .selectByVisibleText("United States");
        new Select(webDriver.findElement(By.id("BillingNewAddress_StateProvinceId")))
                .selectByVisibleText("New York");
        webDriver.findElement(By.id("BillingNewAddress_City")).sendKeys("BillingNewAddress_City");
        webDriver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("BillingNewAddress_Address1");
        webDriver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("BillingNewAddress_ZipPostalCode");
        webDriver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("BillingNewAddress_PhoneNumber");
        webDriver.findElement(By.className("new-address-next-step-button")).click();
        System.out.println("billingAddress ends");
        return this;
    }

    public Checkout shippingMethod() throws InterruptedException {
        System.out.println("shippingMethod starts");
//        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shipping-method-next-step-button")));
        webDriver.findElement(By.className("shipping-method-next-step-button")).click();
        System.out.println("shippingMethod ends");
        return this;
    }

    public Checkout paymentMethodAndInformation() throws InterruptedException {
        System.out.println("paymentMethodAndInformation starts");
//        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("payment-method-next-step-button")));
        webDriver.findElement(By.className("payment-method-next-step-button")).click();
//        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("payment-info-next-step-button")));
        webDriver.findElement(By.className("payment-info-next-step-button")).click();
        System.out.println("paymentMethodAndInformation ends");
        return this;
    }

    public Checkout paymentConfirmation() throws InterruptedException {
        System.out.println("paymentConfirmation starts");
//        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_ORDER_NEXT_STEP_BUTTON));
        webDriver.findElement(CONFIRM_ORDER_NEXT_STEP_BUTTON).click();
//        Thread.sleep(4000);
        System.out.println("paymentConfirmation ends");
        return this;
    }
    public String getFinalConfimationMessage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[5]/div[3]/div/div/div/div[2]/div/div[1]/strong")));
        return webDriver.findElement(By.xpath("html/body/div[5]/div[3]/div/div/div/div[2]/div/div[1]/strong")).getText();

    }
}
