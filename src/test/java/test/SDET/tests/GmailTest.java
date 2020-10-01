package test.SDET.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.SDET.ConfProperties;
import test.SDET.LoginPage;
import test.SDET.ProfilePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Execution(ExecutionMode.CONCURRENT)
public class GmailTest {

    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://192.168.31.122:4444/wd/hub"), cap);

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        driver.get(ConfProperties.getProperty("startPage"));
    }

    @Test
    public void gmailTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.nextBtnClick();

        loginPage.inputPasswd(ConfProperties.getProperty("passwd"));
        loginPage.loginBtnClick();

        profilePage.searchParamBtnClick();
        profilePage.inputSearch(ConfProperties.getProperty("addressee"));
        profilePage.searchBtnClick();

        profilePage.newLetterBtnClick();
        profilePage.inputToField(ConfProperties.getProperty("addressee"));
        profilePage.topicFieldClick();
        profilePage.inputTopicField("Тестовое задание Чекурова");
        profilePage.letterFieldClick();
        String letter = profilePage.getSearchRes();
        profilePage.inputLetter("Найдено писем от " + ConfProperties.getProperty("addressee") + ": " + letter);
        profilePage.sendBtnClick();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
