package ua.qa.edusson.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Random;


public class Helper {

    protected WebDriver driver;
    protected final Wait<WebDriver> wait;


    // public File revision;

    public Helper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,120, 1000).withMessage("Element was not found during 120 Sec");
    }


    public File getRevision() {
        return revision;
    }

    public void setRevision(File revision) {
        this.revision = revision;
    }

    File revision = new File("src/test/resources/testFile.pdf");

    public void goTo(String url){
        driver.get(url);
    }
    public void goToEdusson() {
        driver.get("http://edusson.com/");
    }

    public void goToEdubirdie() {
        driver.get("http://edubirdie.com");
    }

    public void goToStudyfaq() {
        driver.get("http://studyfaq.com/");
    }




    public WebElement cyclicElementSearchByXpath(String target) {

        for (int i = 0; i < 1000; i++) {
            if (driver.findElements(By.xpath(target)).size() > 0) {
                break;
            }
            sleep(1);

        }

        return driver.findElement(By.xpath(target));
    }


    public void WaitElement(String locator) {
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

    }
    public void WaitLoading(String patrUrl) {
        wait.until(ExpectedConditions.urlContains(patrUrl));

    }

    public static void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public WebElement randomChoiceFromDropdown(String xpath) {

        List<WebElement> listOfElements = driver.findElements(By.xpath(xpath));
        // select a random one
        Random random = new Random();
        WebElement someRandomElement = listOfElements.get(random.nextInt(listOfElements.size()));
        someRandomElement.click();
        return someRandomElement;

    }

    public boolean isElementPresent(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setClipboardData(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void attachFile() {
        setClipboardData(this.getRevision().getAbsolutePath());
        try {
            Robot robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(300);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(300);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void unhide(WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['visibility']='visible';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile2(By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(input);
        input.sendKeys(file);
    }


}





