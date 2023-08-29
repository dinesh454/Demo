package dinesh.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dinesh.TestComponents.BaseTest;
import dinesh.pageobjects.CartPage;
import dinesh.pageobjects.CheckOutPage;
import dinesh.pageobjects.ConfirmationPage;
import dinesh.pageobjects.LandingPage;
import dinesh.pageobjects.OrderPage;
import dinesh.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2  extends BaseTest{

	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		
				
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));	
		List<WebElement> products=productCatalogue.getProductList();
		Thread.sleep(5000);
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCart();		
		Boolean match = cartPage.VerifyProductDisplay(productName);
				
		
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage=cartPage.goToCheckout();
		
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		
		
			
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("gavvaladinesh@gmail.com", "Dinesh@454");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		
		HashMap<String,String> mp=new HashMap<String,String>();
		mp.put("email", "gavvaladinesh@gmail.com");
		mp.put("password", "Dinesh@454");
		mp.put("input.get", "ZARA COAT 3");
		
		HashMap<String,String> mp1=new HashMap<String,String>();
		mp1.put("email", "gavvaladinesh@gmail.com");
		mp1.put("password", "Dinesh@454");
		mp1.put("input.get", "ADIDAS ORIGINAL");
		
		
		return new Object[][] {{mp},{mp1}};
	}

}
