package uiTest.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Hooks;
import utilities.Logs;

public class GittiGidiyorBoxPage {
    public GittiGidiyorBoxPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    Logs logs = new Logs();
    Hooks hooks = new Hooks();
    Select select;

    @FindBy(css = "span[class='basket-title']")
    public WebElement myBasket;

    @FindBy(css = "p[class='new-price']")
    public WebElement payPrice;

    @FindBy(xpath = "//select[@class='amount']")
    public WebElement dropDownMenu;

    @FindBy (xpath = "(//i[@class='gg-icon gg-icon-bin-medium'])[1]")
    public WebElement deleteProduct;

    @FindBy (xpath = "//*[text()='Sepetinizde ürün bulunmamaktadır.']")
    public WebElement emptyBasket;



    public GittiGidiyorBoxPage goToMyBasket() {
        hooks.waitFor(2);
        hooks.click(myBasket);
        logs.info("Sepete gidildi");
        String price1 = payPrice.getText().replaceAll("\\D", "");
        Double payPrice = Double.parseDouble(price1);
        Assert.assertTrue(payPrice == GittiGidiyorProductPage.price);
        logs.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyati doğrulandi");
        select = new Select(dropDownMenu);
        select.selectByValue("2");
        logs.info("Ürün adedi 2 ye cikarildi");
        Assert.assertEquals("2", dropDownMenu.getAttribute("value"));
        logs.info("Ürünün 2 adet oldugu dogrulandi");
        hooks.click(deleteProduct);
        hooks.waitFor(2);
        Assert.assertTrue(emptyBasket.getText().contains(ConfigReader.getProperty("expectedEmptyText")));
        Assert.assertTrue(emptyBasket.isEnabled());
        logs.info("Sepetin bos oldugu dogrulandi");
        Driver.closeDriver();
        return this;
    }
}
