package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Checkout {
    private WebDriver webDriver;

    public Checkout(WebDriver webDriver) {
        this.webDriver = webDriver;
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
        Thread.sleep(2000);
        webDriver.findElement(By.className("shipping-method-next-step-button")).click();
        System.out.println("shippingMethod ends");
        return this;
    }

    public Checkout paymentMethodAndInformation() throws InterruptedException {
        System.out.println("paymentMethodAndInformation starts");
        Thread.sleep(2000);
        webDriver.findElement(By.className("payment-method-next-step-button")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.className("payment-info-next-step-button")).click();
        System.out.println("paymentMethodAndInformation ends");
        return this;
    }

    public Checkout paymentConfirmation() throws InterruptedException {
        System.out.println("paymentConfirmation starts");
        Thread.sleep(2000);
        webDriver.findElement(By.className("confirm-order-next-step-button")).click();
        Thread.sleep(2000);
        System.out.println("paymentConfirmation ends");
        return this;
    }
}
