package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilites.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginDDT extends BaseClass {
	SoftAssert softassert = new SoftAssert();

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void VerifyLoginDDT(String username, String password, String res) { //
		logger.info("Test Start");
		try {

			logger.info("Home Page Open");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clkLogin();
			logger.info("Login Page Open");
			LoginPage lp = new LoginPage(driver);
			lp.txtEmail(username);
			lp.txtPassword(password);

			lp.bntLogIn();
			logger.info("Clicked On Login button");
			String confrimLogin = lp.isUserLoggedIn();

			Boolean tragetpage = lp.isMyAccountPageExists();

			if (res.equalsIgnoreCase("Valid")) {

				if (tragetpage==true) {

					logger.info("Login sucessfull");
					// softassert.assertNotEquals(confrimLogin ,"My AccAAAAAAAount", "Login
					// sucessfully With valid data");
					Assert.assertEquals(confrimLogin, "My Account", "Login sucessfully With valid data");
					hp.clickMyAccount();
					lp.bntLogOut();
					logger.info("Clicked On Logout button");
				}

				else {
					logger.info("Fail");
					Assert.assertTrue(false);
				}
			}

			if (res.equalsIgnoreCase("Invalid")) {

				if (tragetpage==true) {
					System.out.print(tragetpage);
					logger.info("Login sucessfull with invalid data");
				// Assert.assertEquals(confrimLogin, "My Account", "Login sucessfully With valid
				// data");
				hp.clickMyAccount();
				lp.bntLogOut();
				logger.info("Clicked On Logout button");
				Assert.assertTrue(false);
			}

			else {
				logger.info("Pass");
				Assert.assertTrue(true);
			}
		}
			
		}
		 catch (Exception e) {
			// TODO: handle exception
			Assert.fail(e + "Check log or testng result");
		}

		logger.info("Test Completed");
	}
}
