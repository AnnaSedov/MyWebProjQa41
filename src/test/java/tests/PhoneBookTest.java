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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

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
    public void loginOfAnExistingUserAddContact(@Optional("chrome") String browser ) throws InterruptedException{
  Allure.description("User already exist. Login and add contact.");

    MainPage mainPage=new MainPage(getDriver());
    Allure.step("login with exist user");
    LoginPage loginPage=mainPage.openTopMenu(TopMenuItem.LOGIN.toString());


  //   loginPage.fillEmailField("homeann7@gmail.com").fillPasswordField("21212zZ!").clickByLoginButton();
   Allure.step("read email and password from resources");
    String PROPERTIES_FILE_PATH="src/test/resources/resources.properties";
    loginPage.fillEmailField(PropertiesReader.getProperty("existingUserEmail",PROPERTIES_FILE_PATH)).fillPasswordField(PropertiesReader.getProperty("existingUserPassword",PROPERTIES_FILE_PATH)).clickByLoginButton();

    mainPage.openTopMenu(TopMenuItem.ADD.toString());
    AddPage addPage=new AddPage(getDriver());
Allure.step("generate new contact");
   Contact contact=new Contact(NameAndLastNameGenerator.generateName(),NameAndLastNameGenerator.generateLastName(),EmailGenerator.generateEmail(5,4,2),AddressGenerator.generateAddress(),
           PhoneNumberGenerator.generatePhoneNumber()," test");
   addPage.fillFormAndSave(contact);

   ContactsPage cp=new ContactsPage(getDriver());
   Assert.assertTrue(cp.getDataFromContactList(contact));
    TakeScreen.takeScreenshot("screen");
    //hw 2024-03-14 add if () and parameter late
Allure.step("write contact to data");
PropertiesWriter.writeProperties("contactPhone",contact.getPhone(),false);
PropertiesWriter.writeProperties("contactName",contact.getName(),false);

   Thread.sleep(5000);


}

//hw 2024-03-10
@Test(description = "The positive registration test  with correct login and password")
    @Parameters("browser")
    public void positiveRegistrationWithCorrectLoginAndPassword(@Optional("edge") String browser)throws InterruptedException{
        Allure.description("Enter correct login and password");
        Allure.step("Open login page");
        MainPage mainPage=new MainPage(getDriver());
        LoginPage loginPage=mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Fill email with generator and exist password from resources");
     //   loginPage.fillEmailField(EmailGenerator.generateEmail(4,2,3)).fillPasswordField(PropertiesReader.getProperty("existingUserPassword")).clickByRegistrationButton();
String PROPERTIES_FILE_PATH="src/test/resources/resources.properties";
    loginPage.fillEmailField(EmailGenerator.generateEmail(6,4,2)).fillPasswordField(PropertiesReader.getProperty("existingUserPassword",PROPERTIES_FILE_PATH)).clickByRegistrationButton();
    Allure.step("Find button- Sign out");

        ContactsPage cp=new ContactsPage(getDriver());


        Assert.assertTrue(cp.FindButton());
        TakeScreen.takeScreenshot("positReg");
    //Thread.sleep(5000);
}

//hw 2024-03-14 delete user from contacts list
    @Test(description="The positive test of deleting user from contacts list")
    @Parameters("browser")
    public void posDeleteExistUserFromContactsList(@Optional("firefox") String browser){
        Allure.description("The positive test of deleting user from contacts list");

        MainPage mainPage=new MainPage(getDriver());
        Allure.step("login with exist user");
        LoginPage loginPage=mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        Allure.step("read email and password from resources");
        loginPage.fillEmailField(PropertiesReader.getProperty("existingUserEmail","src/test/resources/resources.properties")).fillPasswordField(PropertiesReader.getProperty("existingUserPassword","src/test/resources/resources.properties")).clickByLoginButton();

       ContactsPage cp=new ContactsPage(getDriver());
       Allure.step("read contact from data for deleting");
       String findContactKey =PropertiesReader.getProperty("contactPhone","src/test/resources/data.properties");
        String findContactName=PropertiesReader.getProperty("contactName","src/test/resources/data.properties");
        Allure.step("find contact with key phone and delete");

        Assert.assertTrue(cp.deleteContact(findContactKey));


    }
}
