package com.wiley.cfireader.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;

public class ScreenShot {
    static int index = 0;

    public static void takeSnapShot(WebDriver webdriver, String fileName) throws Exception {
        String fileWithPath = "target/test-out/" + fileName + ".jpg";

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file

        scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        //  File DestFile=new File(fileWithPath);

        //Copy file at destination

//        FileUtils.copyFile(SrcFile, DestFile);

    }


    public static String takeScreenshot(WebDriver webdriver ,String testDescription) {

        String FileName = testDescription + index + ".jpg";
        String path = "target/test-out/" + FileName;

        try {
            File e = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(e, new File(path));
        } catch (WebDriverException | IOException var5) {
            var5.printStackTrace();
        }

        String relativePath ="../test-out/"+FileName;
        index++;
        return relativePath;
    }
}
