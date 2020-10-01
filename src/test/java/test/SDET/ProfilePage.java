package test.SDET;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static test.SDET.helpers.Waiters.clickabilityWait;
import static test.SDET.helpers.Waiters.invisabilityWait;

public class ProfilePage {
    public WebDriver driver;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = ".gb_Ef")
    private WebElement searchParamBtn;

    @FindBy(css = ".ZH.nr.aQa")
    private WebElement searchField;

    @FindBy(css = "[class = 'T-I J-J5-Ji Zx aQe T-I-atl L3']")
    private WebElement searchBtn;

    @FindBy(css = ".zA")
    private WebElement searchRes;

    @FindBy(css = ".z0 > .L3")
    private WebElement newLetterBtn;

    @FindBy(css = ".oj .vO")
    private WebElement toField;

    @FindBy(css = ".aoT")
    private WebElement topicField;

    @FindBy(css = ".aoI .Am.Al")
    private WebElement letterField;

    @FindBy(css = ".btA .aoO")
    private WebElement sendBtn;

    @Step
    public void searchParamBtnClick() {
        clickabilityWait(this.driver, 5, searchParamBtn);
        searchParamBtn.click();
    }

    @Step
    public void inputSearch(String search) {
        clickabilityWait(this.driver, 5, searchField);
        searchField.sendKeys(search);
    }

    @Step
    public void searchBtnClick() {
        clickabilityWait(this.driver, 5, searchBtn);
        searchBtn.click();
    }

    @Step
    public String getSearchRes() {
        int count = (driver.findElements(By.cssSelector(".av")).size());

        return String.valueOf(count);
    }

    @Step
    public void newLetterBtnClick() {
        newLetterBtn.click();
    }

    @Step
    public void inputToField(String recipient) {
        clickabilityWait(this.driver, 5, toField);
        toField.sendKeys(recipient + Keys.ENTER);
    }

    @Step
    public void topicFieldClick() {
        topicField.click();
    }

    @Step
    public void inputTopicField(String topic) {
        topicField.sendKeys(topic);
    }

    @Step
    public void letterFieldClick() {
        letterField.click();
    }

    @Step
    public void inputLetter(String letter) {
        letterField.sendKeys(letter);
    }

    @Step
    public void sendBtnClick() {
        sendBtn.click();
        invisabilityWait(this.driver, 10);
    }

}
