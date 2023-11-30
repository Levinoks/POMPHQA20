package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SplashPage;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    Random random =new Random();
    String email=random.nextInt(1000)+1000+"user@gmail.com";
    @Test
    public void positiveRegistration(){
        System.out.println(email);

        Assert.assertTrue(new SplashPage(driver).goToAuthPage().registration(UserDTO.builder()
                .email(email)
                .password("User#12345")
                .build()).validateContactListEmpty());
    }
    @Test
    public void negativeRegistration_UserAlreadyExist(){

        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillEmail("qwer1@hh.e").fillPassword("User#12345").clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }
    @Test
    public void negativeRegistration_WrongPassword(){
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillEmail(email).fillPassword("User12345").clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }
}
