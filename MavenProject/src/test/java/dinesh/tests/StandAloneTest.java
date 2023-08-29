package dinesh.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dinesh.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//LandingPage landingPage=new LandingPage();
		driver.findElement(By.id("userEmail")).sendKeys("gavvaladinesh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Dinesh@454");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-sm-10")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".col-sm-10"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).
				findFirst().orElse(null);		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartList=driver.findElements(By.cssSelector("[class='cartSection'] h3"));
		
		Boolean match= cartList.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
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
		
		String text=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}

}
