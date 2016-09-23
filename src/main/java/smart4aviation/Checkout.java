package smart4aviation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Andy on 9/21/2016.
 */
public class Checkout {
    WebDriver webDriver;

    public Checkout(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void shippingInformations() throws InterruptedException {
        new Select(webDriver.findElement(By.id("BillingNewAddress_CountryId")))
                .selectByVisibleText("United States");
        new Select(webDriver.findElement(By.id("BillingNewAddress_StateProvinceId")))
                .selectByVisibleText("New York");
        webDriver.findElement(By.id("BillingNewAddress_City")).sendKeys("BillingNewAddress_City");
        webDriver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("BillingNewAddress_Address1");
        webDriver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("BillingNewAddress_ZipPostalCode");
        webDriver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("BillingNewAddress_PhoneNumber");
        webDriver.findElement(By.className("new-address-next-step-button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.className("shipping-method-next-step-button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.className("payment-method-next-step-button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.className("payment-info-next-step-button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.className("confirm-order-next-step-button")).click();

        Thread.sleep(20000);
    }
}
