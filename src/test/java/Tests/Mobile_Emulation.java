package Tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import genericUtility.webdriverUtility;
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

	@Test(priority = 1)
	public void logoVerification() {
		driver.get("https://www.lovebeautyandplanet.com/");
		driver.manage().window().maximize();
		WebElement logo = driver.findElement(By.xpath("//img[@alt='Love Beauty and Planet']"));
		Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed on the homepage");
	}

	@Test(priority = 2)
	public void discoverbeautysection() throws InterruptedException {

		WebElement a = driver.findElement(By.xpath("(//section[@class='home-ourstory']//img)[1]"));

		 JavascriptExecutor js = (JavascriptExecutor) driver;

		    // Scroll gradually to load lazy content
		    for (int i = 0; i < 6; i++) {
		        js.executeScript("window.scrollBy(0, 250)");
		        Thread.sleep(1000);
		    }

		a.click();

		String expectedUrl = "https://lovebeautyandplanet.in/pages/beauty-bill";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
	}

	// @AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			// driver.quit();
		}
	}
}