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
import java.util.concurrent.TimeUnit;

public class SingleFileTest {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
//        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test
    void googleTest() {
//        go to google.com
//        enter request"lenovo 440 + details" + click button search
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Lenovo T440\n");
//        on the second page
//        driver.findElement(By.id("pnnext")).click();
        driver.findElement(By.xpath("//*[@id=\"xjs\"]//td["+5+"]/a")).click();
        sleepFor(4);
//        find 3-d site перейти
        driver.findElement(By.id("rso")).findElements(By.xpath("//*[@id=\"rso\"]//a[h3]")).get(2).click();
//        and find title(meta tag) of site + find H1 tag and save it with url
        String url3 = driver.getCurrentUrl();
        String tille3 = driver.getTitle();
        String tagH1 = driver.findElement(By.xpath("//h1[not(@hidden)]")).getText();
        System.out.println("tag H1 :" + tagH1);
        System.out.println("URL:" + url3);
        System.out.println("Title:" + tille3); //!
//        click back in browser
        driver.navigate().back();
//        in current input field of google input title //input[@name='q'and not(@type='hidden')]
        Assertions.assertTrue(findInOutput(tille3,url3));
//        in current input field of google input H1 and найти позицию в выдаче
        Assertions.assertTrue(findInOutput(tagH1, url3));
        sleepFor(1);
    }

    @AfterAll
    static void tierDown() {
        driver.quit();
    }

    private boolean findInOutput(String findArg, String url){
        driver.findElement(By.xpath("//input[@name='q'and not(@type='hidden')]")).clear();
        driver.findElement(By.xpath("//input[@name='q'and not(@type='hidden')]")).sendKeys(findArg + "\n");
//        and найти позицию в выдаче
//        List<WebElement> listLinks = driver.findElement(By.id("rso")).findElements(By.xpath("//*[@id=\"rso\"]//a[h3]//h3"));
//        sleepFor(5);
        int page = 1;
        do {
            List<WebElement> listLinks = driver.findElement(By.id("rso")).findElements(By.xpath("//*[@id=\"rso\"]//a[h3]"));
            int i = 0;
            for (WebElement link : listLinks) {
//                System.out.println("I=" + i + " " + link.getAttribute("href"));
                if(url.equalsIgnoreCase(link.getAttribute("href"))){
                    System.out.println("Match found at the place: "+(i+1)+" at the page:"+page);
                    return true;
                }
                i++;
            }
            System.out.println("Match not found at the "+page+" page!");
            driver.findElement(By.id("pnnext")).click();
            page++;
//            sleepFor(5);
        } while (page<10);
        return false;
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
