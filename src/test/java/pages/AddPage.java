package pages;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPage extends BasePage{
    @FindBy(xpath = "//input[@placeholder(text(),'Name')]")
    WebElement nameField;
    @FindBy(xpath = "//input[@placeholder(text(),'Last Name')]")
    WebElement lastNameField;
    @FindBy(xpath = "//input[@placeholder(text(),'Phone')]")
    WebElement phoneField;
    @FindBy(xpath = "//input[@placeholder(text(),'email')]")
    WebElement emailField;
    @FindBy(xpath = "//input[@placeholder(text(),'Address')]")
    WebElement addressField;
    @FindBy(xpath = "//input[@placeholder(text(),'Description')]")
    WebElement descriptionField;
    @FindBy(xpath = "//b[contains(text(),'Save')]")
    WebElement saveButton;
    public AddPage(WebDriver driver){
        setDriver(driver);
        //automatically set elements on page
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,20),this);
    }
    public void fillFormAndSave(Contact contact){
        nameField.sendKeys(contact.getName());
        lastNameField.sendKeys(contact.getLastName());
       if(contact.getPhone().length()>=10&&contact.getPhone().length()<=15) {
           phoneField.sendKeys(contact.getPhone());
       }else{
           throw new IllegalArgumentException("The phone number length is small or big");
       }
        emailField.sendKeys(contact.getEmail());
        addressField.sendKeys(contact.getAddress());
        descriptionField.sendKeys(contact.getDescriptions());
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement saveButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Save')]")));
        saveButton.click();
    }
}