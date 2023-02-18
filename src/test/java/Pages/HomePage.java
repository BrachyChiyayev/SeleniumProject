package Pages;

import TestCases.SanityTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.Key;

public class HomePage
{
    private static WebDriver driver;

    //Locators of home page
    By iconBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[3]/div[2]/div/a/img");
    By linkHome= By.xpath("//*[@id=\"meganav-link-6\"]/div");
    By linkLeftSide= By.linkText("Garden");
    By linkCenterCatagory= By.xpath("//*[@id=\"buttonlist1\"]/div/div[2]/div/div/div[2]/a");
    By linkInBunner= By.xpath("//*[@id=\"meganav-link-3\"]/div");
    By iconLanguage=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[9]/button");
    By hebruBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[4]/div/button[1]");
    By shopnowBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");

    By searchField= By.id("header-big-screen-search-box");

    //instance of constants class
    private static Constants parameters= new Constants();

    //Constructor
    public HomePage(WebDriver driver)
    {
        setDriver(driver);
    }

    //Method to click on icon
    public  void clickIcon()
    {
        driver.findElement(iconBtn).click();
    }

    //Method to click on homeLink
    public void clickHomeLink()
    {
        driver.findElement(linkHome).click();
    }

    //Method to click on linkCenterCatagory
    public void clickLinkCenterCatagory()
    {
        driver.findElement(linkCenterCatagory).click();
    }

    //Method to click on linkLeftSide
    public void clickLinkLeftSide()
    {
        driver.findElement(linkLeftSide).click();
    }

    //Method to click on linkInBunner
    public void clickLinkInBunner()
    {
        driver.findElement(linkInBunner).click();
    }

    //Method to change language of next website
    public void changeLanguage() throws InterruptedException
    {
        driver.findElement(iconLanguage).click();
        Thread.sleep(3000);
        driver.findElement(hebruBtn).click();
        Thread.sleep(3000);
        driver.findElement(shopnowBtn).click();
    }

    //Method to search products
    public  void search()
    {
        WebElement search1= driver.findElement(searchField);
        search1.sendKeys(parameters.SEARCHVALUE);
        search1.sendKeys(Keys.ENTER);
    }

    //Method to return to last page
    public void returnToHomePage()
    {
        driver.navigate().back();
    }
    //setters
    public static void setDriver(WebDriver driver) {
        HomePage.driver = driver;
    }

    //getters
    public static WebDriver getDriver() {
        return driver;
    }
}
