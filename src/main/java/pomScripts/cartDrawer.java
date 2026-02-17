package pomScripts;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.webdriverUtility;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class cartDrawer {
	WebDriver driver;
	WebDriverWait wait;

	public cartDrawer(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@FindBy(xpath = "//a[contains(@class,'cart-item__name')]")
	private WebElement productTitleCartDrawer;

	public WebElement getProductTitleCartDrawer() {
		return productTitleCartDrawer;
	}

	public void setProductTitleCartDrawer(WebElement productTitleCartDrawer) {
		this.productTitleCartDrawer = productTitleCartDrawer;
	}

	@FindBy(xpath = "//button[@class='drawer__close']")
	private WebElement closeCartDrawerButton;

	public WebElement getCloseCartDrawerButton() {
		return closeCartDrawerButton;
	}

	public void setCloseCartDrawerButton(WebElement closeCartDrawerButton) {
		this.closeCartDrawerButton = closeCartDrawerButton;
	}
	@FindBy(xpath = "(//input[contains(@class,'quantity__input')])[2]")
	private WebElement quantityTextFieldCartDrawer;

	public WebElement getQuantityTextFieldCartDrawer() {
		return quantityTextFieldCartDrawer;
	}

	public void setQuantityTextFieldCartDrawer(WebElement quantityTextFieldCartDrawer) {
		this.quantityTextFieldCartDrawer = quantityTextFieldCartDrawer;
	}
	@FindBy(xpath = "//div[@class='gokwik-checkout']")
	private WebElement checkoutButtonCartDrawer;

	public WebElement getCheckoutButtonCartDrawer() {
		return checkoutButtonCartDrawer;
	}

	public void setCheckoutButtonCartDrawer(WebElement checkoutButtonCartDrawer) {
		this.checkoutButtonCartDrawer = checkoutButtonCartDrawer;
	}
	@FindBy(xpath = "//div[@aria-label='Your cart']")
	private WebElement cartDrawer;

	public WebElement getCartDrawer() {
		return cartDrawer;
	}

	public void setCartDrawer(WebElement cartDrawer) {
		this.cartDrawer = cartDrawer;
	}
	
	@FindBy(xpath = "//div[@class='quantity-popover-container']//button[@name='plus']")
	private WebElement plusButton;
	@FindBy(xpath = "//div[@class='quantity-popover-container']//button[@name='minus']")
	private WebElement minusButton;
	@FindBy(xpath = "//button[@class='button button--tertiary cart-remove-button']")
	private WebElement deleteButton;
	@FindBy(xpath = "//p[@class='totals__total-value']")
	private WebElement totalPrice;
	@FindBy(xpath = "//div[@class='freebie-text']")
	private WebElement freeBieproduct;
	@FindBy(xpath = "//iframe[@id='gokwik-iframe']")
	private WebElement gokwikPopup;
	@FindBy(xpath = "//h2[@class='cart__empty-text']")
	private WebElement emptycartText;
	@FindBy(xpath = "//a[@href='/collections/all']")
	private WebElement continueShopping;

	public WebElement getPlusButton() {
		return plusButton;
	}

	public void setPlusButton(WebElement plusButton) {
		this.plusButton = plusButton;
	}

	public WebElement getMinusButton() {
		return minusButton;
	}

	public void setMinusButton(WebElement minusButton) {
		this.minusButton = minusButton;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(WebElement deleteButton) {
		this.deleteButton = deleteButton;
	}

	public WebElement getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(WebElement totalPrice) {
		this.totalPrice = totalPrice;
	}

	public WebElement getFreeBieproduct() {
		return freeBieproduct;
	}

	public void setFreeBieproduct(WebElement freeBieproduct) {
		this.freeBieproduct = freeBieproduct;
	}

	public WebElement getGokwikPopup() {
		return gokwikPopup;
	}

	public void setGokwikPopup(WebElement gokwikPopup) {
		this.gokwikPopup = gokwikPopup;
	}

	public WebElement getEmptycartText() {
		return emptycartText;
	}

	public void setEmptycartText(WebElement emptycartText) {
		this.emptycartText = emptycartText;
	}

	public WebElement getContinueShopping() {
		return continueShopping;
	}

	public void setContinueShopping(WebElement continueShopping) {
		this.continueShopping = continueShopping;
	}
	@FindBy(xpath = "//div[@id='CartDrawer-CartItems']//span[@class='pro-variant-price']")
	private WebElement productPrice;

	public WebElement getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(WebElement productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
