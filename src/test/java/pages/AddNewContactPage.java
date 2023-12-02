package pages;

import dto.ContactDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactPage extends Screen{
    public AddNewContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement inputName ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement inputLastName ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone ;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement inputAddress;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement inputDescription;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement btnCreate;
    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement textAlertHerror;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnOkCloseAlert;

    public AddNewContactPage sendTextInputName(String name){
        typeTextBase(inputName, name);
        return this;
    }
    public AddNewContactPage sendTextInputLastName(String lastName){
        typeTextBase(inputLastName, lastName);
        return this;
    }
    public AddNewContactPage sendTextInputPhone(String phone){
        typeTextBase(inputPhone, phone);
        return this;
    }
    public AddNewContactPage sendTextInputEmail(String email){
        typeTextBase(inputEmail, email);
        return this;
    }
    public AddNewContactPage sendTextInputAddress(String address){
        typeTextBase(inputAddress, address);
        return this;
    }
    public AddNewContactPage sendTextInputDesc(String description){
        typeTextBase(inputDescription, description);
        return this;
    }

    public ContactListPage clickBtnCreate(){
        clickBase(btnCreate);
        return new ContactListPage(driver);
    }
    public AddNewContactPage clickBtnCreateNegative(){
        clickBase(btnCreate);
        return this;
    }

    public ContactListPage addNewContact(ContactDTO contactDTO){
        return sendTextInputName(contactDTO.getName())
                .sendTextInputLastName(contactDTO.getLastName())
                .sendTextInputEmail(contactDTO.getEmail())
                .sendTextInputPhone(contactDTO.getPhone())
                .sendTextInputAddress(contactDTO.getAddress())
                .sendTextInputDesc(contactDTO.getDescription())
                .clickBtnCreate();
    }

    public AddNewContactPage addNewContactNegative(ContactDTO contactDTO) {
        return sendTextInputName(contactDTO.getName())
                .sendTextInputLastName(contactDTO.getLastName())
                .sendTextInputEmail(contactDTO.getEmail())
                .sendTextInputPhone(contactDTO.getPhone())
                .sendTextInputAddress(contactDTO.getAddress())
                .sendTextInputDesc(contactDTO.getDescription())
                .clickBtnCreateNegative();
    }

    public boolean validateErrorMessage() {
        return isTextEqual(textAlertHerror, "Error");
    }

    public AddNewContactPage clickOKCloseAlert() {
        clickBase(btnOkCloseAlert);
        return this;
    }

    public ContactListPage backToContactList() {
        btnBackOnEmulator();
        return new ContactListPage(driver);
    }
}
