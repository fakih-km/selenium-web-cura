package klickFlow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginGuard {
    WebDriver driver;
    @Test
    private void logi(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // get to homepage
        driver.navigate().to("http://klickguard.klickersgroup.com/rint-test/login.html");
        //driver.switchTo().frame(0);
        //driver.findElement(By.xpath("//*[@id=\"loginForm\"]/input")).click();

        //List <WebElement> input = driver.findElements(By.xpath("//input"));
        //for (WebElement inputTxt:input);
        //System.out.println(input.getAttribute("value"));
//        List<WebElement> test = driver.findElements(By.tagName("input"));
//        System.out.println("result :"+test);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("test1234");
        //xpath by text
        driver.findElement(By.className("login-btn-new")).click();
        //driver.findElement(By.className("login-btn-new")).click();

        //WebElement test = driver.findElement(By.xpath("//a[contains(text(),'Manage Main Process')]"));
//String test1 = new ArrayList<String>();
        List<WebElement> lst=driver.findElements(By.cssSelector("div ul li ul li ul li a"));
        List<String> strings = new ArrayList<String>();
        String script = "return arguments[0].innerText";
        strings = Collections.unmodifiableList((List<String>) ((JavascriptExecutor) driver).executeScript(script, lst));
        System.out.println("Text test : " + strings);

       // List<WebElement> lst=driver.findElements(By.cssSelector("div ul li ul li ul li a"));
        //List<String> strings = new ArrayList<String>();
//        for(WebElement e : lst){
//            strings.add(e.getText());
//        }
//        System.out.println("Text dropdown : " + strings);

    }
}
