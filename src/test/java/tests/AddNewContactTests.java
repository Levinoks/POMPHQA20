package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddNewContactPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    boolean flagToCloseAlert = false;

    @BeforeClass
    public void beforeClass() {
        new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                .email("qwer1@hh.e")
                .password("User#12345")
                .build());
    }

    @AfterClass
    public void afterClass() {
        //need to open ContactPage before logout
        new ContactListPage(driver).logout();
    }

    @AfterMethod
    public void afterMethod() {
        if (flagToCloseAlert) {
            flagToCloseAlert = false;
            new AddNewContactPage(driver).clickOKCloseAlert().btnBackOnEmulator();


        }
    }

    @Test
    public void positiveAddNewContactTest_validationWithList() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        new ContactListPage(driver).pause(10);
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact().addNewContact(ContactDTO.builder()
                        .name("tester" + i)
                        .lastName("user" + i)
                        .email(i + "user@gmail.com")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact# " + i)
                        .build())
                .validateCurrentContactCreated(i));

    }

    @Test
    public void positiveAddNewContactTest_validationWithoutList() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        new ContactListPage(driver).pause(10);
        ContactDTO contact=ContactDTO.builder()
                .name("tester" + i)
                .lastName("user" + i)
                .email(i + "user@gmail.com")
                .phone("1234567" + i)
                .address("Haifa")
                .description("contact# " + i)
                .build();
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact().addNewContact(contact)
                .validationNewContactAdded(contact.getPhone()));

    }


    @Test
    public void negativeEmptyPhoneTest() {
        int i;

        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        flagToCloseAlert = true;
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact().addNewContactNegative(ContactDTO.builder()
                        .name("tester" + i)
                        .lastName("user" + i)
                        .email(i + "user@gmail.com")
                        .phone("")
                        .address("Haifa")
                        .description("contact# " + i)
                        .build())
                .validateErrorMessage());

    }
}
