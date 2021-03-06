package ua.qa.edusson.pages.CommonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static ua.qa.edusson.tests.tools.TestBase.app;

public class Chat {



    public static String clientMessage;
    public static String writerMessage;
    public static String writerOrderUrl  = "https://edusson.com/order/view/203585" ;
    public static String customerOrderUrl  = "https://edubirdie.com/order/view/203585";
    public static String dialog = "//div[contains(@class, 'interlocutor-message')][last()]/div[2]";

    public void openChat() {
        app.getHelper().waitElement("//p[text()=' Chat']");
        app.getHelper().cyclicElementSearchByXpath("//p[text()=' Chat']").click();
    }

    public void chooseDialog() {
        app.getHelper().waitElement("//span[text()='chat testing']");
        app.getHelper().cyclicElementSearchByXpath("//span[text()='chat testing']").click();
    }

    public void sendMessage() {
        long curTime = System.currentTimeMillis();
        clientMessage = String.valueOf(curTime);
        System.out.println(clientMessage);
        app.getHelper().cyclicElementSearchByXpath("//div[@class='write-message']/textarea").sendKeys(clientMessage);
        app.getHelper().cyclicElementSearchByXpath("//div[@class='write-message']/textarea").sendKeys(Keys.ENTER);
    }
    public String getMessageText() {
        return app.driver.findElement(By.xpath(dialog)).getText();
    }

}
