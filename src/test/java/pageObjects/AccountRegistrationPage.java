package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	public void txtFirstName(String Text) {
		txtFirstName.sendKeys(Text);
	}
 @FindBy(xpath="//input[@id='input-lastname']")
 WebElement txtLastName;
 public void txtLastName(String Text) {
	 txtLastName.sendKeys(Text);
 }
 
 @FindBy(xpath="//input[@id='input-email']")
 WebElement txtEmailId;
 
 public void EmailId(String Text) {
	 txtEmailId.sendKeys(Text);
 }
 
 @FindBy(xpath="//input[@id='input-password']")
 WebElement Password;
 public void txtPassword(String Text) {
	 Password.sendKeys(Text);
 }
 
 @FindBy(xpath="//input[@name='agree']")
 WebElement RdBntAgreeTermsButton;
 public void RdBntAgreeTermsButton() {
	 RdBntAgreeTermsButton.click();
	
}
 
 @FindBy(xpath="//button[contains(text(),'Continue')]")
 WebElement BntSaveButton;
 public void BntSaveButton() {
	 BntSaveButton.click();
 }
 
 @FindBy(xpath="//button[contains(text(),'Continue')]")
 WebElement SaveButton;
 public WebElement SaveButton() {
	return SaveButton;
 }
 
 @FindBy(tagName ="h1")
WebElement H1tag;
public  String getH1tag() {
	String H1=H1tag.getText();
	return H1;
}

public boolean isMyAccountPageExists()   // MyAccount Page heading display status
{
	try {
		return (H1tag.isDisplayed());
	} catch (Exception e) {
		return (false);
	}

}
}

