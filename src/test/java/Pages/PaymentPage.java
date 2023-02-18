package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage
{
    private static WebDriver driver;

    //Locators of payment page
    By creditCard = By.id("card_option");
    By cardNumber= By.id("cardNumber");
    By expiryMounth= By.id("expiryMonth");
    By expiryYear= By.id("expiryYear");
    By securityCode= By.id("securityCode");
    By payNowBtn=By.id("submitButton");
    By eroreMessage= By.id("cardNumber-hint");

    //instance of constants class
    private static Constants parameters= new Constants();

    //Method to payment by creditCard
    public boolean payment()
    {
        driver.findElement(creditCard).click();
        driver.findElement(cardNumber).sendKeys(parameters.CARDNUMBER);
        driver.findElement(expiryMounth).sendKeys(parameters.EXPIRYMOUNTH);
        driver.findElement(securityCode).sendKeys(parameters.SECURITYCODE);
        driver.findElement(payNowBtn).click();
        //return true if errorMessage is disabled
        return driver.findElement(eroreMessage).isDisplayed();
    }


    //constructor
    public PaymentPage(WebDriver driver)
    {
        setDriver(driver);
    }

    //setters
    public static void setDriver(WebDriver driver) {
        PaymentPage.driver = driver;
    }

    //getters
    public static WebDriver getDriver() {
        return driver;
    }
}
