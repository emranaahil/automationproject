package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginTest extends BaseClass {

	@Test(groups= {"Sanity", "Regression", "Master"})
	public void VerifyLoginTest () {
		// TODO Auto-generated method stub
		HomePage hp = new HomePage(driver);
		LoginPage Lp= new LoginPage(driver);
		try {
			
		
		logger.info("**** Starting VerifyLoginTest *****");
		hp.clickMyAccount();
		hp.clkLogin();
		logger.info("**** Login page open*****");
		
		Lp.txtEmail(p.getProperty("email"));
		Lp.txtPassword(p.getProperty("password"));
		
		Lp.bntLogIn();
		
		logger.info("**** Click on logined button *****");
		Thread.sleep(300);
		//hp.clickMyAccount();
		//Lp.bntLogOut();
		logger.info("**** Click on Logout button *****"); 
		}
		catch (Exception e) {
			logger.error("**** Test FAIL****"+e);
			logger.debug("**** FAIL VerifyRegisterationTest(Debug) ****"+e);
			Assert.fail("Test Fail check logs"+e);// TODO: handle exception
		}
	}

	
	
	
}
