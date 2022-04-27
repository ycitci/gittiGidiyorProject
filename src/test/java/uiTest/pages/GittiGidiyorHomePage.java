package uiTest.pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Logs;



public class GittiGidiyorHomePage {
    public GittiGidiyorHomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    Logs logs = new Logs();

    @FindBy (xpath = "//*[text()='Kapat']")
    public WebElement cookies;

    public GittiGidiyorProductPage goToHomePage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        logs.startTestCase("GittiGidiyor Test");
        logs.info("Gittigidiyor url'ine gidildi");
        Assert.assertEquals(ConfigReader.getProperty("expectedUrl"),Driver.getDriver().getCurrentUrl());
        logs.info("Url dogrulandi");
        try {
            cookies.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return new GittiGidiyorProductPage();
    }

}
