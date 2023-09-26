package cura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class MakeAppointmentTes {
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
    private void loginWithCorrectValues() {
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
    }

    @Test(priority = 1)
    private void checkElemet() {
        //check h2 element
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Make Appointment");
        //check dropdown element
        Select dropdownFacility = new Select(driver.findElement(By.id("combo_facility")));
        List<WebElement> dropdownOptions = dropdownFacility.getOptions();
        Assert.assertEquals(dropdownOptions.get(0).getAttribute("value"), "Tokyo CURA Healthcare Center");
        Assert.assertEquals(dropdownOptions.get(1).getAttribute("value"), "Hongkong CURA Healthcare Center");
        Assert.assertEquals(dropdownOptions.get(2).getAttribute("value"), "Seoul CURA Healthcare Center");
        //check text ares
        Assert.assertEquals(driver.findElement(By.id("txt_comment")).getAttribute("placeholder"), "Comment");
    }

    @Test(priority = 2)
    private void makeAppointmentWithCorrectValues() {
        //dropdown
        Select dropdownFacility = new Select(driver.findElement(By.id("combo_facility")));
        dropdownFacility.selectByValue("Seoul CURA Healthcare Center");
        //check box
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        //radio button
        driver.findElement(By.id("radio_program_medicaid")).click();
        //date picker
        driver.findElement(By.id("txt_visit_date")).sendKeys("06/09/2023");
        //text area
        driver.findElement(By.id("txt_comment")).sendKeys("testing with selenium");
        //click button
        driver.findElement(By.id("btn-book-appointment")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/appointment.php#summary");
    }

    @Test(priority = 3, dependsOnMethods = {"makeAppointmentWithCorrectValues"})
    private void checkAppointmentSummary() {
        //check h2 element
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(), "Appointment Confirmation");
        //check p element
        Assert.assertEquals(driver.findElement(By.cssSelector("section p")).getText(), "Please be informed that your appointment has been booked as following:");
        //check facility
        Assert.assertEquals(driver.findElement(By.id("facility")).getText(), "Seoul CURA Healthcare Center");
        //check readmission
        Assert.assertEquals(driver.findElement(By.id("hospital_readmission")).getText(), "Yes");
        //check healthcare program
        Assert.assertEquals(driver.findElement(By.id("program")).getText(), "Medicaid");
        //check visit date
        Assert.assertEquals(driver.findElement(By.id("visit_date")).getText(), "06/09/2023");
        //check comment text area
        Assert.assertEquals(driver.findElement(By.id("comment")).getText(), "testing with selenium");
        //go to homepage
        driver.findElement(By.className("btn-default")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/");

    }
    @AfterTest
    private void closeBrowser(){
        driver.quit();
    }
}
