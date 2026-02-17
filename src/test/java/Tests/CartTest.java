package Tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.webdriverUtility;
import pomScripts.cartDrawer;
import pomScripts.homePage;
import pomScripts.loginPage;
import pomScripts.productDetailsPage;
import pomScripts.productListingPage;

public class CartTest extends BaseClass {

	loginPage lp;
	homePage hp;
	productDetailsPage pdp;
	productListingPage plp;

	// case 1: Verify if user is able to add multiple product to cart from PDP page
	@Test(priority = 1)
	public void addToCartFromPDP() throws InterruptedException {
		lp = new loginPage(driver);
		hp = new homePage(driver);
		lp.goToWebsite();
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
		Thread.sleep(2000);
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
		webdriverUtility.waitUntilElementIsVisible(pdp.getProductTitle());
		webdriverUtility.scrollToElement(pdp.getAddToCart2());
		pdp.getAddToCart2().click();
		webdriverUtility.waitUntilElementIsVisible(c.getCheckoutButtonCartDrawer());
		String cartPro1 = c.getProductTitleCartDrawer().getText();
		Assert.assertEquals(cartPro1.toLowerCase(), product2.toLowerCase(),
				"Product in cart drawer is not same as the one added to cart");
		c.getCloseCartDrawerButton().click();
		driver.get("https://lovebeautyandplanet.in/cart/clear");
	}

	// Case2: Verify if cart price updates when user adds multiple quantity to cart
	@Test(priority = 2)
	public void priceUpdate() throws InterruptedException {
		lp = new loginPage(driver);
		hp = new homePage(driver);
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
		webdriverUtility.scrollToElement(pdp.getAddToCartButton());
		pdp.getAddToCartButton().click();
		cartDrawer c = new cartDrawer(driver);
		String price = c.getProductPrice().getText();
		c.getPlusButton().click();
		String cartQuantity = c.getQuantityTextFieldCartDrawer().getAttribute("value");
		int updatedValue = Integer.parseInt(price) * Integer.parseInt(cartQuantity);
		Assert.assertEquals(c.getTotalPrice(), String.valueOf(updatedValue));
		driver.get("https://lovebeautyandplanet.in/cart/clear");

	}

	// Case3: Verify if cart price updates when user adds multiple quantity to cart
	//@Test(priority = 3)
	public void priceUpdate2() throws InterruptedException {
		lp = new loginPage(driver);
		hp = new homePage(driver);
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
		String price=pdp.getProductPrice().getText();
		webdriverUtility.scrollToElement(pdp.getAddToCartButton());
		pdp.getAddToCartButton().click();
		cartDrawer c = new cartDrawer(driver);
		webdriverUtility.waitUntilElementIsVisible(c.getCheckoutButtonCartDrawer());
		c.getCloseCartDrawerButton().click();
		webdriverUtility.scrollToElement(pdp.getQuantityPlusButton());
		pdp.getQuantityPlusButton().click();
		String quantity = pdp.getQuantityTextField().getAttribute("data-cart-quantity");
		int updatedPrice = Integer.parseInt(price) * Integer.parseInt(quantity);
		Assert.assertEquals(c.getTotalPrice(), String.valueOf(updatedPrice));
	}

	
	// case4: Verify if freebie product is added to cart when user increase the
	// quantity
//	@Test(priority = 4)
	public void freeBieProduct() {
		cartDrawer c = new cartDrawer(driver);
		boolean freebieProdut = c.getFreeBieproduct().isDisplayed();
		Assert.assertTrue(freebieProdut, "Freebie product is missing");

	}

	// case 5: Verify user is able to click on checkout button
	//@Test(priority = 5)
	public void checkout() {
		cartDrawer c = new cartDrawer(driver);
		c.getCheckoutButtonCartDrawer().click();
		driver.switchTo().frame(c.getGokwikPopup());
		Assert.assertTrue(c.getGokwikPopup().isDisplayed(), "User is unable to navigate to checkout page");
		driver.navigate().refresh();
	}

	// case 6: verify whether user is able to delete the product from cart
//	@Test(priority = 6)
	public void deleteProduct() {
		cartDrawer c = new cartDrawer(driver);
		hp = new homePage(driver);
		hp.getCartLink().click();
		webdriverUtility.scrollToElement(c.getDeleteButton());
		c.getDeleteButton().click();
		Assert.assertTrue(c.getEmptycartText().isDisplayed(), "Product is not deleted from cart");

	}

	// case 7: verify if empty cart is redirected to collections page
	//@Test(priority = 7)
	public void emptyCartRedirection() {
		cartDrawer c = new cartDrawer(driver);
		c.getContinueShopping().click();
		String expected = "https://lovebeautyandplanet.in/collections/all";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(expected, actual, "user fails to redirect to collections page");
	}

}
