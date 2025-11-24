package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath = "//span[@class='d-none d-lg-inline' and contains(text(), 'My Account')]")
	WebElement ProfileIcon;
	 
	public void clickMyAccount()
	{ ProfileIcon.click();
		}
	
	
	@FindBy(xpath = "//a[@class='dropdown-item' and contains(text(), 'Register')]")
	WebElement Registeration;
	 
	public void clickRegisteration()
	{ Registeration.click();
		}
	
	
	@FindBy(xpath="//a[@class='dropdown-item' and contains(text(), 'Login')]")
	WebElement Login;
	public void clkLogin() {
		Login.click();
		
	}
}

