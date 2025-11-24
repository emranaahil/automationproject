package testCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // log4j
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({ "OS", "Browser" })
	public void Setup(String OS, String Browser) {

		File src = new File("src/test/resources/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			p = new Properties();
			p.load(fis);
		} catch (Exception e) {
			System.out.println("File not found"); // TODO: handle exception
		}
		DesiredCapabilities capabilites = new DesiredCapabilities();
		if (p.getProperty("excution_env").equalsIgnoreCase("remote")) {

			if (OS.equalsIgnoreCase("Windows10")) {
				capabilites.setPlatform(Platform.WIN10);

			}

			else if (OS.equalsIgnoreCase("MAC")) {
				capabilites.setPlatform(Platform.MAC);

			} else {
				System.out.println(OS+" OS Not Found or Invalid OS");
				return;

			}
			
			switch (Browser.toLowerCase()) {
			case "chrome":
				capabilites.setBrowserName("chrome");
				break;
			case "firefox":
				capabilites.setBrowserName("firefox");
				break;
			case "Microsoftedge":
				capabilites.setBrowserName("Microsoftedge");
				break;
				
			case "safari":
				capabilites.setBrowserName("Safari");
				break;

			default:
				System.out.println("Invalid Browser Name");
				return; // stop excustion
			}
			
			try {
				driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilites);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		if (p.getProperty("excution_env").equalsIgnoreCase("local")) {

			switch (Browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("Invalid Browser Name");
				return; // stop excustion
			}
		}
		logger = LogManager.getLogger(this.getClass());// logmanger
		logger.info("Logger Initialized Successfully!");
		// driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// driver.get("http://localhost/opencart/index.php");
		driver.get(p.getProperty("baseUrl1"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
	}

	public String randomString() {

		String randomstring = RandomStringUtils.randomAlphabetic(5);
		return randomstring;
	}

	public String randomNum() {

		String randomNum = RandomStringUtils.randomNumeric(10);
		return randomNum;
	}

	public String randomAlphaNumbric() {

		String randomalp = RandomStringUtils.randomAlphabetic(3);
		String randomnnumber = RandomStringUtils.randomNumeric(3);
		return randomalp + randomnnumber;
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void tearDown() {
		driver.close();
	}

	public String captureScreen(String tname) throws IOException {

		// Ensure the screenshots directory exists

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// Construct the target file path
		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// Rename and save the file
		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
