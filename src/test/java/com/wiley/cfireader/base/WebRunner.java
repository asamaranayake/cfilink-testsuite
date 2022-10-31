package com.wiley.cfireader.base;

import com.aventstack.extentreports.ExtentTest;
import com.wiley.cfireader.fileio.CsvUtils;
import com.wiley.cfireader.pojo.ExcelBook;
import com.wiley.cfireader.pojo.ExcelSheet;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.wiley.cfireader.base.ScreenShot.takeScreenshot;

public class WebRunner {
    public static WebDriver driver;

    public void webRunner(String username, String password, String appUrl, ExcelBook excelBook , ExtentTest scenarioNodes) throws Exception{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
      //  ((HasAuthentication) driver).register(UsernameAndPassword.of("0lympics", "GregLouganis1984"));
        driver.get(appUrl);
        driver.manage().window().maximize();
      //  Runtime.getRuntime().exec("C:\\zone\\autoItScripts\\authEreader.exe");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        //call login
        userLogin(username, password);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        try {
            Thread.sleep(3000);
        }catch (Exception ex){}
        driver.navigate().to(excelBook.getUrlPrefix()+"/page/0/section/top-of-page");
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wiley-ereader-frame")));
        WebElement coverPage;
        if(excelBook.getBookName().equals("Young, College Algebra & Trigonometry, 4e")){
            coverPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='cover image']")));
        }else {
            coverPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("coverstart")));
        }

        ExtentTest test001 = scenarioNodes.createNode("Cover Image Loading Verification");
        if(coverPage.isDisplayed()){
            cfiLinksTryOuts(driver, excelBook , scenarioNodes);
            test001.pass("Cover Image is loaded");
            test001.addScreenCaptureFromPath(takeScreenshot(driver,excelBook.getBookName()));
        }else{
            test001.fail("Cover Image is Not loaded");
            test001.addScreenCaptureFromPath(takeScreenshot(driver,excelBook.getBookName()));
        }
    }

    private void userLogin(String username, String password ){
        By emailPath = By.id("email");
        By passwordPath = By.id("filled-adornment-password");
        By submit = By.xpath("//span[text()='Log in']");

        driver.findElement(emailPath).sendKeys(username);
        driver.findElement(passwordPath).sendKeys(password);
        driver.findElement(submit).click();
    }

    private void cfiLinksTryOuts(WebDriver driver, ExcelBook excelBook, ExtentTest scenarioNodes) throws Exception{

        CsvUtils.writeDataToCSVFile(excelBook.getBookName(), null, false);
        for (ExcelSheet sheet: excelBook.getExcelSheetDataList()) {
            // prepare link as per the CFI link format
            // CFI sample link format

            driver.navigate().to(excelBook.getUrlPrefix()+"/epubcfi/"+sheet.getCfiEntryPoint()+"@0:0.00");
            ExtentTest test002 = scenarioNodes.createNode(excelBook.getUrlPrefix()+"/epubcfi/"+sheet.getCfiEntryPoint()+"@0:0.00"+ " __ CFI Link Verification ");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
            SectionNameChecker commonPaths = new SectionNameChecker();
            commonPaths.isSectionNameMatching(driver,  excelBook, sheet , test002);
        }
    }
}
