package smart4aviation.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    private WebDriver webDriver;
    private HomePage home;
    private SearchResultPage searchResultPage;
    private Checkout checkout;
    private ShoppingCart shoppingCart;
    private static String userId = UUID.randomUUID().toString();

    @BeforeSuite(groups = {"include-test-one"})
    @Parameters({"URLAddress","Dimensions"})
    public void setUp(String addres, String dimensions) throws InterruptedException, IOException {
        int xDim = Integer.parseInt(dimensions.split(",")[0]);
        int yDim = Integer.parseInt(dimensions.split(",")[1]);
        System.out.println("TestinMainClas setup runs ");
        System.out.println(Integer.parseInt(dimensions.split(",")[0]));
        String mvnDriver = System.getProperty("driver");
        System.out.println("ddddddddddddddddrrrrrrrrrrrrrriiiiiver"+mvnDriver);

        System.out.println("Address z Parametru to : "+addres+" DrugiPraram "+dimensions);
//        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumTestNG\\src\\main\\resources\\chromedriver.exe");
//        this.webDriver = new ChromeDriver();
//        /////////////////////////////////
       System.out.println( "Moj DDDDDDDDriver "+System.getProperty("driver"));
//        System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
//        webDriver=new InternetExplorerDriver();
webDriver = BrowserFactory.getWebDriver(System.getProperty("driver"));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(xDim,yDim));//   maximize();
//        WebDriver augmentedDriver = new Augmenter().augment(webDriver);
//    ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        System.out.println("TestinMainClas setup ends ");
    }

    @Test(groups = {"include-test-one"})
    public void registrationProcessTest() throws InterruptedException {
        System.out.println("REGISTRATIONPROCESSTEST starts ");
        home = new HomePage(webDriver)
                .openAddress()
                .goToRegistration().register(userId, userId, userId + "@example.com", "6characters");
//        System.out.println( "USER EMAIL ++++++++++"+home.getUserEmail());
        assertEquals(home.getUserEmail(), userId + "@example.comA", "Verifying USERID");
        System.out.println("REGISTRATIONPROCESSTEST  ends ");
//        Thread.sleep(5000);
    }

    //    @BeforeTest(groups = {"include-test-one"})
//    public void openPageAndLogin() throws InterruptedException {
//        System.out.println("TestingMainClass openPageAndLogin runs ");
//        System.out.println(webDriver != null);
////        home = new HomePage(webDriver);
////        home.openAddress();
//
////        home.login("krzys@gmail.com","krzys24");
//        Thread.sleep(1000);
//    }
    @Test(groups = {"include-test-one"}, dependsOnMethods = {"registrationProcessTest"})
    public void searchForProduct() throws InterruptedException {
        System.out.println("SEARCHFORPRODUCT  Starts ");
        System.out.println("TestinMainClas searchForProduct starts ");
        SearchResultPage searchResultPage = home.sendToSearchBox("HTC One Mini Blue");

        shoppingCart = searchResultPage.getItem("HTC One Mini Blue").navigateToCart();
        System.out.println("+++++to jest w koszyku "+shoppingCart.getProductsInShoppingCart());
//        assertEquals(shoppingCart.getProductsInShoppingCart().toLowerCase(),"HTC One Mini Blue".toLowerCase(),
//                "Verifying HTC One Mini Blue is in the cart");
        System.out.println("SEARCHFORPRODUCT  ends ");

    }

    @Test(groups = {"include-test-one"}, dependsOnMethods = {"searchForProduct"})
    public void checkoutProcessTest() throws InterruptedException {
        System.out.println("CHECKOUTPROCESS  starts ");
        Thread.sleep(1000);
//        Assert.assertEquals("g","A");
        checkout = shoppingCart.goToCheckout();
        checkout.billingAddress().shippingMethod().paymentMethodAndInformation().
                paymentConfirmation();

        System.out.println("Final Confirmation Notification is present "+checkout.getFinalConfimationMessage());
        assertEquals(checkout.getFinalConfimationMessage().toLowerCase(),"Your order has been successfully processed!".toLowerCase(),
                "Final Confirmation message is displayed");
        System.out.println("TestinMainClas searchForProduct ends ");
        System.out.println("CHECKOUTPROCESS  ends ");
        Thread.sleep(1000);
    }
//
//    @Test(groups = {"include-test-one"})
//    public void addToCartTest() throws InterruptedException {
//        System.out.println("addToCart runs ");
//        NavigationMenu nv = home.getNavigationMenu();
//        nv.setDropDownBox("Books");
//        SearchResultPage searchResultPage = nv.sendToSearch("Selenium");
//        String firstResultTitle = searchResultPage.getFirstResultTitle();
//        ProductDetailsPage productDetailsPage = searchResultPage.clickFirstResult();
//        CheckOutPage checkOutPage = productDetailsPage.addToCart();
//        assert (checkOutPage.success().contentEquals("To jest ju koniec"));
//WebDriver augmentedDriver = new Augmenter().augment(driver);
//    ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
//    }
@AfterMethod(groups = {"include-test-one"})
public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
    System.out.println("takeScreenShotOnFailure  starts ");
    if (testResult.getStatus() == ITestResult.FAILURE) {
        System.out.println("FAILURRRRRRRE "+testResult.getStatus());
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("\\Smart4Aviation\\target\\surefire-reports\\Smart4Aviation\\testScreenShot.jpg"));
    }
}

    @AfterSuite(groups = {"include-test-one"})
    public void tearDown() {
        System.out.println("TearDown runs ");
        webDriver.quit();
    }
}
