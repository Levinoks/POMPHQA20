package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthentificationPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    boolean flagIsUserLogin=false;
    boolean flagIsPopUpErrorDisplays=false;

    @AfterMethod
    public void postconditions(){
        if(flagIsUserLogin){
            flagIsUserLogin=false;
            new ContactListPage(driver).logout();
        }else if(flagIsPopUpErrorDisplays){
            flagIsPopUpErrorDisplays=false;
            new AuthentificationPage(driver).clickOKBtn();
        }

    }
    Random random =new Random();
    String email=random.nextInt(1000)+1000+"user@gmail.com";
    @Test
    public void positiveRegistration(){
        System.out.println(email);
        flagIsUserLogin=true;

        Assert.assertTrue(new SplashPage(driver).goToAuthPage().registration(UserDTO.builder()
                .email(email)
                .password("User#12345")
                .build()).validateContactListEmpty());
    }
    @Test
    public void negativeRegistration_UserAlreadyExist(){
        flagIsPopUpErrorDisplays=true;

        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillEmail("qwer1@hh.e").fillPassword("User#12345").clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }
    @Test
    public void negativeRegistration_WrongPassword(){
        flagIsPopUpErrorDisplays=true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillEmail(email).fillPassword("User12345").clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }
}
