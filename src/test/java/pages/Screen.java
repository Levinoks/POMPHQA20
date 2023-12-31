package pages;

import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Screen {
    AppiumDriver<MobileElement> driver;

    public Screen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    Faker faker=new Faker();
    String fakerEmail=faker.internet().emailAddress();
    String randomPhone=new Random().nextInt(1000) + 1000+"1234567";

    public void typeTextBase(MobileElement el, String text){
        el.click();
        el.clear();
        el.sendKeys(text);
        driver.hideKeyboard();
    }

    public void clickBase(MobileElement el){
        el.click();
    }
    public String getTextBase(MobileElement el){
        return el.getText().toUpperCase().trim();
    }
    public boolean isTextEqual(MobileElement el, String text){
        if(getTextBase(el).equals(text.toUpperCase())) {
            return true;
        }else{
            System.out.println("actual result: "+getTextBase(el)+ "expected result: "+text.toUpperCase());
            return false;
        }

    }
    public void pause(long mill){
        try {
            Thread.sleep(mill*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnBackOnEmulator(){
        driver.navigate().back();
    }
    public MobileElement getElemBy(By by){
        return driver.findElement(by);
    }
    public void waitElement(MobileElement el, int time){
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(el));
    }
    public MobileElement findElementBase(By by){
        return driver.findElement(by);
    }
    public void scrollToElementBase(By by){
        scrollToElementMobEl(findElementBase(by));
    }

    private void scrollToElementMobEl(MobileElement el) {
        Rectangle rect=el.getRect();
        int xTo=rect.getX()+rect.getWidth()/2;
        int yTo=rect.getY()+rect.getHeight()/2;

        TouchAction<?> action=new TouchAction<>(driver);
        action.longPress(PointOption.point(xTo, yTo))
                .moveTo(PointOption.point(xTo, 0))
                .release()
                .perform();
    }
}
