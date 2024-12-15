package mavenproject1;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_register {

    @Test
    public void registerTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/mavenproject1");
        driver.manage().window().maximize();

        driver.findElement(By.id("name")).sendKeys("bnnk");
        driver.findElement(By.id("phone")).sendKeys("76545336");
        driver.findElement(By.id("email")).sendKeys("nk@gmail.mm");
        driver.findElement(By.cssSelector("input[type='submit']")).click();


        AssertJUnit.assertEquals(driver.getTitle(), "REGISTRATION SUCCESS");
        
        driver.close();
    }
}

