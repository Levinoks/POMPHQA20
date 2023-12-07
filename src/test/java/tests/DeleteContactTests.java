package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class DeleteContactTests extends AppiumConfig {
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
              new ContactListPage(driver).logout();
    }

    @Test
    public void deleteOneContactPositive(){
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
       // new ContactListPage(driver).pause(10);
        Assert.assertFalse(new ContactListPage(driver).clickBtnAddNewContact().addNewContact(ContactDTO.builder()
                .name("tester" + i)
                .lastName("user" + i)
                .email(i + "user@gmail.com")
                .phone("1234567" + i)
                .address("Haifa")
                .description("contact# " + i)
                .build())
                .moveContactByPhoneNumberTotheRight("1234567" + i)
                .clickYesBtnPopUpForContactDelete()
                .validateCurrentContactCreated(i));
    }

    @Test
    public void deleteAllContacts(){
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);

        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact().addNewContact(ContactDTO.builder()
                        .name("tester" + i)
                        .lastName("user" + i)
                        .email(i + "user@gmail.com")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact# " + i)
                        .build())
                .deleteAllContacts()
                .valsidateContactListEmpty());

    }

}
