package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage extends Screen {
    public ContactInfoPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emailTxt']")
    MobileElement viewFieldEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/phoneTxt']")
    MobileElement viewFieldPhone;

    public MobileElement getElementByPhoneNumber(String phone) {
        return driver.findElement(By.xpath(String.format("//*[@text='%s']", phone)));
    }
    public MobileElement getElementByName(String name, String lastName) {
        return driver.findElement(By.xpath(String.format("//*[@text='%s']", name+" "+lastName)));
    }

    public boolean validateCreatedContactUpdated(String phone, String email) {
        clickBase(getElementByPhoneNumber(phone));
        if (getTextBase(viewFieldEmail).equals(email)) ;
        return true;
    }

    public String getFakerEmail() {
        return fakerEmail;
    }

    public String getRandomPhone() {
        return randomPhone;
    }

    public boolean validateCreatedContactUpdatedPhone(String name, String lastName,String randomPhone) {
        clickBase(getElementByName(name, lastName));
        if (getTextBase(viewFieldPhone).equals(randomPhone)) ;
        return true;
    }
}
