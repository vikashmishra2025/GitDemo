package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	//WebElement userEmails= driver.findElement(By.id("userEmail"));
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//convert this step- List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	/*@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement passwordEle;
	@FindBy(id="login")
	WebElement submit;*/
	
	/*public void loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}*/
	
	By productsBy = By.cssSelector(".mb-3");
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
}
