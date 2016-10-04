package smart4aviation.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import smart4aviation.Checkout;
import smart4aviation.HomePage;
import smart4aviation.SearchResultPage;
import smart4aviation.ShoppingCart;
import smart4aviation.utilities.BrowserFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by Andy on 9/17/2016.
 */
public class TestingMainClass {
    private static final String PRODUCT_TO_LOOK_FOR = "HTC One Mini Blue";
    private WebDriver webDriver;
    private HomePage home;
    private SearchResultPage searchResultPage;
    private Checkout checkout;
    private ShoppingCart shoppingCart;
    private static String userId = UUID.randomUUID().toString();
    private static String ADDRESS_URL;

    @BeforeSuite(groups = {"important"})
    @Parameters({"URLAddress"})
    public void setUp(String addressUrl) throws InterruptedException, IOException {
        ADDRESS_URL = addressUrl;
        System.out.println("TestinMainClas setup runs ");
        String dimensions = System.getProperty("dimensions");
        int xDim = Integer.parseInt(dimensions.split(":")[0]);
        int yDim = Integer.parseInt(dimensions.split(":")[1]);
        System.out.println("Current driver is  " + System.getProperty("driver"));
        webDriver = BrowserFactory.getWebDriver(System.getProperty("driver"));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(xDim, yDim));
        System.out.println("TestinMainClas setup ends ");
    }

    @Test(groups = {"important"})
    public void registrationProcessTest() throws InterruptedException {
        System.out.println("REGISTRATIONPROCESSTEST starts ");
        home = new HomePage(webDriver).openAddress(ADDRESS_URL).goToRegistration().
                register(userId, userId, userId + "@example.com", "6characters");
        assertEquals(home.getUserEmail(), userId + "@example.com", "Verifying USERID");
        System.out.println("REGISTRATIONPROCESSTEST  ends ");
    }

    @Test(groups = {"important"}, dependsOnMethods = {"registrationProcessTest"})
    public void searchForProduct() throws InterruptedException {
        System.out.println("SEARCHFORPRODUCT  Starts ");
        System.out.println("TestinMainClas searchForProduct starts ");
        SearchResultPage searchResultPage = home.sendToSearchBox(PRODUCT_TO_LOOK_FOR);
        shoppingCart = searchResultPage.getItem(PRODUCT_TO_LOOK_FOR).navigateToCart();
        System.out.println("Product in the cart  " + shoppingCart.getProductsInShoppingCart());
        assertEquals(shoppingCart.getProductsInShoppingCart().toLowerCase(),PRODUCT_TO_LOOK_FOR.toLowerCase(),
                "Verifying HTC One Mini Blue is in the cart");
        System.out.println("SEARCHFORPRODUCT  ends ");
    }

    @Test(groups = {"important"}, dependsOnMethods = {"searchForProduct"})
    public void checkoutProcessTest() throws InterruptedException {
        System.out.println("CHECKOUTPROCESS  starts ");
//        Thread.sleep(1000);
        checkout = shoppingCart.goToCheckout();
        checkout.billingAddress().shippingMethod().paymentMethodAndInformation().paymentConfirmation();
        assertEquals(checkout.getFinalConfimationMessage().toLowerCase(), "Your order has been successfully processed!".toLowerCase(),
                "Final Confirmation message is displayed");
        System.out.println("CHECKOUTPROCESS  ends ");
        Thread.sleep(1000);
    }

    @AfterMethod(groups = {"important"})
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("\\Smart4Aviation\\target\\surefire-reports\\Smart4Aviation\\testScreenShot.jpg"));
        }
    }

    @AfterSuite(groups = {"important"})
    public void tearDown() {
        System.out.println("TearDown runs ");
        webDriver.quit();
    }
}
