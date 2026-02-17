package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.webdriverUtility;
import pomScripts.cartDrawer;
import pomScripts.homePage;
import pomScripts.loginPage;
import pomScripts.productDetailsPage;
import pomScripts.productListingPage;

public class ProductlistingpageTest extends BaseClass {

	loginPage lp;
	homePage hp;
	productDetailsPage pdp;
	productListingPage plp;

	// Case 1: Verify if user is able to navigate to PLP by clicking on mega-menu
	// options
	@Test(priority = 1)
	public void hpToPlpNavigation() {
		lp = new loginPage(driver);
		hp = new homePage(driver);
		lp.goToWebsite();
		hp.getMegamenuOffersLink().click();
		hp.getBuy3At1399Link().click();
		String expectedUrl = "https://lovebeautyandplanet.in/collections/buy3-1399";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	// Case 2: Verify applied filters are displayed in PLP page
	@Test(priority = 2)
	public void appliedFilters() {

		plp = new productListingPage(driver);

		List<String> expectedFilters = Arrays.asList("Onion & Rosemary", "Hair Care");

		int selectedCount = 0;

		for (int i = 0; i < plp.getAllFilters().size(); i++) {

			String filterText = plp.getAllFilters().get(i).getText();

			webdriverUtility.scrollToElement(plp.getAllFilters().get(i));

			if (expectedFilters.contains(filterText)) {

				plp.getAllFilters().get(i).click();
				selectedCount++;

				webdriverUtility.waitForPageToLoad(driver);

				if (selectedCount == expectedFilters.size()) {
					break;
				}
			}
		}

		// 🔽 Scroll to Applied Filter section
		webdriverUtility.scrollToElement(plp.getFilterHeading());
		Assert.assertTrue(plp.getClearAllLink().isDisplayed(), "Filters are not applied");
		driver.navigate().refresh();
	}

	// Case 3: Verify if relevant products are displayed on applying filters in PLP
	// page
	@Test(priority = 3)
	public void relevantProdutRedirection() {

		WebElement firstProduct = plp.getProductTitlesPLP().get(0);
		webdriverUtility.waitUntilElementIsVisible(firstProduct);
		Actions actions = new Actions(driver);
		actions.moveToElement(firstProduct).perform();
		webdriverUtility.waitUntilClickable(firstProduct);
		String productTitle = firstProduct.getText();
		firstProduct.click();

		pdp = new productDetailsPage(driver);
		webdriverUtility.waitForPageToLoad(driver);
		String actualPro = pdp.getProductTitle().getText();
		Assert.assertEquals(actualPro, productTitle, "Product title mismatch after redirection");
		driver.navigate().back();
	}

	// Case 4: Verify if user is able to clear applied filters in PLP page
	@Test(priority = 4)
	public void clearFilters() throws InterruptedException {
		Thread.sleep(2000);
		plp = new productListingPage(driver);
		webdriverUtility.scrollToElement(plp.getFilterHeading());
		plp.getClearAllLink().click();
		webdriverUtility.waitForPageToLoad(driver);
		Assert.assertTrue(plp.getClearAllLink().isDisplayed(), "Filters are not cleared");
		driver.navigate().refresh();

	}

	// case 5: Verify Add to Cart functionality from PLP page
	@Test(priority = 5)
	public void addToCartFromPLP() {
		WebElement firstProduct = plp.getProductTitlesPLP().get(0);
		webdriverUtility.waitUntilElementIsVisible(firstProduct);
		String product = firstProduct.getText();
		webdriverUtility.scrollToElement(plp.getProductTitlesPLP().get(0));
		webdriverUtility.scrollToElement(plp.getAddToCartButtons().get(0));
		plp.getAddToCartButtons().get(0).click();
		webdriverUtility.waitUntilElementIsVisible(plp.getViewCartButton());
		plp.getViewCartButton().click();
		cartDrawer c = new cartDrawer(driver);
		String cartPro = c.getProductTitleCartDrawer().getText();
		Assert.assertEquals(cartPro.toLowerCase(), product.toLowerCase(),
				"Product in cart drawer is not same as the one added to cart");
		driver.navigate().refresh();

	}

	// case 6: Verify if You May Also Like section is displayed in PLP page
	@Test(priority = 6)
	public void youMayAlsoLikeSection() {
		webdriverUtility.scrollToElement(plp.getYouMayAlsoLikeSection());
		Assert.assertTrue(plp.getYouMayAlsoLikeSection().isDisplayed(),
				"You May Also Like section is not displayed in PLP page");
	}

	// case 7: Verify if beauty Archives section is displayed in PLP page
	@Test(priority = 7)
	public void beautyArchivesSection() {
		webdriverUtility.scrollToElement(plp.getBeautyArchivesSection());
		Assert.assertTrue(plp.getBeautyArchivesSection().isDisplayed(),
				"Beauty Archives section is not displayed in PLP page");
	}

	// case 8: Verify if view all redirects to correct page in PLP page
	@Test(priority = 8)
	public void viewAllRedirection() {
		webdriverUtility.scrollToElement(plp.getViewAllLink());
		plp.getViewAllLink().click();
		webdriverUtility.waitForPageToLoad(driver);
		String expectedUrl = "https://lovebeautyandplanet.in/blogs/hair";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "View All link is not redirecting to correct page");
		driver.navigate().back();
	}

	// case 9: Verify if FAQs section is displayed in PLP page
	@Test(priority = 9)
	public void faqsSection() {
		webdriverUtility.scrollToElement(plp.getFaqsSection());
		webdriverUtility.waitUntilElementIsVisible(plp.getFaqsSection());
		Assert.assertTrue(plp.getFaqsSection().isDisplayed(), "FAQs section is not displayed in PLP page");
	}

	// case 10: Verify if Write to us link redirects to correct page in PLP page
	@Test(priority = 10)
	public void writeToUsRedirection() {
		webdriverUtility.scrollToElement(plp.getWriteToUsLink());
		plp.getWriteToUsLink().click();
		webdriverUtility.waitForPageToLoad(driver);
		String expectedUrl = "https://lovebeautyandplanet.in/pages/contact-us";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Write to us link is not redirecting to correct page");
		driver.navigate().back();
	}
}
