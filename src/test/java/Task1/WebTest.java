package Task1;

import Task1.page.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTest {
    private static MainPage mainPage;

    @BeforeAll
    public static void startUpDriver() {
        if (Auxillary.driver == null) {
            WebDriverManager.chromedriver().setup();
            Auxillary.driver = new ChromeDriver();
            Auxillary.driver.navigate().to("https://www.google.com/");
        }
        mainPage = new MainPage(Auxillary.driver);

    }

    @Test
    public void test1(){

        mainPage.setFieldValue("input", "Lenovo 440\n");
        mainPage.clickButton("next page");
        mainPage.clickButton("third link");

        try {
            Thread.sleep((3000));
        } catch (InterruptedException e) {
            System.out.println("Exception on sleep 3000");
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown(){
        Auxillary.driver.close();
    }
}
