package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage (WebDriver driver) {
		super(driver); //questiion
	}

		@FindBy(xpath = "//input[@id='input-email']")
		WebElement txtEmail;
		 
		public void txtEmail(String text){
			txtEmail.sendKeys(text);
		}
		
		@FindBy(xpath = "//input[@id='input-password']")
		WebElement txtPassword;
		 
		public void txtPassword(String text){
			txtPassword.sendKeys(text);
		}
		
		@FindBy(xpath = "//button[@type='submit' and contains(text(), 'Login')]")
		WebElement bntLogIn;
		 
		public void bntLogIn(){
			bntLogIn.click();
		}
		
		@FindBy(xpath = "//a[@class='dropdown-item' and contains(text(), 'Logout')]")
		WebElement bntLogOut;
		 
		public void bntLogOut(){
			bntLogOut.click();
		}
		
		@FindBy(tagName ="h1")
		WebElement h1;
		public String isUserLoggedIn() {
			try {
				return (h1.getText());
			} catch (Exception e) {
				return (e.getMessage());

			}
		}
		
		@FindBy(xpath = "//h1[text()='My Account']") // MyAccount Page heading
		WebElement msgHeading;
		
		public boolean isMyAccountPageExists()   // MyAccount Page heading display status
		{
			try {
				return (msgHeading.isDisplayed());
			} catch (Exception e) {
				return (false);
			}
		}

}
		

		

