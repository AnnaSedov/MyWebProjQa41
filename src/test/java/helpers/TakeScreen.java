package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static config.BaseTest.getDriver;

public class TakeScreen {
    @Attachment(value = "Failure screenshot",type="image/png")
    public static byte[]takeScreenshot(String testName){
       // return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            String screenShotName=testName+"_"+System.currentTimeMillis()+".png";
            File screenShotFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile,new File("screenshots/"+screenShotName));
            return Files.readAllBytes(Paths.get("screenshots\\"+screenShotName));
        }catch (IOException e){
            return null;
        }
    }

    
}
