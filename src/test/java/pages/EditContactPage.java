package pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

public class EditContactPage extends Screen {
    public EditContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@class='android.widget.Button']")
    MobileElement btnUpdate;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone;

    public By getElementByPhoneNumber(String phone) {
        return By.xpath(String.format("//*[@text='%s']", phone));
    }

    public EditContactPage moveContactByPhoneNumberTotheLeft(String phone) {
        MobileElement phoneNumber = getElemBy(getElementByPhoneNumber(phone));
        Rectangle rectangle = phoneNumber.getRect();
        int xStart = rectangle.getX() + rectangle.getWidth() / 8;
        int xEnd = rectangle.getX() + rectangle.getWidth() * 6 / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xEnd, y))
                .moveTo(PointOption.point(xStart, y))
                .release()
                .perform();


        return this;
    }

    public ContactListPage updateCreatedContactByEmail() {
        typeTextBase(inputEmail, fakerEmail);
        clickBase(btnUpdate);
        return new ContactListPage(driver);

    }

    public ContactListPage updateCreatedContactByPhone() {
        typeTextBase(inputPhone, randomPhone);
        clickBase(btnUpdate);
        return new ContactListPage(driver);
    }
}

