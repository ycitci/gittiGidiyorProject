package uiTest.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Hooks;
import utilities.Logs;
import java.util.List;


public class GittiGidiyorProductPage {
    public GittiGidiyorProductPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    Logs logs = new Logs();
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    Hooks hooks = new Hooks();
    public static double price;

    @FindBy(css = "input[data-cy='header-search-input']")
    public WebElement searchBox;

    @FindBy(css = "a[aria-label='2. sayfa']")
    public WebElement secondPage;

    @FindBy(xpath = "//h2[@class='sc-1ku3a4v-0 sc-7u3xly-0 clhtrN jytHfD sc-1n49x8z-16 khXIrI']")
    public List<WebElement> productList;

    @FindBy(id = "sp-title")
    public WebElement producktInfo;

    @FindBy(id = "sp-price-lowPrice")
    public WebElement priceInfo;

    @FindBy (id ="add-to-basket")
    public WebElement addBasket;

    public GittiGidiyorProductPage productSearch() {

        searchBox.sendKeys(ConfigReader.getProperty("searchWord") + Keys.ENTER);
        logs.info("Arama kutusuna bilgisayar yazdilip aratildi");
        return this;
    }

    public GittiGidiyorProductPage selectWindow (){
        js.executeScript("window.scrollTo(0,4000)");
        secondPage.click();
        logs.info("İkinci sayfaya tiklandi");
        String blueColor = secondPage.getCssValue("background-color");
        String hex = Color.fromString(blueColor).asHex();
        Assert.assertEquals(hex,ConfigReader.getProperty("expectedColor"));
        logs.info("İkinci sayfada oldugu dogrulandi");
        return this;

    }
    public GittiGidiyorBoxPage productSelect (){
        int random = hooks.randomCount(productList.size());
        hooks.waitFor(2);
        hooks.jsClick(productList.get(random));
        logs.info("Random urun secildi");
        hooks.writer(producktInfo.getText(), priceInfo.getText());
        logs.info("Ürün bilgileri txt dosyasina yazildi");
         String price1 = priceInfo.getText().replaceAll("\\D","");
         price = Double.parseDouble(price1);
        hooks.click(addBasket);
        logs.info("Sepete eklendi");
        hooks.waitFor(2);
        return new GittiGidiyorBoxPage();
    }

}




