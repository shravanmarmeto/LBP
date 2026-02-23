package Tests;

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

public class PLP_PDP_CART extends BaseClass {

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
		driver.get("https://lovebeautyandplanet.in/cart/clear");
	}
	
	// Case 11: Verify if user is able to navigate to PDP by clicking on product in
		// search PLP page
		@Test(priority = 11)
		public void hpPlpNavigation() throws InterruptedException {
			hp.getLogo().click();
			hp.getSearchTextField().click();
			Thread.sleep(2000);
			hp.getSearchTextField().sendKeys("hair");
			hp.getSearchButton().click();
			plp = new productListingPage(driver);
			String product = "Onion, Black Seed Oil & Patchouli Hair Mask";
			for (WebElement ele : plp.getProductLinksSearchPLP()) {
				if (ele.getText().equalsIgnoreCase(product)) {
					ele.click();
					break;
				}
			}
			pdp = new productDetailsPage(driver);
			String productActual = pdp.getProductTitle().getText();
			Assert.assertEquals(productActual.toLowerCase(), product.toLowerCase());

		}

		// Case 12: Verify if user is able to navigate to relevant page by clicking on
		// links in PDP page
		@Test(priority = 12)
		public void pdpToPlpNavigation() throws InterruptedException {
			pdp = new productDetailsPage(driver);
			pdp.getViewAllProductsLink().click();
			String expectedUrl = "https://lovebeautyandplanet.in/collections/all-products";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
			driver.navigate().back();
			pdp.getViewBundleProductsLink().click();
			String expectedUrl1 = "https://lovebeautyandplanet.in/collections/bundle-offers";
			String actualUrl1 = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl1, expectedUrl1);
			driver.navigate().back();

		}

		// Case 13: Verify if user is able to check delivery availability by entering
		// invalid pincode in PDP page
		@Test(priority = 13)
		public void invalidPincodeCheck() throws InterruptedException {
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getPincodeTextField());
			pdp.getPincodeTextField().sendKeys("123456");
			pdp.getCheckButton().click();
			webdriverUtility.scrollToElement(pdp.getAddToCartButton());
			String expectedErrorMessage = "Please enter a valid pincode";
			String actualErrorMessage = pdp.getInvalidPincodeErrorMessage().getText();
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		}

		// Case 14: Verify if user is able to check delivery availability by entering
		// valid pincode in PDP page
		@Test(priority = 14)
		public void validPincodeCheck() throws InterruptedException {
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getPincodeTextField());
			pdp.getPincodeTextField().clear();
			pdp.getPincodeTextField().sendKeys("560037");
			pdp.getCheckButton().click();
			webdriverUtility.scrollToElement(pdp.getAddToCartButton());
			boolean actualMessage = pdp.getValidPincodeErrorMessage().isDisplayed();
			Assert.assertTrue(actualMessage);
		}

		// Case 15: Verify if user is able to add product to cart from PDP page
		@Test(priority = 15)
		public void addToCartFromPDP() throws InterruptedException {
			pdp = new productDetailsPage(driver);
			String product = pdp.getProductTitle().getText();
			webdriverUtility.scrollToElement(pdp.getAddToCartButton());
			pdp.getAddToCartButton().click();
			cartDrawer c = new cartDrawer(driver);
			webdriverUtility.waitUntilElementIsVisible(c.getCheckoutButtonCartDrawer());
			String cartPro = c.getProductTitleCartDrawer().getText();
			Assert.assertEquals(cartPro.toLowerCase(), product.toLowerCase(),
					"Product in cart drawer is not same as the one added to cart");
			c.getCloseCartDrawerButton().click();
		}

		// Case 16: Verify if user is able to increase quantity from PDP page
		@Test(priority = 16)
		public void quantityIncrease() throws InterruptedException {
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getQuantityPlusButton());
			pdp.getQuantityPlusButton().click();
			cartDrawer c = new cartDrawer(driver);
			webdriverUtility.waitUntilElementIsVisible(c.getCartDrawer());
			webdriverUtility.waitUntilElementIsVisible(c.getQuantityTextFieldCartDrawer());
			Thread.sleep(2000);
			String cartQuantity = c.getQuantityTextFieldCartDrawer().getAttribute("value");
			c.getCloseCartDrawerButton().click();
			String quantity = pdp.getQuantityTextField().getAttribute("data-cart-quantity");
			
			Assert.assertEquals(quantity, cartQuantity, "Quantity in cart drawer is not same as the one selected in PDP");
		}
		// Case 17: Verify if user is able to decrease quantity from PDP page
			@Test(priority = 17, dependsOnMethods = {"quantityIncrease"})
			public void quantityDecrease() throws InterruptedException {
				Thread.sleep(2000);
				pdp = new productDetailsPage(driver);
				webdriverUtility.scrollToElement(pdp.getQuantityMinusButton());
				pdp.getQuantityMinusButton().click();
				cartDrawer c = new cartDrawer(driver);
				webdriverUtility.waitUntilElementIsVisible(c.getCartDrawer());
				webdriverUtility.waitUntilElementIsVisible(c.getQuantityTextFieldCartDrawer());
				String cartQuantity = c.getQuantityTextFieldCartDrawer().getAttribute("value");
				c.getCloseCartDrawerButton().click();
				String quantity = pdp.getQuantityTextField().getAttribute("data-cart-quantity");
				
				Assert.assertEquals(quantity, cartQuantity, "Quantity in cart drawer is not same as the one selected in PDP");
			}
			
		

		// case 18: Verify if Reviews section is displayed in PDP page
		@Test(priority = 18)
		public void reviewsSection() throws InterruptedException {
			Thread.sleep(2000);
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getReviewSection());
			boolean actual = pdp.getReviewSection().isDisplayed();
			Assert.assertTrue(actual, "Reviews section is not displayed in PDP page");
		}

		// case 19: Verify if details section is displayed in PDP page
		@Test(priority = 19)
		public void detailsSection() throws InterruptedException {
			Thread.sleep(2000);
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getDetailsTab());
			boolean actual = pdp.getDetailsTab().isDisplayed();
			Assert.assertTrue(actual, "Details section is not displayed in PDP page");
		}

		// case 20: Verify if YOU MAY ALSO LIKE section is displayed in PDP page
		@Test(priority = 20)
		public void youMayAlsoLikeSectionPDP() throws InterruptedException {
			Thread.sleep(2000);
			pdp = new productDetailsPage(driver);
			webdriverUtility.scrollToElement(pdp.getYouMayAlsoLikeSection());
			boolean actual = pdp.getYouMayAlsoLikeSection().isDisplayed();
			Assert.assertTrue(actual, "YOU MAY ALSO LIKE section is not displayed in PDP page");
			driver.get("https://lovebeautyandplanet.in/cart/clear");

		}
		// case 21: verify if empty cart is redirected to collections page
		@Test(priority = 21)
		public void emptyCart() {
			lp = new loginPage(driver);
			hp = new homePage(driver);
			hp.getLogo().click();
			hp.getCartLink().click();
			cartDrawer c = new cartDrawer(driver);
			c.getContinueShopping().click();
			String expected = "https://lovebeautyandplanet.in/collections/all";
			String actual = driver.getCurrentUrl();
			Assert.assertEquals(expected, actual, "user fails to redirect to collections page");

		}

		String product1price;
		String product2price;

		// case 22: Verify if user is able to add multiple product to cart from PDP page
		@Test(priority = 22)
		public void addToCartFromPDP2() throws InterruptedException {
			lp = new loginPage(driver);
			hp = new homePage(driver);
			hp.getSearchTextField().click();
			webdriverUtility.waitUntilElementIsVisible(hp.getSearchTextField());

			hp.getSearchTextField().sendKeys("hair");
			hp.getSearchButton().click();
			plp = new productListingPage(driver);
			String product = "Onion, Black Seed Oil & Patchouli Hair Mask";
			for (WebElement ele : plp.getProductLinksSearchPLP()) {
				if (ele.getText().equalsIgnoreCase(product)) {
					ele.click();
					break;
				}
			}
			pdp = new productDetailsPage(driver);
			product1price = pdp.getProductPrice().getText();
			webdriverUtility.scrollToElement(pdp.getAddToCartButton());
			pdp.getAddToCartButton().click();
			cartDrawer c = new cartDrawer(driver);
			webdriverUtility.waitUntilElementIsVisible(c.getCheckoutButtonCartDrawer());
			String cartPro = c.getProductTitleCartDrawer().getText();
			Assert.assertEquals(cartPro.toLowerCase(), product.toLowerCase(),
					"Product in cart drawer is not same as the one added to cart");
			c.getCloseCartDrawerButton().click();
			hp.getLogo().click();
			hp.getSearchTextField().click();
			webdriverUtility.waitUntilElementIsVisible(hp.getSearchTextField());
			hp.getSearchTextField().sendKeys("body");
			hp.getSearchButton().click();
			plp = new productListingPage(driver);
			String product2 = "Coconut Water & Mimosa Flower Conditioner - 200 ml";
			for (WebElement ele1 : plp.getProductLinksSearchPLP()) {
				if (ele1.getText().equalsIgnoreCase(product2)) {
					webdriverUtility.scrollToElement(ele1);
					ele1.click();
					break;
				}
			}
			pdp = new productDetailsPage(driver);
			product2price = pdp.getProductPrice().getText();
			webdriverUtility.waitUntilElementIsVisible(pdp.getProductTitle());
			webdriverUtility.scrollToElement(pdp.getAddToCart2());
			pdp.getAddToCart2().click();
			webdriverUtility.waitUntilElementIsVisible(c.getCheckoutButtonCartDrawer());
			String cartPro1 = c.getProductTitleCartDrawer().getText();
			Assert.assertEquals(cartPro1.toLowerCase(), product2.toLowerCase(),
					"Product in cart drawer is not same as the one added to cart");
		}

		// Case 23: Verify if cart price updates when user adds multiple quantity to cart
		@Test(priority = 23)
		public void priceUpdateVerification() throws InterruptedException {
			cartDrawer c = new cartDrawer(driver);
			String price = c.getTotalPrice().getText();
			String sumof2products = "₹" + String.valueOf(Integer.parseInt(product1price) + Integer.parseInt(product2price));
			Assert.assertEquals(price, sumof2products,
					"Total price is not updated correctly when user adds multiple product to cart");
			Thread.sleep(2000);

		}

		// case 24: Verify if freebie product is added to cart when user increase the
		// quantity
		@Test(priority = 24)
		public void freeBieProduct() throws InterruptedException {
			cartDrawer c = new cartDrawer(driver);
			boolean freebieProdut = c.getFreeBieproduct().isDisplayed();
			Assert.assertTrue(freebieProdut, "Freebie product is missing");

		}

		// case 25: Verify user is able to click on checkout button
		@Test(priority = 25, dependsOnMethods = "freeBieProduct")
		public void checkout() {
			cartDrawer c = new cartDrawer(driver);
			c.getCheckoutButtonCartDrawer().click();
			webdriverUtility.waitUntilElementIsVisible(c.getGokwikPopup());
			Assert.assertTrue(c.getGokwikPopup().isDisplayed(), "User is unable to navigate to checkout page");
			driver.navigate().refresh();
			driver.get("https://lovebeautyandplanet.in/cart/clear");
		}

}
