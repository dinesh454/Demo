package dinesh.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import dinesh.TestComponents.BaseTest;
import dinesh.TestComponents.Retry;

public class ErrorValidation  extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
				
		landingPage.loginApplication("gavvaladinesh@ssgmail.com", "Dinesh@454");	
		Assert.assertEquals("Incorrect 123emassil or password.", landingPage.getErrorMessage());
		//"Incorrect email or password.",landingPage.getErrorMessage()

	}

}
