package Tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pomScripts.homePage;
import pomScripts.loginPage;

public class Browserstack {

    public static final String USERNAME = "manurajbalakrish_EkKGOl";
    public static final String ACCESS_KEY = "MMndxY6zSdyopqJPMKqB";
    public static final String URL_STRING =
            "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    WebDriver driver;
    loginPage lp;
    homePage hp;

    Faker f = new Faker();
    String firstName = f.name().firstName();
    String lastName = f.name().lastName();
    String email = f.internet().emailAddress();
    String phone = f.number().numberBetween(6000000000L, 9999999999L) + "";
    String password = f.internet().password(8, 16);

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
//        caps.setCapability("os", "Windows");
//        caps.setCapability("osVersion", "11");
//        caps.setCapability("name", "Homepage Test");

        driver = new RemoteWebDriver(new URL(URL_STRING), caps);
        //driver.get("https://your-shopify-store-url.com");

        lp = new loginPage(driver);
        hp = new homePage(driver);
    }

    // Case 1: Verify navigation to login page
    @Test(priority = 1)
    public void loginPageNavigation() {
        lp.goToWebsite();
        hp.getAccountLink().click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
    }

    // Case 2: Create account
   // @Test(priority = 2)
    public void createAccount() {
        lp.createAccount(firstName, lastName, email, phone, password);
        hp.getAccountLink().click();
        Assert.assertTrue(lp.getAccountTitle().isDisplayed());
        lp.getLogoutLink().click();
    }

    // Case 3: Login with valid credentials
   // @Test(priority = 3)
    public void login() {
        lp.goToWebsite();
        hp.getAccountLink().click();
        lp.login(email, password);
        Assert.assertTrue(lp.getAccountTitle().isDisplayed());
        lp.getLogoutLink().click();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}