import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTestUtils;
import java.util.concurrent.TimeUnit;

public class _FirstBaseTest extends BaseTestUtils {

    @Test
    public void MyFirstTest() {

        getDriver().get("https://google.com");

        getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys("Selenium");

        WebElement searchButton = getDriver().findElement(By.name("btnK"));
        searchButton.click();

        WebElement pictureLink = getDriver().findElement(By.linkText("Картинки"));
        pictureLink.click();

        getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        searchBox = getDriver().findElement(By.name("q"));
        Assert.assertEquals(searchBox.getAttribute("value"), "Selenium");
    }
}