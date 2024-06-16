package klickFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class manageMasterData {
    WebDriver driver;

    @BeforeTest
    private void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // get to homepage
        driver.navigate().to("http://klickguard.klickersgroup.com/rint-test/login.html");
        Login login = new Login(driver);
        login.LoginValid();
    }
    @Test(priority = 0)
    public void AddNewMasterData() throws InterruptedException {
        //login
        Login login = new Login(driver);
        login.LoginValid();

        //add new master data
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div[4]/div/div/div/div/div/div/div[3]/input")).click();
        driver.switchTo().frame(0);
        Thread.sleep(2000);
        driver.findElement(By.id("title")).sendKeys("test automate");
        driver.findElement(By.id("description")).sendKeys("testing automation with selenium 4");
        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"file\"]"));
        chooseFile.sendKeys("C:/Users/fakih/Downloads/test.xlsx");
        driver.findElement(By.xpath("//*[@id=\"addMasterData\"]/div[5]/div/button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button")).click();

    }
    @Test (priority = 1)
    private void ManageUserPosition(){
        String tes = "REG_JAPAN";
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[3]")).click();
        String xpath1 = String.format("//tr[.//td='%s']//td[6]/a[1]/span", tes);
        driver.findElement(By.xpath(xpath1)).click();

    }
    @Test(priority = 2)
    private void MyCustomReport(){
        String ExpectedResult = "View Custom Report -";
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[6]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[6]/ul/li[5]")).click();
        String title = "Staff Assets Tracking Form";
        driver.findElement(By.id("formName")).sendKeys(title);
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.className("la-edit")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div[3]/div/div/div[1]/h3")).getText(),"Edit Report");

    }
    @Test
    private void testing(){
        WebElement test = driver.findElement(By.xpath("//a[contains(text(),'Manage Main Process')]"));
        String test1 = test.getText();
        String script = "return arguments[0].innerText";
        test1 = (String) ((JavascriptExecutor) driver).executeScript(script, test);
        System.out.println("Text test : " + test1);
    }

}
