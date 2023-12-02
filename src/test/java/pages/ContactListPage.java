package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
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


    By phoneContainer = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");


    public AddNewContactPage clickBtnAddNewContact() {
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

    public boolean validationNewContactAdded(String phone) {
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
}
