package pomScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class productListingPage {

	WebDriver driver;
	WebDriverWait wait;

	public productListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	
	@FindBy(xpath = "//span[@class='facet-checkbox__text']")
	private List<WebElement> allFilters;

	public List<WebElement> getAllFilters() {
		return allFilters;
	}

	public void setAllFilters(List<WebElement> allFilters) {
		this.allFilters = allFilters;
	}
	
	@FindBy(xpath = "//b[@id='verticalTitle']")
	private WebElement filterHeading;
	@FindBy(xpath = "//a[@class='active-facets__button active-facets__button--light']")
	private List<WebElement> appliedFilter;
	@FindBy(xpath = "(//span[text()='Clear all'])[1]")
	private WebElement clearAllLink;

	public WebElement getFilterHeading() {
		return filterHeading;
	}

	public void setFilterHeading(WebElement filterHeading) {
		this.filterHeading = filterHeading;
	}

	public List<WebElement> getAppliedFilter() {
		return appliedFilter;
	}

	public void setAppliedFilter(List<WebElement> appliedFilter) {
		this.appliedFilter = appliedFilter;
	}

	public WebElement getClearAllLink() {
		return clearAllLink;
	}

	public void setClearAllLink(WebElement clearAllLink) {
		this.clearAllLink = clearAllLink;
	}
	@FindBy(xpath = "//p[@class='h-pro-card-cnt-description']")
	private List<WebElement> productTitlesPLP;

	public List<WebElement> getProductTitlesPLP() {
		return productTitlesPLP;
	}

	public void setProductTitlesPLP(List<WebElement> productTitlesPLP) {
		this.productTitlesPLP = productTitlesPLP;
	}
	@FindBy(xpath = "//button[@name='add']")
	private List<WebElement> addToCartButtons;

	public List<WebElement> getAddToCartButtons() {
		return addToCartButtons;
	}

	public void setAddToCartButtons(List<WebElement> addToCartButtons) {
		this.addToCartButtons = addToCartButtons;
	}
	
	@FindBy(xpath = "//div[@class='add-to-cart-pop']")
	private WebElement addToCartPopup;
	@FindBy(xpath = "//button[@id='view-cart-drawer']")
	private WebElement viewCartButton;

	public WebElement getAddToCartPopup() {
		return addToCartPopup;
	}

	public void setAddToCartPopup(WebElement addToCartPopup) {
		this.addToCartPopup = addToCartPopup;
	}

	public WebElement getViewCartButton() {
		return viewCartButton;
	}

	public void setViewCartButton(WebElement viewCartButton) {
		this.viewCartButton = viewCartButton;
	}
	@FindBy(xpath = "//h2[text()='you may also like']")
	private WebElement youMayAlsoLikeSection;
	@FindBy(xpath = "//h2[text()='Beauty Archives']")
	private WebElement beautyArchivesSection;
	@FindBy(xpath = "//a[text()='View all']")
	private WebElement viewAllLink;
	@FindBy(xpath = "//h2[text()='faqs']")
	private WebElement faqsSection;
	@FindBy(xpath = "//a[text()='Write to us']")
	private WebElement writeToUsLink;

	public WebElement getYouMayAlsoLikeSection() {
		return youMayAlsoLikeSection;
	}

	public void setYouMayAlsoLikeSection(WebElement youMayAlsoLikeSection) {
		this.youMayAlsoLikeSection = youMayAlsoLikeSection;
	}

	public WebElement getBeautyArchivesSection() {
		return beautyArchivesSection;
	}

	public void setBeautyArchivesSection(WebElement beautyArchivesSection) {
		this.beautyArchivesSection = beautyArchivesSection;
	}

	public WebElement getViewAllLink() {
		return viewAllLink;
	}

	public void setViewAllLink(WebElement viewAllLink) {
		this.viewAllLink = viewAllLink;
	}

	public WebElement getFaqsSection() {
		return faqsSection;
	}

	public void setFaqsSection(WebElement faqsSection) {
		this.faqsSection = faqsSection;
	}

	public WebElement getWriteToUsLink() {
		return writeToUsLink;
	}

	public void setWriteToUsLink(WebElement writeToUsLink) {
		this.writeToUsLink = writeToUsLink;
	}
	
	
	@FindBy(xpath = "//div[@class='result-product-item-info h-pro-card-cnt']//a")
	private List<WebElement> productLinksSearchPLP;

	public List<WebElement> getProductLinksSearchPLP() {
		return productLinksSearchPLP;
	}

	public void setProductLinksSearchPLP(List<WebElement> productLinksSearchPLP) {
		this.productLinksSearchPLP = productLinksSearchPLP;
	}
	
	
	
}
