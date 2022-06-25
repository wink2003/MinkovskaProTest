import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.BaseTestUtils;

import java.util.ArrayList;
import java.util.List;

public class BackEndTest extends BaseTestUtils {
    private final static String BACK_LOGIN_URL = "https://minkovska.pro/wp-admin/";
    private final static String BACK_PAGES_URL = "https://minkovska.pro/wp-admin/edit.php?post_type=page";


    @Test
    public void backendLoginTest() throws InterruptedException {
        getDriver().get(BACK_LOGIN_URL);
        loginBackEnd();
        boolean avatarimg = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@class='avatar avatar-26 photo']"))).isDisplayed();
        Assert.assertTrue(avatarimg);
    }

    @Test
    public void pagesTranslateUkTest() {
        getDriver().get(BACK_LOGIN_URL);
        loginBackEnd();
        getDriver().findElement(By.xpath("//li[@id='menu-pages']//div[3]")).click();

        List<WebElement> ukicon = new ArrayList<>();
        ukicon = getDriver().findElements(By.xpath("//td[@class='language_uk column-language_uk']"));
        for (WebElement webElement : ukicon) {
            Assert.assertFalse(webElement.getText().contains("Добавить"));
        }
    }
    @Ignore
    @Test
    public void pagesTranslateRuTRTest() {
        getDriver().get(BACK_LOGIN_URL);
        loginBackEnd();
        getDriver().findElement(By.xpath("//li[@id='menu-pages']//div[3]")).click();

        List<WebElement> ruicon = new ArrayList<>();
        ruicon = getDriver().findElements(By.xpath("//td[@class='language_ru column-language_ru']"));
        for (WebElement webElement : ruicon) {
            Assert.assertFalse(webElement.getText().contains("Добавить"));
        }
    }

}
