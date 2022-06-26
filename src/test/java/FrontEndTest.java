import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrontEndTest extends BaseTestUtils {

    private final static String FRONT_MAIN_URL = "https://minkovska.pro/";


    @Test
    public void sendFormAllTest() {
        getDriver().get(FRONT_MAIN_URL);

        getDriver().findElement(By.name("names[first_name]")).sendKeys("Testname");
        getDriver().findElement(By.name("email")).sendKeys("test@test.net");
        getDriver().findElement(By.name("input_mask")).sendKeys("0440000000");
        getDriver().findElement(By.name("terms-n-condition")).click();
        getDriver().findElement(By.name("custom_submit_button-6_1")).click();

        WebElement thankyoutext = getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='fluentform_6_success']")));
        Assert.assertEquals(thankyoutext.getText(), "Дякую, я з вами зв'яжусь");
    }
    @Test
    public void sendFormEmptyNameTest() {
        getDriver().get(FRONT_MAIN_URL);

        getDriver().findElement(By.name("names[first_name]")).sendKeys("");
        getDriver().findElement(By.name("email")).sendKeys("test@test.net");
        getDriver().findElement(By.name("input_mask")).sendKeys("0440000000");
        getDriver().findElement(By.name("terms-n-condition")).click();
        getDriver().findElement(By.name("custom_submit_button-6_1")).click();

        WebElement erroremsg = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("text-danger")));
        Assert.assertEquals(erroremsg.getText(), "Обов'язково");
    }
    @Test
    public void sendFormEmptyEmailTest() {
        getDriver().get(FRONT_MAIN_URL);

        getDriver().findElement(By.name("names[first_name]")).sendKeys("Testname");
        getDriver().findElement(By.name("email")).sendKeys("");
        getDriver().findElement(By.name("input_mask")).sendKeys("0440000000");
        getDriver().findElement(By.name("terms-n-condition")).click();
        getDriver().findElement(By.name("custom_submit_button-6_1")).click();

        WebElement erroremsg = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.className("text-danger")));
        Assert.assertEquals(erroremsg.getText(), "Обов'язково");
    }
    @Test
    public void showMenuOnMinResolutionTest() {
        getDriver().get(FRONT_MAIN_URL);

        getDriver().manage().window().setSize(new Dimension(1200, 907));

        WebElement sidebarmenu = getDriver().findElement(By.xpath("//*[@id=\"askella-menu\"]"));
        Assert.assertTrue(sidebarmenu.isDisplayed());

    }

    @Test
    public void defaultLangIsUATest() {
        getDriver().get(FRONT_MAIN_URL);

        WebElement languageua = getDriver().findElement(By.
                xpath("//a[@class='pll-parent-menu-item menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children has-arrow webkitNoSelect']"));
        Assert.assertTrue(languageua.isEnabled());
    }

    @Test
    public void siteInfoYearTest() {
        getDriver().get(FRONT_MAIN_URL);

        WebElement siteyear = getDriver().findElement(
                By.xpath("//div[@class='site-info']"));
        Assert.assertTrue(siteyear.getText().contains(Integer.toString(LocalDate.now().getYear())));
    }
}
