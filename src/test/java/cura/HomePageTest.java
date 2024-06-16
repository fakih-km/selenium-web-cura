package cura;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest {
	WebDriver driver;

	@BeforeTest
	private void init() {
		// initial browser
		// System.setProperty("web-driver.chrome.driver", "C:/Program
		// Files/Google/Chrome/Application/chrome.exe");
		driver = new ChromeDriver();
		// maximize browser
		driver.manage().window().maximize();
		// get to homepage
		driver.navigate().to("https://katalon-demo-cura.herokuapp.com/");

	}

	@Test(priority = 0)
	private void checkElement() {
		// check h1 element
		assertEquals(driver.findElement(By.cssSelector("header h1")).getText(), "CURA Healthcare Service");
		assertEquals(Color.fromString(driver.findElement(By.cssSelector("header h1")).getCssValue("color")).asHex(),
				"#ffffff");
		// check h3 element
		assertEquals(driver.findElement(By.cssSelector("header h3")).getText(), "We Care About Your Health");
		assertEquals(Color.fromString(driver.findElement(By.cssSelector("header h3")).getCssValue("color")).asHex(),
				"#4fb6e7");
		// check button
		assertEquals(driver.findElement(By.id("btn-make-appointment")).getText(), "Make Appointment");
		assertEquals(driver.findElement(By.id("btn-make-appointment")).getCssValue("background-color"),
				"rgba(115, 112, 181, 0.8)");
		// check toogle menu
		assertEquals(driver.findElement(By.id("menu-toggle")).getCssValue("background-color"),
				"rgba(115, 112, 181, 0.8)");

	}

	@Test(priority = 1)
	private void checkElementToogleMenu() {
		driver.findElement(By.id("menu-toggle")).click();
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[1]/a")).getText(),
				"CURA Healthcare");
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[2]/a")).getText(), "Home");
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a")).getText(), "Login");
	}

	@Test(priority = 2)
	private void checkLoginValid() {
		driver.findElement(By.id("btn-make-appointment")).click();
		assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/profile.php#login");

	}

	@AfterTest
	private void closeBrowser() {
		driver.close();
	}
}
