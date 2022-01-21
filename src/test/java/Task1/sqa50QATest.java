package Task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sqa50QATest {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.google.com/");
    }

    @Test
    void test1() {
//        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/"); //?? виснет тут и у лектора
        WebElement downloadButton = driver.findElement(By.xpath("//span[text()='Downloads']"));
        // "//button[@class='close']/preceding-sibling::h2"
//        "//button[@class='close']/child::*"
        sleepFor(4);
        downloadButton.click();
        sleepFor(3);
        assertEquals("Downloads | Selenium", driver.getTitle());
    }

    @Test
    void test2() {
        driver.get("https://yandex.ru/");
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@data-id='translate']")).click();
        sleepFor(3);

        // переход на свеже открытую вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        assertEquals("Яндекс.Переводчик – словарь и онлайн перевод на английский,"
                + " русский, немецкий, французский, украинский и другие языки.", driver.getTitle());
    }

    @Test
    void test3() {
        //1. Зайти на яндекс
        driver.navigate().to("https://yandex.ru/");
//        2. Проверить тайтл = Яндекс
        Assertions.assertEquals("Яндекс", driver.getTitle());
//        3. В поисковую строку вбиваем Люксофт
        driver.findElement(By.xpath("//input[@id='text']")).sendKeys("luxoft");
//        4. Нажать кнопку Найти
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String originalWindow = driver.getWindowHandle();
//        5. Проверить первую ссылку выдачи, что она содержит слово 'Luxoft'
        List<WebElement> links = driver.findElements(By.xpath("//h2[contains(@class,'OrganicTitle-LinkText')]"));
        String link1Text = links.get(0).findElement(By.xpath(".//span")).getText();
        Assertions.assertTrue(link1Text.contains("Luxoft"));
//        6. Кликнуть по ней
        links.get(0).findElement(By.xpath("./..")).click();
//        7. Закрыть окно Яндекс
        Set<String> handles = driver.getWindowHandles();
        driver.close();
//        8. Переключится на окно Люксофт
        for (String windowHandle : handles) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
//        9. Проверить что в секции Services we offer 6 баннеров*
        List<WebElement> servicesWeOffer = driver.findElements(By.xpath("//h2[contains(text(),'Services')]/following-sibling::div/a"));
        assertEquals(6, servicesWeOffer.size(), "Services size is not 6 but "+servicesWeOffer.size());
    }

    @AfterAll
    static void tierDown() {
        driver.quit();
    }

    void sleepFor(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Exeption in Thread.sleep");
            e.printStackTrace();
        }
    }
}
