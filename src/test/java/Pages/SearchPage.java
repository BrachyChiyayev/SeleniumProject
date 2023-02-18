package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage
{
    private static WebDriver driver;
    By choosedress = By.xpath("//*[@id=\"platform_modernisation_product_summary_C64568\"]/div/div[1]/div[1]/div/div/div[1]/a/img");

    //constructor
    public SearchPage(WebDriver driver)
    {
        setDriver(driver);
    }

    public void clickOnFirstDress()
    {
        driver.findElement(choosedress).click();
    }
    //setters
    public static void setDriver(WebDriver driver) {
        SearchPage.driver = driver;
    }

    //getters
    public static WebDriver getDriver() {
        return driver;
    }
}
