package pages;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AuthentificationPage extends Screen {
    public AuthentificationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    MobileElement inputPass;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/regBtn']")
    MobileElement btnRag;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/loginBtn']")
    MobileElement btnLogin;

    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement titleErrorTextAlert;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnAlertOKError;

    public ContactListPage clickRegBtn() {
        clickBase(btnRag);
        return new ContactListPage(driver);
    }



    public ContactListPage clickLoginBtn() {
        clickBase(btnLogin);
        return new ContactListPage(driver);
    }
    public AuthentificationPage clickOKBtn() {
        clickBase(btnAlertOKError);
        return this;
    }

    public AuthentificationPage clickLoginBtnNegative() {
        clickBase(btnLogin);
        return this;
    }

    public AuthentificationPage fillEmail(String email) {
        typeTextBase(inputEmail, email);
        return this;
    }

    public AuthentificationPage fillPassword(String password) {
        typeTextBase(inputPass, password);
        return this;
    }
    public ContactListPage login(UserDTO user){
        return  fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginBtn();
    }
    public boolean validateErrorTitleAlertCorrect(){
        return isTextEqual(titleErrorTextAlert, "Error");
    }
}
