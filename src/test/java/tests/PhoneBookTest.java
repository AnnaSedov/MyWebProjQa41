package tests;

import config.BaseTest;
import helpers.*;
import helpers.TopMenuItem;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import io.qameta.allure.Allure;
import model.Contact;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTest extends BaseTest {
//    @Test
//    @Parameters("browser")
//    public void phoneBookTest001() throws InterruptedException{
//
//        MainPage mainPage=new MainPage(getDriver());
//        LoginPage  loginPage =mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//
//       loginPage.fillEmailField("myemail@mail.com").clickByRegistrationButton();
//       Thread.sleep(5000);
//         }
    //hw2
//    @Test
//    @Parameters("browser")
//    public void phoneBookTest002() throws InterruptedException{
//
//        MainPage mainPage=new MainPage(getDriver());
//        LoginPage  loginPage =mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//        //test 1 positive registration with correct login and password
//        loginPage.fillEmailField("myemailAnna3@mail.com").fillPasswordField("1Test22!").clickByRegistrationButton();
//        Thread.sleep(5000);
//
//    }
//    @Test
//    @Parameters("browser")
//    public void phoneBookTest003() throws InterruptedException {
//
//        MainPage mainPage = new MainPage(getDriver());
//        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//        //test 2 positive login with correct login and password
//        loginPage.fillEmailField("homeann7@gmail.com").fillPasswordField("21212zZ!").clickByLoginButton();
//        Thread.sleep(5000);
//    }
    //05032024
@Test(description = "The test checks the empty field warning declaration.")
@Parameters("browser")
public void registrationWithoutPassword(@Optional("chrome") String browser) throws InterruptedException {
    Allure.description("User already exist. Login and add contact.!");

    MainPage mainPage = new MainPage(getDriver());
    Allure.step("Click by Login button");
    LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
    Allure.step("Click by Reg button");
    String expectedString = "Wrong";


    Alert alert= loginPage.fillEmailField("myemail@mail.com").clickByRegistrationButton();
     boolean isAlertHandled=AlertHandler.handAlert(alert,expectedString);
    Assert.assertTrue(isAlertHandled);


}

@Test(description = "The test of login with exist user and add new contact")
    @Parameters("browser")
    public void loginOfAnExistingUserAddContact(@Optional("firefox") String browser ) throws InterruptedException{
 //  Allure.description("User already exist. Login and add contact.");
    MainPage mainPage=new MainPage(getDriver());
   // Allure.step("Click by Login button");
    LoginPage loginPage=mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//
//    Allure.step("Click by Log button");
//    String expectString="";

    Alert alert= (Alert) loginPage.fillEmailField("homeann7@gmail.com").fillPasswordField("21212zZ!").clickByLoginButton();
    Thread.sleep(5000);
    AddPage addPage=mainPage.openTopMenu(TopMenuItem.ADD.toString());
  //  addPage.fillFormAndSave(new Contact(NameAndLastNameGenerator.generateName(),NameAndLastNameGenerator.generateLastName(),EmailGenerator.generateEmail(5,4,2),AddressGenerator.generateAddress(),PhoneNumberGenerator.generatePhoneNumber(),""));
    addPage.fillFormAndSave(new Contact("a","b","aa@bb.com","ddd","22222222222",""));
    Thread.sleep(5000);
 ////   boolean isAlertHandled=AlertHandler.handAlert(alert,expectString);

}


}
