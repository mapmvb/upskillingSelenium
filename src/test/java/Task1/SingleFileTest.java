package Task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SingleFileTest {
    private static WebDriver driver;

    @BeforeAll
    static void setUp(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test
    void googleTest(){
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Lenovo 440\n");
        sleepFor(4);
    }

    @AfterAll
    static void tierDown(){
        driver.quit();
    }

    void sleepFor(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            System.out.println("Exeption in Thread.sleep");
            e.printStackTrace();
        }
    }
}
