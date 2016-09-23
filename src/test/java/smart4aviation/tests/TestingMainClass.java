package smart4aviation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import smart4aviation.Checkout;
import smart4aviation.HomePage;
import smart4aviation.SearchResultPage;
import smart4aviation.ShoppingCart;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andy on 9/17/2016.
 */
public class TestingMainClass {
    private WebDriver webDriver;
    private HomePage home;
    private SearchResultPage searchResultPage;
    private Checkout checkout;
    private static String userId = UUID.randomUUID().toString();

    @BeforeSuite(groups = {"include-test-one"})
    public void setUp() throws InterruptedException {
//        System.out.println(webDriver != null);
        System.out.println("TestinMainClas setup runs ");
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumTestNG\\src\\main\\resources\\chromedriver.exe");
        this.webDriver = new ChromeDriver();
        System.out.println(webDriver != null);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        home =new HomePage(webDriver)
        .openAddress()
        .goToRegistration().register(userId,userId,userId+"@example.com","6characters");
        System.out.println("TestinMainClas setup ends ");

        Thread.sleep(20000);
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
    @Test(groups = {"include-test-one"})
    public void searchProduct() throws InterruptedException {
        System.out.println("TestinMainClas searchProduct starts ");
        SearchResultPage searchResultPage = home.sendToSearchBox("HTC One Mini Blue");
        searchResultPage.getItem("HTC One Mini Blue");
        ShoppingCart shoppingCart = searchResultPage.navigateToCart();
        Thread.sleep(1000);
        checkout = shoppingCart.goToCheckout();
        checkout.shippingInformations();

        System.out.println("TestinMainClas searchProduct ends ");
        Thread.sleep(10000);
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
//    }

    @AfterSuite(groups = {"include-test-one"})
    public void tearDown() {
        System.out.println("TearDown runs ");
        webDriver.quit();
    }
}
