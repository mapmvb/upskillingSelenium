package Task1.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public abstract class CommonPage {
    public abstract WebElement getElement(String name);

    public void setFieldValue(String field, String value){
        getElement(field).sendKeys(value);
    }

    public void clickButton(String name) {
        getElement(name).click();
    }

    public void checkElementDisplayed(String name, boolean condition){
        try{
            assertThat(getElement(name).isDisplayed(), is(condition));
        } catch (NullPointerException npe) {
            if(condition) {
                assertThat(condition,is(false));
            }
            else Assertions.fail("element "+ name + " not found at the page!");
        }
    }
}
