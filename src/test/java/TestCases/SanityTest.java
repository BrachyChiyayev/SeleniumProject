package TestCases;

import Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SanityTest
{
    private static WebDriver driver;

    //creating object for home page
    private static HomePage home;

    //creating object for login page
    private static LoginPage login;

    //creating object for payment page
    private static PaymentPage payment;

    //creating object for product page
    private static ProductPage product;

    //creating object for search page
    private static SearchPage search;

    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent ;

    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test ;

    private static Constants parameters= new Constants();

    @BeforeClass
    public static void BeforeClass() throws ParserConfigurationException, IOException, SAXException
    {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(parameters.REPORTLOCATION);

        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("SanityTest", "Sanity test for Next");

        // add custom system info
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Tester", "Brachy");

        // log results
        test.log(Status.INFO, "@Before class");

       if(getData("BROWSER").equals("chrome"))
        {
            //chrome
            System.setProperty("webdriver.chrome.driver", parameters.CHROMRDRIVERPATH);
            driver = new ChromeDriver();
            driver.get(getData("URL"));
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        //create instance for all pages
        home = new HomePage(driver);
        login= new LoginPage(driver);
        payment= new PaymentPage(driver);
        product=new ProductPage(driver);
        search=new SearchPage(driver);
    }


    @Test
    public void loginTest() throws ParserConfigurationException, IOException, SAXException, InterruptedException
    {
        //click on icon button
        home.clickIcon();
        //login to next website
        login.login(getData("MAILADRESS"), getData("PASSWORD"));
        waiting();
        String expectedTitle = parameters.TITLEHOMEPAGE;
        String actualTitle = driver.getTitle();
        if (currentPage(expectedTitle, actualTitle))
        {
            test.log(Status.INFO, "login test passed ");
            System.out.println("sucsses");
        }
        else
        {
            test.log(Status.INFO, "login test failed ");
            //send screenshot to report
            String currentTime = String.valueOf(System.currentTimeMillis());
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(parameters.IMAGESPATH + currentTime)).build());
            System.out.println("failed");
        }
    }

    @Test
    public void homeTest() throws InterruptedException, IOException
    {
        driver.get("https://www.next.co.il/en/homeware");
        //home.clickHomeLink(); Not performed due to a problem connecting to the login page in the automation
        home.clickLinkLeftSide();
        String expectedTitle=parameters.TITLEHOMEOFFICE;
        waiting();
        String actualTitle=driver.getTitle();

        System.out.println(currentPage(expectedTitle,actualTitle));
        if(currentPage(expectedTitle,actualTitle))
        {
            //return to home page
            home.returnToHomePage();
            home.clickLinkCenterCatagory();
            //return to home page
            home.returnToHomePage();
            home.clickLinkInBunner();
            waiting();
            //return to home page
            home.returnToHomePage();
            home.changeLanguage();
            waiting();
            home.search();
            //return to home page
            home.returnToHomePage();
            //write to report log
            test.log(Status.INFO, "home test passed");
            System.out.println("Home test succsess");
        }
        else
        {
            //write to report log
            test.log(Status.INFO, "home test failed");
            //send screenshot to report
            String currentTime = String.valueOf(System.currentTimeMillis());
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(parameters.IMAGESPATH + currentTime)).build());
            System.out.println("Home test failed");
        }
    }


    @Test
    public void searchTest()
    {
        search.clickOnFirstDress();
    }

    @Test
    public void productTest() throws InterruptedException
    {
        //go to the specific product
        driver.navigate().to(parameters.PRODACTPATH);
        product.selectColor();
        product.selectSize();
        product.addToBag();
        product.clickOnBag();
        product.addQuantity();
        //לבדוק אם עובד
        product.clickCheckOutBtn();
        //product.checkQuantity();
        waiting();
    }

    @Ignore
    @Test
    public void paymentTest() throws InterruptedException, IOException
    {
        //go to payment page
        driver.navigate().to(parameters.PAYMENTPATH);
        if(payment.payment())
        {
            System.out.println("payment test passed");
            test.log(Status.INFO, "payment test passed ");
        }
        else
        {
            test.log(Status.INFO, "payment test failed ");
            //send screenshot to report
            String currentTime = String.valueOf(System.currentTimeMillis());
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(parameters.IMAGESPATH + currentTime)).build());
            System.out.println("payment test failed");
        }
       // waiting();
    }


    @AfterClass
    public static void tearDown()
    {
        test.log(Status.INFO, "@After class");
        //close browser instance
        driver.quit();
        // build and flush report
        extent.flush();
    }

    public boolean currentPage(String expectedResult,String actualResult)
    {
        if(expectedResult.equals(actualResult))
            return  true;
        else
            return false;
    }

    private void waiting() throws InterruptedException
    {
        Thread.sleep(3000);
        System.out.println("wait");
    }

    //method for read from files
    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException
    {
        File configXmlFile = new File(parameters.XMLFILELOCATION);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    //method for get ScreenShot
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
