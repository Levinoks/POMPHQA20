package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListPage extends Screen {
    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement textNoContacts;


    public boolean validateContactListOpened() {
        return isTextEqual(textTitle, "Contact list");

    }
    public boolean validateContactListEmpty() {
        return isTextEqual(textNoContacts, "No Contacts. Add One more!");

    }
}
