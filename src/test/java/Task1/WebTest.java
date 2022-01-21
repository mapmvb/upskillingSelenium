package Task1;

import Task1.page.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class WebTest {
    private static MainPage mainPage;

    @BeforeAll
    public static void startUpDriver() {
        if (Auxillary.driver == null) {
            WebDriverManager.chromedriver().setup();
            Auxillary.driver = new ChromeDriver();
            Auxillary.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//            Auxillary.driver.navigate().to("https://www.google.com/");
//            Auxillary.driver.navigate().to("https://yandex.ru/");
        }
        mainPage = new MainPage(Auxillary.driver);

    }

    @Test
    public void testYandex(){
        Auxillary.driver.navigate().to("https://yandex.ru/");

        Assertions.assertEquals("Яндекс", mainPage.driver.getTitle());
//        mainPage.driver.findElement(By.xpath("//input[@id='text']")).sendKeys("luxoft"+ Keys.ENTER);
        mainPage.driver.findElement(By.xpath("//input[@id='text']")).sendKeys("luxoft");
        mainPage.driver.findElement(By.xpath("//button[@type='submit']")).click();
//        Auxillary.driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MICROSECONDS);

//        WebElement link = mainPage.driver.findElement(By.xpath("//h2"));
        List<WebElement> links = mainPage.driver.
//                findElements(By.xpath("//h2[contains(@class,'OrganicTitle-LinkText')]"));
                findElements(By.xpath("//h2[contains(@class,'Organic')]"));
        WebElement link1 = links.get(0);
        WebElement link2 = link1.findElement(By.xpath(".//span"));
        String link1Text = link2.getText();
//        String link1Text =links.get(0).findElement(By.xpath(".//span")).getText();
        Assertions.assertTrue( link1Text.contains("Luxoft"));

//        assertThat(.getText(), containsString("Luxoft"));

    }

    @Test
    public void test1(){

        mainPage.setFieldValue("input", "Lenovo 440\n");
        mainPage.clickButton("next page");
        mainPage.clickButton("third link");

        sleepFor(3);
    }

    void sleepFor(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            System.out.println("Exeption in Thread.sleep");
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Auxillary.driver.navigate().to("https://www.google.com/");
        WebElement test = Auxillary.driver.findElement(By.xpath("//input[@name='q']"));
        sleepFor(3);
    }

    @AfterAll
    public static void tearDown(){
        Auxillary.driver.close();
    }
}
