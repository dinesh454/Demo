package StepDefination;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp {
	WebDriver driver;
	String productName="ZARA COAT 3";
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));


	@Given("I landed on Ecomerce Page")
	public void I_landed_on_Ecomerce_Page()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/gandl/OneDrive/Desktop/Selenium/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	@Given ("^logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/gandl/OneDrive/Desktop/Selenium/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("gavvaladinesh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dinesh@454");
		driver.findElement(By.id("login")).click();
	}
	
	@When ("^I add the produt (.+) from the cart$")
	public void I_add_product_to_cart(String productName)
	{
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-sm-10")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".col-sm-10"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).
				findFirst().orElse(null);	
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}
	@And("^checkout(.+) and submit the order$")
	public void checkout_and_sybmit_order()
	{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		driver.findElement(By.cssSelector(".totalRow button")).click();
driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		
		List<WebElement> values=driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		
		for(WebElement value:values)
		{
			if(value.getText().equals("India"))
			{
				value.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
	}
	
	@Then("{string} Message is displayed on the confirmationPage")
	public void Message_is_displayed_on_the_confirmationPage(String string)
	{
        String text=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
