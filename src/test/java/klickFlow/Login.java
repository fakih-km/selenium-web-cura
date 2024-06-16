package klickFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {
    WebDriver driver;

    Login(){

    }

    Login(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeTest
    private void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // get to homepage
        driver.navigate().to("http://klickguard.klickersgroup.com/rint-test/login.html");
    }
    @Test (priority = 0)
    public void LoginValid(){
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("test1234");
        //xpath by text
        driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();
        //driver.findElement(By.className("login-btn-new")).click();
    }
    @Test(priority = 1)
    private void AddUser() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div[4]/div/div/div[2]/div/div/div[3]/input")).click();
        driver.switchTo().frame(0);
        Thread.sleep(2000);
        driver.findElement(By.id("fullName")).sendKeys("testing");
        driver.findElement(By.id("email")).sendKeys("testing@email.com");
        driver.findElement(By.id("password")).sendKeys("@test1234");
        driver.findElement(By.id("clientNo")).click();
        driver.findElement(By.xpath("//*[@id=\"clientNo\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"deptNo_chosen\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"deptNo_chosen\"]/div/ul/li[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"uplineNo_chosen\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"uplineNo_chosen\"]/div/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"roleNo_chosen\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"roleNo_chosen\"]/div/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"positionNo_chosen\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"positionNo_chosen\"]/div/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"timezone_chosen\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"timezone_chosen\"]/div/ul/li[272]")).click();
        driver.findElement(By.id("loginStatus")).click();
        driver.findElement(By.xpath("//*[@id=\"loginStatus\"]/option[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"changePassword\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"changePassword\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"suspended\"]")).click();
        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button")).click();

    }
    @Test
    private void test12 () throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.className("login-btn-new")).click();
        String test = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/input")).getAttribute("value");
        System.out.println(test);
    }
}
