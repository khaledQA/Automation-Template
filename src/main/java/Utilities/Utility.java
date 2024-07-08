package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {

    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";



    public static void clickOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public  static  void sendData(WebDriver driver, By locator, String text)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver)
    {
        return  new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public static WebElement locatorToWebElement(WebDriver driver, By locator)
    {
        return driver.findElement(locator);
    }
    public static void selectingFromDropDown(WebDriver driver, By locator, String option)
    {
        new Select(locatorToWebElement(driver,locator)).selectByVisibleText(option);
    }

    public static void scrollToWebElement(WebDriver driver, By locator)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", locatorToWebElement(driver,locator));
    }
    public static String getTimestamp()
    {
        return new SimpleDateFormat("yyyy-mm-dd-h-ssa").format(new Date());
    }
    public static void takeScreenshot(WebDriver driver , String screenshotName)
    {
        try {
            //Capture screenshot using TakeScreenshot
            File screenSource = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //Save screenshot to a file
            File screenDestination = new File(SCREENSHOTS_PATH+screenshotName+"-"+ getTimestamp()+".png");
            FileUtils.copyFile(screenSource,screenDestination);
            //Attach the screenshot to allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenDestination.getPath())));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
