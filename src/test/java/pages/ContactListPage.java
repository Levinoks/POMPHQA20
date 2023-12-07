package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class ContactListPage extends Screen {
    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement textNoContacts;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement menuMoreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement btnLogOut;
    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement btnAddNewContact;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnYesDeleteContact;

    public By getElementByPhoneNumber(String phone) {
        return By.xpath(String.format("//*[@text='%s']", phone));
    }


    By phoneContainer = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");

    public boolean isPhoneNumberOnThePage(String phoneNumber) {
        pause(10000);
        waitElement(btnAddNewContact, 5);
        boolean flag = false;
        try {
            scrollToPhoneNumber(phoneNumber);
            pause(10000);
            findElementBase(getElementByPhoneNumber(phoneNumber));
            flag = true;
            System.out.println(flag + "-------------------------");
        } catch (Exception e) {
            e.getMessage();
        }
        return flag;
    }

    public AddNewContactPage clickBtnAddNewContact() {
        waitElement(btnAddNewContact, 3);
        clickBase(btnAddNewContact);
        return new AddNewContactPage(driver);
    }

    public AuthentificationPage logout() {
        clickBase(menuMoreOptions);
        clickBase(btnLogOut);
        return new AuthentificationPage(driver);

    }


    public boolean validateContactListOpened() {
        return isTextEqual(textTitle, "Contact list");

    }

    public boolean validateContactListEmpty() {
        return isTextEqual(textNoContacts, "No Contacts. Add One more!");

    }

    public List<MobileElement> list(By locator) {
        List<MobileElement> phoneNumbers = driver.findElements(locator);
        return phoneNumbers;
    }

    public boolean validateCurrentContactCreated(int i) {
        for (MobileElement mob : list(phoneContainer)) {
            if (isTextEqual(mob, "1234567" + i))
                return true;
        }
        return false;
    }

    public ContactListPage scrollToPhoneNumber(String phone) {
        try {
            scrollToElementBase(getElementByPhoneNumber(phone));
        } catch (Exception e) {
            e.getMessage();
        }
        return this;

    }

    public boolean validationNewContactAdded(String phone) {
        waitElement(btnAddNewContact, 5);
        scrollToElementBase(getElementByPhoneNumber(phone));


        if (isElementByTextPresent(getPhoneNumberInContactsLocator(phone), phone)) {
            System.out.println("Element with text '" + phone + "' exists");
            return true;
        } else {
            System.out.println("Element with text '" + phone + "' does not exist");
        }

        return false;
    }

    private boolean isElementByTextPresent(By locator, String text) {

        MobileElement element = driver.findElement(locator);
        element.getText();
        if (element.getText().equals(text)) {
            return true;
        }
        return false;
    }

    public By getPhoneNumberInContactsLocator(String phone) {
        return By.xpath(String.format("//*[@text='%s']", phone));
    }

    public ContactListPage moveContactByPhoneNumberTotheRight(String phone) {
        MobileElement phoneNumber = getElemBy(getElementByPhoneNumber(phone));
        Rectangle rectangle = phoneNumber.getRect();
        int xStart = rectangle.getX() + rectangle.getWidth() / 8;
        int xEnd = rectangle.getX() + rectangle.getWidth() * 6 / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();


        return this;
    }


    public ContactListPage clickYesBtnPopUpForContactDelete() {
        clickBase(btnYesDeleteContact);
        return this;
    }

    public ContactListPage deleteAllContacts() {
        String phone = "";
        List<MobileElement> listElems = driver.findElements(phoneContainer);
        while (!listElems.isEmpty()) {
            try {
                MobileElement elem = getElemBy(phoneContainer);

                phone = getTextBase(elem);
                moveContactByPhoneNumberTotheRight(phone);
                clickYesBtnPopUpForContactDelete();
            } catch (Exception e) {
                e.getMessage();
            } finally {
                {
                    listElems = driver.findElements(phoneContainer);
                }
            }
        }
        return this;
    }

    public boolean valsidateContactListEmpty() {
        List<MobileElement> listElems = driver.findElements(phoneContainer);
        return listElems.isEmpty();
    }
}
