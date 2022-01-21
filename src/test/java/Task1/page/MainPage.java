package Task1.page;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MainPage extends CommonPage {

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(String name){
        WebElement obj = null;
//        List<WebElement> links;
        try {
            switch (name){
                case "next page":
                    obj = driver.findElement(By.id("pnnext")); break;
                case "third link":
                    obj = driver.findElement(By.id("rso")).findElement(By.xpath("./div[3]"));
                    break;
                case "password":
                    obj = driver.findElement(By.name("password")); break;
                case "input":
                    obj = driver.findElement(By.xpath("//input[@name='q']")); break;
                case "input_yandex":
                    obj = driver.findElement(By.xpath("//input[@name='q']")); break;
                case "logout":
                    obj = driver.findElement(By.cssSelector(".icon-signout")); break;
                default:
                    System.out.println("Error! getElement can't find field:"+name);
            }
        } catch (NoSuchElementException e) {
            Assertions.fail("element "+ name + " not found at the page");
            return null;
        }
        return obj;
    }
}

