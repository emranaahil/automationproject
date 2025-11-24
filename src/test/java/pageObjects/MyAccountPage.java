package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath = "//a[@class='list-group-item' and content(text(), 'Logout')]")
WebElement bntLogout;
public void bntLogout() {
	bntLogout.click();
}



}
