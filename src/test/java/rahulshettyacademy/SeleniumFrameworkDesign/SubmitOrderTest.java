package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) {
		String ProductName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);
		
		landingPage.goTo();
		driver.manage().window().fullscreen();
		landingPage.loginApplication("vikashmishraemail@gmail.com", "Vikash@123");
		/*driver.findElement(By.id("userEmail")).sendKeys("vikashmishraemail@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vikash@123");
		driver.findElement(By.id("login")).click();*/
		ProductCatalogue productCatologue = new ProductCatalogue(driver);
		List<WebElement> products= productCatologue.getProductList();
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));*/
	WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	//System.out.println(prod.getText());
	//prod.findElement(By.cssSelector(".w-10")).click();
	//new Way to Generate "cssSelector"
	//4 ways learned here to generate cssSelector
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean Match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
	Assert.assertTrue(Match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "INDIA").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	driver.findElement(By.xpath("//a[contains(@class,'ng-star-inserted')]")).click();
	String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	} 
}