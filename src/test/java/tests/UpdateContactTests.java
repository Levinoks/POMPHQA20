package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.Random;

public class UpdateContactTests extends AppiumConfig {

    @BeforeClass
    public void beforeClass() {
        new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                .email("qwer1@hh.e")
                .password("User#12345")
                .build());
    }
    @AfterClass
    public void afterClass() {
        new ContactListPage(driver).deleteAllContacts().logout();
    }

    @AfterMethod
    public void afterMethod() {
        new AddNewContactPage(driver).backToContactList();
    }

    @Test
    public void positiveUpdateContactTest_ChangeEmail() {
        ContactInfoPage page = new ContactInfoPage(driver);
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        ContactDTO contact = ContactDTO.builder()
                .name("tester" + i)
                .lastName("user" + i)
                .email(i + "user@gmail.com")
                .phone("1234567" + i)
                .address("Haifa")
                .description("contact# " + i)
                .build();
        new ContactListPage(driver).clickBtnAddNewContact().addNewContact(contact);
        new EditContactPage(driver).moveContactByPhoneNumberTotheLeft(contact.getPhone())
                .updateCreatedContactByEmail();
        Assert.assertTrue(new ContactInfoPage(driver)
                .validateCreatedContactUpdated(contact.getPhone(), page.getFakerEmail()));
    }

    @Test
    public void positiveUpdateContactTest_ChangePhone() {
        ContactInfoPage page = new ContactInfoPage(driver);
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        ContactDTO contact = ContactDTO.builder()
                .name("tester" + i)
                .lastName("user" + i)
                .email(i + "user@gmail.com")
                .phone("1234567" + i)
                .address("Haifa")
                .description("contact# " + i)
                .build();
        new ContactListPage(driver).clickBtnAddNewContact().addNewContact(contact);
        new EditContactPage(driver).moveContactByPhoneNumberTotheLeft(contact.getPhone())
                .updateCreatedContactByPhone();
        Assert.assertTrue(new ContactInfoPage(driver)
                .validateCreatedContactUpdatedPhone(contact.getName(), contact.getLastName(), page.getRandomPhone()));
    }

}
