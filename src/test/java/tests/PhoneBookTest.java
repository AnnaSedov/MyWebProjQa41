package tests;

import config.BaseTest;
import helpers.TopMenuItem;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTest extends BaseTest {
    @Test
    @Parameters("browser")
    public void phoneBookTest001() throws InterruptedException{
        MainPage mainPage=new MainPage(getDriver());
        LoginPage  loginPage =mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

//        loginPage.fillEmailField("myemail@mail.com").clickByRegistrationButton();
//        Thread.sleep(15000);
        //hw2
        //test 1 positive registration with correct login and password
        loginPage.fillEmailField("myemailAnna22@mail.com").fillPasswordField("1Test22!").clickByRegistrationButton();
        Thread.sleep(15000);
        //test 2 positive login with correct login and password
        loginPage.fillEmailField("homeann7@gmail.com").fillPasswordField("21212zZ!").clickByLoginButton();
        Thread.sleep(15000);




    }

}
