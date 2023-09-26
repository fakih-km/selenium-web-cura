package cura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest {
    WebDriver driver;

    @BeforeTest
    private void init() {
        //initial browser
        System.setProperty("web-driver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chrome.exe");
        driver = new ChromeDriver();
        //maximize browser
        driver.manage().window().maximize();
        //get to homepage
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/profile.php#login");

    }

    @Test(priority = 0)
    private void checkElement() {
        //check h2 element
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Login");
        //check p element
        Assert.assertEquals(driver.findElement(By.cssSelector("section p")).getText(), "Please login to make appointment.");
        //check textBox element
        Assert.assertEquals(driver.findElement(By.id("txt-username")).getAttribute("placeholder"), "Username");
        Assert.assertEquals(driver.findElement(By.id("txt-password")).getAttribute("placeholder"), "Password");
    }

    @Test(priority = 1)
    private void loginWithNullValues() {
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(), "Login failed! Please ensure the username and password are valid.");
    }

    @Test(priority = 2)
    private void loginWithWrongValues() {
        driver.findElement(By.id("txt-username")).sendKeys("jhon Doe");
        driver.findElement(By.id("txt-password")).sendKeys("Wrong Password");
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(), "Login failed! Please ensure the username and password are valid.");
    }

    @Test(priority = 3)
    private void loginWithCorrectValues() {
        driver.findElement(By.id("txt-username")).sendKeys("Jhon Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
    }

    @AfterTest
    private void closeBrowser() {
        driver.close();
    }
}
