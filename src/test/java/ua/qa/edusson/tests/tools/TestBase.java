package ua.qa.edusson.tests.tools;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ua.qa.edusson.utils.ApplicationManager;
import ua.qa.edusson.utils.WebWindow;


/**
 * Created by tester on 12.08.2016.
 */
@Listeners(MyTestListener.class)
public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    public static String handleHost;


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @Parameters("site")
    @BeforeMethod
    public void chooseSite(@Optional("https://essays.studymoose.com/") String siteName, ITestContext context) {
        context.setAttribute("app", app);
        app.driver.get(siteName);
        handleHost = app.driver.getWindowHandle(); //handle first Window
    }

    @AfterMethod
    public void closeUnused() throws Exception {
        try {
            WebWindow.closeUnusedTabs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


