package tests;

import config.BaseTest;
import helpers.TopMenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTest extends BaseTest {
    @Test
    public void phoneBookTest001(){
        MainPage mainPage=new MainPage(getDriver());
        LoginPage  loginPage =mainPage.openTopMenu(TopMenuItem.LOGIN.toString());

        LoginPage.fillEmailField("my");
        Thread.sleep(5000);




    }

}
