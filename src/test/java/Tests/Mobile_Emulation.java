package Tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pomScripts.homePage;
import pomScripts.loginPage;

public class Mobile_Emulation {

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
    public void setup() {

        // 📱 Mobile Emulation (iPhone X size 375x812)
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 812);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15A372 Safari/604.1");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = new ChromeDriver(options);

        // Initialize Page Objects
        lp = new loginPage(driver);
        hp = new homePage(driver);
    }

    // ✅ Case 1: Verify navigation to login page
    @Test(priority = 1)
    public void loginPageNavigation() {

        lp.goToWebsite();
        hp.getAccountLink().click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
    }

    // ✅ Case 2: Create account
    //@Test(priority = 2)
    public void createAccount() {

        lp.goToWebsite();
        hp.getAccountLink().click();

        lp.createAccount(firstName, lastName, email, phone, password);
        Assert.assertTrue(lp.getAccountTitle().isDisplayed());

        lp.getLogoutLink().click();
    }

    // ✅ Case 3: Login with valid credentials
   // @Test(priority = 3)
    public void login() {

        lp.goToWebsite();
        hp.getAccountLink().click();

        lp.login(email, password);
        Assert.assertTrue(lp.getAccountTitle().isDisplayed());

        lp.getLogoutLink().click();
    }

   // @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}