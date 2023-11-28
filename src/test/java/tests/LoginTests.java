package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {
    @Test
    public void positiveLogin(){
Assert.assertTrue(new SplashPage(driver).goToAuthPage().login(UserDTO.builder()
                .email("qwer1@hh.e")
                .password("User#12345")
        .build()).validateContactListOpened());
    }
    @Test
    public void negativeLogin(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("User#12345")
                .clickLoginBtnNegative().validateErrorTitleAlertCorrect());
    }
}
