package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthentificationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {
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
    @Test
    public void positiveLogin(){
        flagIsUserLogin=true;
Assert.assertTrue(new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                .email("qwer1@hh.e")
                .password("User#12345")
        .build()).validateContactListOpened());
    }
    @Test
    public void negativeLogin(){
        flagIsPopUpErrorDisplays=true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("User#12345")
                .clickLoginBtnNegative().validateErrorTitleAlertCorrect());
    }
}
