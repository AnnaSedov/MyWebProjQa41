package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WindowAndTabTests {
    public static void main(String[] args) throws InterruptedException {
       // switchTab ();
      //  sliderTest();
        System.out.println(findRowByValue("Frank"));
        System.out.println(findRowByValueL("Frank"));
    }
//click right button of mouse
    public void rightMouseClick() throws InterruptedException {
        WebDriver driver=new FirefoxDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        WebElement element=driver.findElement(By.xpath("//a[contains(text),'Testing')]"));
        Actions actions=new Actions(driver);
        actions.contextClick(element).perform();
        Thread.sleep(3000);
        driver.quit();

    }
    public static void sliderTest() throws InterruptedException {
        WebDriver driver=new FirefoxDriver();

        driver.get("https://demoqa.com/slider");
        driver.manage().window().maximize();
        WebElement slider=driver.findElement(By.xpath("//input[@type='range']"));
        Actions action=new Actions(driver);
        action.dragAndDropBy(slider,30,0).build().perform();

//        slider.sendKeys(Keys.ARROW_RIGHT);
//        slider.sendKeys(Keys.ARROW_RIGHT);
//        slider.sendKeys(Keys.ARROW_RIGHT);
//        slider.sendKeys(Keys.ARROW_RIGHT);
//        slider.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(1000);
        driver.quit();
    }
    public static void switchTab(){
        WebDriver driver=new FirefoxDriver();
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();
        String mainWindowHandler=driver.getWindowHandle();//получаем идетн тек окна
        WebElement newButton=driver.findElement(By.xpath("//button[@id='tabButton']"));
        newButton.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindowHandles=driver.getWindowHandles();
        String newWindowHandler="";
        for(String windowHandle:allWindowHandles){
            if(!windowHandle.equals(mainWindowHandler)){
                newWindowHandler=windowHandle;
                break;
            }
        }
        driver.switchTo().window(newWindowHandler);
        WebElement newPageElement=driver.findElement(By.xpath("//hi[@id='sampleHeadinga']"));
        newPageElement.click();
        driver.quit();

    }
    @Test
    public static void dragAndDrop(){
        WebDriver driver=new FirefoxDriver();

        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();
        WebElement elementToDropA=driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement elementToDropB=driver.findElement(By.xpath("//div[@id='column-b']"));
        Actions actions=new Actions(driver);
        actions.dragAndDrop(elementToDropA,elementToDropB).build().perform();
      //  Thread.sleep(5000);
        driver.quit();

    }
    @Test
    //wait element
    public static void waitForAnElement(){
        WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
//        WebElement buttonStart=driver.findElement(By.xpath("//button"));
//        buttonStart.click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement textElement=driver.findElement(By.xpath("//div[@id='finish']"));
       textElement.click();
        driver.quit();
    }
    @Test
    //work with table
    public static String findRowByValue(String valueToFind){
        WebDriver driver=new FirefoxDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/tables");
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            WebElement tableElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            List<WebElement> rows=tableElement.findElements(By.tagName("tr"));
            for (WebElement row:rows){
                List<WebElement> cells=row.findElements(By.tagName("td"));
                for(WebElement cell: cells){
                    if(cell.getText().equals(valueToFind)){
                       System.out.println(row.getText());
                        return row.getText();
                    }
                }
            } return null;
        }finally {
            {driver.quit();
        }


    }
    }
    //with lambda
    @Test
    public static String findRowByValueL(String valueToFind){

        WebDriver driver=new FirefoxDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://the-internet.herokuapp.com/tables");
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            WebElement tableElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
            List<WebElement> rows=tableElement.findElements(By.tagName("tr"));
//            for (WebElement row:rows){
//                List<WebElement> cells=row.findElements(By.tagName("td"));
//                for(WebElement cell: cells){
//                    if(cell.getText().equals(valueToFind)){
//                        System.out.println(row.getText());
//                        return row.getText();
//                    }
//                }
//            }
            Optional<WebElement> optionalRow=rows.stream().
                    filter(row->row.findElements(By.tagName("td")).
                            stream().allMatch(cell->cell.getText().equals(valueToFind))).findFirst();
            return optionalRow.map(WebElement::getText).orElse(null);
        }finally {
            {driver.quit();
            }


        }
    }


}
