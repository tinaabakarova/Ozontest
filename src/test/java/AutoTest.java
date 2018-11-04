import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.aplana.steps.MainPage;

public class AutoTest {
    MainPage user = new MainPage();

    @Before
    public void before(){
        user.startUp();
    }

    @After
    public void after(){
        user.endTest();
    }

    @Test
    public void checkPriceCount(){

        user.doSearch("iPhone 7 Plus/8 Plus Black");
        user.waitForVisible(By.xpath("//*[@class='item-wrapper']"));
        user.getPrice("147034039");
        user.addItemToCart("147034039");
        user.getPrice("138208691");
        user.addItemToCart("138208691");
        user.getPrice("145231983");
        user.addItemToCart("145231983");
        user.goToTheCart();
        user.waitForVisible(By.xpath("//div[contains(text(), 'Подтвердить заказ')]"));
        user.assertPrice("147034039");
        user.assertPrice("138208691");
        user.assertPrice("145231983");
        System.out.println("Цены совпадают");
    }
}
