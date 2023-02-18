package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage
{
    private static WebDriver driver;

    //Locators of product page
    By color = By.id("dk_container_Colour-929220");
    By chooseColor=By.linkText("Charcoal Grey");
    By size= By.id("dk_container_Size-A79-896");
    By chooseSize=By.xpath("//*[@id=\"dk_container_Size-A79-896\"]/div/ul/li[4]/a");
    By addToBagBtn= By.xpath("//*[@id=\"Style929220\"]/section/div[4]/div[4]/div[4]/div/a[1]");
    By iconBag=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[6]/div[2]/div[2]/div/div[2]/div/div[3]/div[1]/a");
    By quantity=By.xpath("//*[@id=\"dk_container_Qty_1\"]");
    By chooseQuantity=By.xpath("//*[@id=\"dk_container_Qty_1\"]/div/ul/li[2]/a");
    By checkoutBtn=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div/a");


    //constructor
    public ProductPage(WebDriver driver)
    {
        setDriver(driver);
    }

    //Method for select color
    public void selectColor()
    {
        driver.findElement(color).click();
        driver.findElement(chooseColor).click();
    }

    //Method for select size
    public void selectSize()
    {
        driver.findElement(size).click();
        driver.findElement(chooseSize).click();
    }

    //Method for add to bag
    public void addToBag()
    {
        driver.findElement(addToBagBtn).click();
    }

    //Method for click on bag
    public void clickOnBag()
    {
        driver.findElement(iconBag).click();
    }

    //Method for add quantity
    public void addQuantity()
    {
        driver.findElement(quantity).click();
        driver.findElement(chooseQuantity).click();
    }

    //Method for click on CheckOut button
    public void clickCheckOutBtn()
    {
        driver.findElement(checkoutBtn).click();
    }

    //Method to check Quantity of product in bag
    public void checkQuantity() throws InterruptedException
    {
        /*
        Select selectByValue=new Select(driver.findElement(chooseQuantity));
        selectByValue.selectByValue("2");
        Thread.sleep(Long.parseLong("5000"));
        */

        String str= driver.findElement(chooseQuantity).getText();
        System.out.println(str);
        String equal="2";
        if(str.equals(equal))
            System.out.println("Sucsses");
        else
            System.out.println("fail");
    }



    //setters
    public static void setDriver(WebDriver driver) {
        ProductPage.driver = driver;
    }

    //getters
    public static WebDriver getDriver() {
        return driver;
    }
}
