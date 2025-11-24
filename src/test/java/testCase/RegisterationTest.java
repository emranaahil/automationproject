package testCase;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class RegisterationTest extends BaseClass {
	

	
	SoftAssert asert = new SoftAssert();
	 
	
	
	@Test(groups= {"Sanity", "Regression", "Master"})
	
	public void VerifyRegisterationTest() throws InterruptedException {
		try {
		logger.info("***** Start VerifyRegisterationTest ****");
		AccountRegistrationPage accpg =new AccountRegistrationPage(driver);
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		hp.clickRegisteration();
		logger.info("***** Open Registeration Account Page  ****");
		//assertEquals(accpg.getH1tag(), "Registration Account");;
		//asert.assertEquals(accpg.getH1tag(), "Registeration Account");
		accpg.txtFirstName(randomString().toLowerCase());
		accpg.txtLastName(randomString().toLowerCase());
		accpg.EmailId(randomString()+"@yopmail.com");
		accpg.txtPassword(randomNum());
		
		//js.executeScript("window.scrollBy(0,250)", "");
		js.executeScript("arguments[0].scrollIntoView(true);", accpg.SaveButton());
		Thread.sleep(3000);
		accpg.RdBntAgreeTermsButton();
		accpg.BntSaveButton();
		logger.info("***** Click on sumbit button ****");
		
		logger.info("***** Open Sucessfull Account Page  ****");
		}catch (Exception e) {
			
			logger.error("**** Test FAIL****"+e);
			logger.debug("**** FAIL VerifyRegisterationTest(Debug) ****"+e);
			Assert.fail("Test FAIL"+e);
			// TODO: handle exception
		}
		//Your Account Has Been Created!
	}
	
	

}
