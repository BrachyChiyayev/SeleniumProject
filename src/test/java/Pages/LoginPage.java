package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage
{
    private static WebDriver driver;

    //Locators of home page
    private  By userNameLocator= By.id("EmailOrAccountNumber");
    private  By passwordLocator= By.id("Password");
    //private By signInBtnLocator= By.id("SignInNow");
    private By signInBtnLocator= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[4]/div[2]/div/a");


    //instance of constants class
    private static Constants parameters= new Constants();

    //constructor
    public LoginPage(WebDriver driver)
    {
        setDriver(driver);
    }

    //Method to login website
    public void login(String userName,String password)
    {
        driver.findElement(userNameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(signInBtnLocator).click();
        driver.navigate().to(parameters.HOMEPAGEURL);
    }

    //setters
    public static void setDriver(WebDriver driver) {
        LoginPage.driver = driver;
    }

    //getters
    public static WebDriver getDriver() {
        return driver;
    }
}
