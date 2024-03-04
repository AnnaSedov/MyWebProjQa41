package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ContactsPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder(text(),'Name')]")
    WebElement NameField;
    @FindBy(xpath = "//input[@placeholder(text(),'Last Name')]")
    WebElement LastNameField;
    @FindBy(xpath = "//input[@placeholder(text(),'Phone')]")
    WebElement PhoneField;
    @FindBy(xpath = "//input[@placeholder(text(),'email')]")
    WebElement EmailField;
    @FindBy(xpath = "//input[@placeholder(text(),'Address')]")
    WebElement AddressField;
    @FindBy(xpath = "//input[@placeholder(text(),'Description')]")
    WebElement DescriptionField;
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;

    public ContactsPage(WebDriver driver){
        setDriver(driver);
        //automatically set elements on page
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);

    }



}
