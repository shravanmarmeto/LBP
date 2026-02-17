package Tests;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import genericUtility.BaseClass;
import genericUtility.webdriverUtility;
import pomScripts.cartDrawer;
import pomScripts.homePage;
import pomScripts.loginPage;
import pomScripts.productDetailsPage;

public class HomepageTest extends BaseClass {

	loginPage lp;
	homePage hp;
	productDetailsPage pdp;

	// Case 1: Verify logo is displayed in home page
	@Test(priority = 1)
	public void loginPageNavigation() {
		lp = new loginPage(driver);
		hp = new homePage(driver);
		lp.goToWebsite();
		Assert.assertTrue(hp.getLogo().isDisplayed());
	}

	// Case 2: Verify user is able to navigate product page by clicking on
	// announcement bar
	@Test(priority = 2)
	public void announcementBarNavigation() {
		hp = new homePage(driver);
		hp.getAnnouncementBar().click();
		String expectedUrl = "https://lovebeautyandplanet.in/collections/bundle-offers";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 3: Verify user is able to navigate to product page by clicking on
	// megamenu options
	@Test(priority = 3)
	public void megamenuNavigation() {
		hp = new homePage(driver);
		hp.getMegamenuShopLink().click();
		hp.getExploreByIngredientsLink().click();
		hp.getCurryLeavesBiotinMandarinProduct().click();
		String expectedUrl = "https://lovebeautyandplanet.in/collections/curry-leaves-biotin-mandarin";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 4: Verify user is able to navigate to About us page by clicking on about
	// us link in megamenu
	@Test(priority = 4)
	public void aboutUsPageNavigation() {
		hp = new homePage(driver);
		hp.getMegamenuAboutusLink().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/our-story";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 5: Verify user is able to navigate to know your ingredients page by
	// clicking on know your ingredients link in megamenu
	@Test(priority = 5)
	public void knowYourIngredientsPageNavigation() {
		hp = new homePage(driver);
		hp.getMegamenuKnowyouringredientsLink().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/ingredients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 6: Verify user is able to navigate to blog listing page by clicking on
	// beauty archives link in megamenu
	@Test(priority = 6)
	public void beautyArchivesPageNavigation() {
		hp = new homePage(driver);
		hp.getMegamenuBeautyArchivesLink().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/blogs";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 7: Verify user is able to navigate to Contact us page by clicking on
	// support link in megamenu
	@Test(priority = 7)
	public void contactUsPageNavigation() {
		hp = new homePage(driver);
		hp.getMegamenuSupportLink().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/contact-us";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		driver.navigate().back();
	}

	// Case 8: Verify user is able to slide hero banner using navigation arrows
	@Test(priority = 8)
	public void verifyHeroBannerNavigation() throws InterruptedException {

		hp = new homePage(driver);
		String firstBanner = hp.getActiveBannerImage();

		// Click Next 3 times
		hp.clickNext(3);

		// Click Prev 2 times
		hp.clickPrev(2);

		String finalBanner = hp.getActiveBannerImage();
		Assert.assertNotEquals(firstBanner, finalBanner, "Banner did not change after navigation");
	}

	// Case 9: Verify user is able to navigate to relevant page by clicking on hero
	// banner link
	@Test(priority = 9)
	public void verifyHeroBannerLinkNavigation() {
		hp = new homePage(driver);
		String homepage = driver.getCurrentUrl();
		hp.getActiveBanner().click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertNotEquals(homepage, currentUrl, "User is not redirected to Relevant Page");
		driver.navigate().back();
	}

	// Case 10: Verify if user is able to switch the tabs and navigate to relevant
	// page by clicking on view all link
	@Test(priority = 10)
	public void verifyCollectionTabsNavigation() {
		hp = new homePage(driver);
		for (int i = 0; i < hp.getAllCollectionTabs().size(); i++) {
			webdriverUtility.scrollToElement(hp.getCombosTab());
			hp.getAllCollectionTabs().get(i).click();
			webdriverUtility.scrollToElement(hp.getViewAllLink().get(i));
			webdriverUtility.elementToBeClickable(driver, hp.getViewAllLink().get(i));

			hp.getViewAllLink().get(i).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("collections"), "URL does not contain expected text");
			driver.navigate().back();
		}
	}

	// case 11: Verify user is able to navigate to relevant product page by clicking
	// on product in collection tab
	@Test(priority = 11)
	public void verifyProductNavigationFromCollectionTab() throws InterruptedException {
		driver.navigate().refresh();
		hp = new homePage(driver);
		String product = "Argan Oil & Lavender Hair Mask - 200 ml";
		for (WebElement pro : hp.getProductCardDescriptions()) {

			if (pro.getText().equalsIgnoreCase(product)) {
				webdriverUtility.scrollToElement(pro);
				pro.click();
				pdp = new productDetailsPage(driver);
				webdriverUtility.waitUntilElementIsVisible(pdp.getProductTitle());
				String actualProduct = pdp.getProductTitle().getText();
				System.out.println(actualProduct);
				Assert.assertEquals(actualProduct, product.toLowerCase(),
						"User is not navigated to relevant product page");
				driver.navigate().back();
				break;
			}
		}

	}

	// Case 12: Verify user is able to add a product to cart
	@Test(priority = 12)
	public void verifyAddToCartFunctionality() throws InterruptedException {
		driver.navigate().refresh();
		hp = new homePage(driver);
		String product = "Argan Oil & Lavender Hair Mask - 200 ml";
		for (WebElement pro : hp.getProductCardDescriptions()) {

			if (pro.getText().equalsIgnoreCase(product)) {
				webdriverUtility.scrollToElement(pro);
				webdriverUtility.scrollToElement(hp.getAddToCart().get(0));
				hp.getAddToCart().get(0).click();
			}
		}
		webdriverUtility.waitUntilElementIsVisible(hp.getViewCartButton());
		hp.getViewCartButton().click();
		Thread.sleep(3000);
		cartDrawer c = new cartDrawer(driver);
		String cartPro = c.getProductTitleCartDrawer().getText();
		Assert.assertEquals(cartPro.toLowerCase(), product.toLowerCase(),
				"Product in cart drawer is not same as the one added to cart");
		driver.get("https://lovebeautyandplanet.in/cart/clear");
		hp.getLogo().click();

	}

	// case 13: Verify shop by concern section
	@Test(priority = 13)
	public void verifyShopByConcernSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getFrizzConcern());
		Assert.assertTrue(hp.getShopByConcernSection().isDisplayed(), "Shop by concern section is not displayed");

		hp.getFrizzConcern().click();
		String expectedUrl = "https://lovebeautyandplanet.in/collections/argan-oil-lavender";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on frizz concern");
		driver.navigate().back();

		hp.getHairFallConcern().click();
		expectedUrl = "https://lovebeautyandplanet.in/collections/onion-blackseed-patchouli";
		actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on hair fall concern");
		driver.navigate().back();

		hp.getDamageConcern().click();
		expectedUrl = "https://lovebeautyandplanet.in/collections/bond-repair";
		actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on damage concern");
		driver.navigate().back();

		hp.getSplitEndsConcern().click();
		expectedUrl = "https://lovebeautyandplanet.in/collections/curry-leaves-biotin-mandarin";
		actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on split ends concern");
		driver.navigate().back();

		hp.getCurlCareConcern().click();
		expectedUrl = "https://lovebeautyandplanet.in/collections/rice-water-angelica";
		actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on curl care concern");
		driver.navigate().back();

		hp.getDandruffConcern().click();
		expectedUrl = "https://lovebeautyandplanet.in/collections/tea-tree-and-vetiver";
		actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to relevant page after clicking on dandruff concern");
		driver.navigate().back();

	}

	// Case 14: Verify discover beauty bill section
	@Test(priority = 14)
	public void verifyDiscoverBeautyBillSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getDiscoverBeautyBillSection());
		hp.getDiscoverBeautyBillSection().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/beauty-bill";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to beauty bill page after clicking on discover beauty bill section");
		driver.navigate().back();

	}

	// Case 15: Verify in the spot light section and add a product to cart
	@Test(priority = 15)
	public void verifyInTheSpotlightSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getWhatSetsUsApartSection());
		// Right 3 times
		int n = 3;
		hp.clickRightMultipleTimes(n);

		// Left 2 times

		hp.clickLeftMultipleTimes(n - 1);
		webdriverUtility.waitUntilElementIsVisible(hp.getActiveSlideProductName());
		String activeProduct = hp.getActiveSlideProductName().getText();

		// Click Add to Cart
		hp.getAddToCartButton().click();
		webdriverUtility.waitUntilElementIsVisible(hp.getViewCartButton());
		hp.getViewCartButton().click();
		cartDrawer c = new cartDrawer(driver);
		String cartPro = c.getProductTitleCartDrawer().getText();
		Assert.assertTrue(cartPro.toLowerCase().contains(activeProduct.toLowerCase()),
				"Product in cart drawer is not same as the one added to cart");
		driver.get("https://lovebeautyandplanet.in/cart/clear");
		hp.getLogo().click();

	}

	// Case 16: Verify what sets us apart section
	@Test(priority = 16)
	public void verifyWhatSetsUsApartSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getWhatSetsUsApartSectionContainer());
		Assert.assertTrue(hp.getWhatSetsUsApartSection().isDisplayed(), "What sets us apart section is not displayed");

		for (int i = 0; i < hp.getWhatSetsUsApartCards().size(); i++) {
			hp.getWhatSetsUsApartCards().get(i).click();
			Assert.assertTrue(hp.getWhatSetsUsApartCardTitles().get(i).isDisplayed(),
					"Content is not displayed after clicking on tab");
		}
	}

	// case 17: Verify reels section
	@Test(priority = 17)
	public void verifyReelsSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getReelsSectionHeading());
		Assert.assertTrue(hp.getReelsSectionHeading().isDisplayed(), "Reels section is not displayed");
	}

	// case 18: Verify customer love section
	@Test(priority = 18)
	public void verifyCustomerLoveSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getGifSection());
		Assert.assertTrue(hp.getCustomerLoveSectionHeading().isDisplayed(), "Customer love section is not displayed");
		// Right 3 times
		int n = 3;
		hp.clickRightMultipleTimesCustomerLove(n);

		// Left 2 times

		hp.clickLeftMultipleTimesCustomerLove(n - 1);
	}

	// case 19: Verify discover our story section
	@Test(priority = 19)
	public void verifyDiscoverOurStorySection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getDiscoverOurStorySection2());
		webdriverUtility.waitUntilElementIsVisible(hp.getDiscoverOurStorySection());
		hp.getDiscoverOurStorySection().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/our-story";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to our story page after clicking on discover our story section");
		driver.navigate().back();

	}

	// Case 20: Verify beauty archives section
	@Test(priority = 20)
	public void verifyBeautyArchivesSection() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getBeautyArchiveNextBtn());
		hp.getBeautyArchivesViewAllLink().click();
		String expectedUrl = "https://lovebeautyandplanet.in/pages/blogs";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to blogs page after clicking on beauty archives view all link");
		driver.navigate().back();
		String blog = "6 Post-Festive Hair Hacks That Will Make Your Hair Healthier Than Ever";
		for (int i = 0; i < hp.getBeautyArchivesArticleLinks().size(); i++) {
			if (hp.getBeautyArchivesArticleLinks().get(i).getText().equalsIgnoreCase(blog)) {
				hp.getBeautyArchivesArticleLinks().get(i).click();
				String actualBlogTitle = hp.getBlogHeading().getText();
				Assert.assertEquals(actualBlogTitle, blog,
						"User is not navigated to relevant blog page after clicking on blog");
				driver.navigate().back();
				break;
			}
		}
	}

	// Case 21: Verify the newsletter subscription functionality
	@Test(priority = 21)
	public void verifyNewsletterSubscription() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getProductLinksFooterHeading());
		Faker faker = new Faker();
		hp.getNewsletterEmailTextField().sendKeys(faker.internet().emailAddress());
		hp.getNewsletterCheckbox().click();
		hp.getNewsletterSubscribeButton().click();
		webdriverUtility.waitUntilElementIsVisible(hp.getNewsletterSuccessMessage());
		Assert.assertTrue(hp.getNewsletterSuccessMessage().isDisplayed(),
				"Success message is not displayed after subscribing to newsletter");

	}

	// Case 22: Verify the footer product links
	@Test(priority = 22)
	public void verifyFooterProductLinks() {
		hp = new homePage(driver);
		// webdriverUtility.scrollToElement(hp.getBeBeautifulSection());
		for (int i = 0; i < hp.getProductLinksFooter().size(); i++) {
			webdriverUtility.scrollToElement(hp.getProductLinksFooter().get(i));
			String linkName = hp.getProductLinksFooter().get(i).getText();
			hp.getProductLinksFooter().get(i).click();
			String currentUrl = driver.getCurrentUrl();
			String url = currentUrl.toLowerCase();
			String[] words = linkName.toLowerCase().split(" ");

			boolean matchFound = false;

			for (String word : words) {
				String root = word.replaceAll("(es|s)$", ""); // remove plural

				if (url.contains(root)) {
					matchFound = true;
					break;
				}
			}

			Assert.assertTrue(matchFound,
					"User is not navigated to relevant page after clicking on footer link: " + linkName);
			driver.navigate().back();
		}
	}

	// case 23: Verify the concern links in footer
	@Test(priority = 23)
	public void verifyFooterConcernLinks() {
		hp = new homePage(driver);
		for (int i = 0; i < hp.getConcernsFooter().size(); i++) {
			webdriverUtility.scrollToElement(hp.getConcernsFooter().get(i));
			String linkName = hp.getConcernsFooter().get(i).getText();
			hp.getConcernsFooter().get(i).click();
			String currentUrl = driver.getCurrentUrl();
			String url = currentUrl.toLowerCase();
			String[] words = linkName.toLowerCase().split(" ");

			boolean matchFound = false;

			for (String word : words) {
				String root = word.replaceAll("(es|s)$", ""); // remove plural

				if (url.contains(root)) {
					matchFound = true;
					break;
				}
			}

			Assert.assertTrue(matchFound,
					"User is not navigated to relevant page after clicking on footer link: " + linkName);
			driver.navigate().back();
		}
	}

	// case 24: Verify the about us and quick links in footer
	@Test(priority = 24)
	public void verifyFooterQuickLinks() {
		hp = new homePage(driver);
		for (int i = 0; i < hp.getQuickLinksFooter().size(); i++) {
			webdriverUtility.scrollToElement(hp.getQuickLinksFooter().get(i));
			String linkName = hp.getQuickLinksFooter().get(i).getText();
			hp.getQuickLinksFooter().get(i).click();
			String currentUrl = driver.getCurrentUrl();
			String url = currentUrl.toLowerCase();
			String[] words = linkName.toLowerCase().split(" ");

			boolean matchFound = false;

			for (String word : words) {
				String root = word.replaceAll("(es|s)$", ""); // remove plural

				if (url.contains(root)) {
					matchFound = true;
					break;
				}
			}

			Assert.assertTrue(matchFound,
					"User is not navigated to relevant page after clicking on footer link: " + linkName);
			driver.navigate().back();
		}

	}

	// Case 25: Verify the legal links in footer
	@Test(priority = 25)
	public void verifyFooterLegalLinks() {

		hp = new homePage(driver);

		for (int i = 0; i < hp.getLegalFooter().size(); i++) {

			webdriverUtility.scrollToElement(hp.getLegalFooter().get(i));

			String linkName = hp.getLegalFooter().get(i).getText();

			String parentWindow = driver.getWindowHandle();
			int windowsBefore = driver.getWindowHandles().size();

			hp.getLegalFooter().get(i).click();

			// Wait a moment for navigation/tab opening
			new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(d -> driver.getWindowHandles().size() >= windowsBefore);

			int windowsAfter = driver.getWindowHandles().size();

			// 🔥 CASE 1 — NEW TAB OPENED
			if (windowsAfter > windowsBefore) {

				for (String window : driver.getWindowHandles()) {
					if (!window.equals(parentWindow)) {
						driver.switchTo().window(window);
						break;
					}
				}

			}
			// 🔥 CASE 2 — SAME TAB NAVIGATION
			else {
				new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/"));
			}

			// ===== ASSERT URL (FIRST 4 LETTER MATCH) =====

			String currentUrl = driver.getCurrentUrl().toLowerCase();

			// Normalize URL → remove symbols, hyphens, spaces
			String normalizedUrl = currentUrl.replaceAll("[^a-z]", "");

			// Normalize link text
			String normalizedLink = linkName.toLowerCase().replaceAll("[^a-z]", "");

			// Take first 4 letters (or less if word small)
			int compareLength = Math.min(4, normalizedLink.length());
			String prefix = normalizedLink.substring(0, compareLength);

			boolean matchFound = normalizedUrl.contains(prefix);

			Assert.assertTrue(matchFound, "User is not navigated correctly after clicking footer link: " + linkName
					+ " | Expected prefix: " + prefix + " | Actual URL: " + currentUrl);

			// 🔥 CLOSE CHILD TAB OR GO BACK
			if (!driver.getWindowHandle().equals(parentWindow)) {
				driver.close(); // close new tab
				driver.switchTo().window(parentWindow);
			} else {
				driver.navigate().back(); // same tab
			}
		}

	}

	// case 26: Verify the social media links in footer
	@Test(priority = 26)
	public void verifyFooterSocialMediaLinks() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getFacebookLink());
		hp.getFacebookLink().click();
		Set<String> windows = driver.getWindowHandles();
		String parent = driver.getWindowHandle();
		for (String window : windows) {
			if (!window.equals(parent)) {
				driver.switchTo().window(window);
				break;
			}
		}
		String expectedUrl = "https://www.facebook.com/lovebeautyandplanetin";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl,
				"User is not navigated to Facebook page after clicking on Facebook link");
		driver.close();
		driver.switchTo().window(parent);
		webdriverUtility.scrollToElement(hp.getInstagramLink());
		hp.getInstagramLink().click();
		Set<String> windows1 = driver.getWindowHandles();
		String parent1 = driver.getWindowHandle();
		for (String window : windows1) {
			if (!window.equals(parent1)) {
				driver.switchTo().window(window);
				break;
			}
		}
		String expectedUrlinsta = "https://www.instagram.com/lovebeautyandplanet_in/";
		String actualUrlinsta = driver.getCurrentUrl();
		Assert.assertEquals(actualUrlinsta, expectedUrlinsta,
				"User is not navigated to Instagram page after clicking on Facebook link");
		driver.close();
		driver.switchTo().window(parent);
		webdriverUtility.scrollToElement(hp.getYoutubeLink());
		hp.getYoutubeLink().click();
		Set<String> windows2 = driver.getWindowHandles();
		String parent2 = driver.getWindowHandle();
		for (String window : windows2) {
			if (!window.equals(parent2)) {
				driver.switchTo().window(window);
				break;
			}
		}
		String expectedUrlYT = "https://www.youtube.com/channel/UCUFcrEf1Wb1164G6O2_LoyA";
		String actualUrlYT = driver.getCurrentUrl();
		Assert.assertEquals(actualUrlYT, expectedUrlYT,
				"User is not navigated to Youtube page after clicking on Facebook link");
		driver.close();
		driver.switchTo().window(parent);
	}

	// Case 27: Verify the Caution notice
	@Test(priority = 27)
	public void verifyCautionNotice() {
		hp = new homePage(driver);
		webdriverUtility.scrollToElement(hp.getCautionNotice());
		Assert.assertTrue(hp.getCautionNotice().isDisplayed(), "Caution notice is not displayed in the footer");
	}

}
