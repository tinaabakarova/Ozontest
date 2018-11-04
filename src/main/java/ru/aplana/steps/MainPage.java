package ru.aplana.steps;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class MainPage extends CommonSteps{
    Map<String, String> map;
    private static final String itemFormat = "(//*[@id='%s']//button)[1]";
    private static final String priceFormat = "//div[@id='%s']//span[@class='price-number']/span";
    private static final String assertionPriceFormat = "//div[@data-id='%s']//div[contains(@class,'eCartItem_price')]";

    @FindBy(xpath = "//*[@type='text']")
    WebElement searchField;

    @FindBy(xpath = "//*[@href='/context/cart'][contains(@class,'eMyOzon_ItemWrap')]")
    WebElement cart;


    public MainPage() {
        BASE_URL = (String) TestProps.getInstance().getProperties().get("base.url");;
        map = new HashMap<String, String>();
    }
    //step1
    public void doSearch(String text){
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
    }
    //step2
    public void getPrice(String id){
        By itemLocator = By.xpath(String.format(priceFormat, id));
        String price = findByLocator(itemLocator).getText() + ",00";
        map.put(id, price);
    }

    public void addItemToCart(String id){
        By categoryLocator = By.xpath(String.format(itemFormat, id));
        click(categoryLocator);

    }
    //step3
    public void goToTheCart(){
        jse.executeScript("arguments[0].scrollIntoView(true);", cart);
        cart.click();
    }

    //step4
    public void assertPrice(String id){
        By assertionPriceLocator = By.xpath(String.format(assertionPriceFormat, id));
        String price = findByLocator(assertionPriceLocator).getText();
        price = StringUtils.substringBefore(price, " руб");

        Assert.assertTrue("Цены не совпадают", map.get(id).equalsIgnoreCase(price));
    }
}
