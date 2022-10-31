package com.wiley.cfireader.base;

import com.aventstack.extentreports.ExtentTest;
import com.wiley.cfireader.books.*;
import com.wiley.cfireader.fileio.CsvUtils;
import com.wiley.cfireader.pojo.ExcelBook;
import com.wiley.cfireader.pojo.ExcelSheet;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.wiley.cfireader.base.ScreenShot.takeScreenshot;

public class SectionNameChecker {
    static List<String> resultsList= new ArrayList<>();
    public void isSectionNameMatching(WebDriver driver, ExcelBook book, ExcelSheet sheet, ExtentTest scenarioNodes) throws Exception {
        String bookName = book.getBookName();
        String sectionId = sheet.getSectionPrefix();
        String excelSectionName;
        String cfiSuffix = sheet.getCfiEntryPoint();

        if(book.isSectionNum()){
             excelSectionName = sectionId+" "+sheet.getSectionName();
        } else {
             excelSectionName = sheet.getSectionName();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().defaultContent();
        try{
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wiley-ereader-frame")));
        }catch (org.openqa.selenium.TimeoutException ex){
            System.out.println("Time out");
            scenarioNodes.fail("Time out");
            scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,"TimeoutException"));
        } catch (org.openqa.selenium.NoSuchElementException ns){
            System.out.println("Element not found");
            scenarioNodes.fail("Element not found");
            scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,"Element_not_found "));
        }
        String xpathDisplay;
        String sectionText="";
        boolean isVisible;
        xpathDisplay = getInitialPath(bookName, excelSectionName);
        System.out.println();
        System.out.println();
        System.out.println("Looking for xpath:"+ xpathDisplay);

        WebElement element = null;
        try {
            Thread.sleep(3000);
            element = driver.findElement(By.xpath(xpathDisplay));
            wait.until(ExpectedConditions.visibilityOf(element));
            sectionText=excelSectionName;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            try {
                excelSectionName = returnMappedSectionName(bookName, excelSectionName);
               if(excelSectionName.startsWith("//") && excelSectionName.contains("___")){
                   xpathDisplay= excelSectionName.split("___")[0];
                   sectionText= excelSectionName.split("___")[1];
               } else {
                   xpathDisplay = getInitialPath(bookName, excelSectionName);
                   sectionText=excelSectionName;
               }
                element = driver.findElement(By.xpath(xpathDisplay));
                wait.until(ExpectedConditions.visibilityOf(element));
            } catch (org.openqa.selenium.NoSuchElementException ex) {
                System.out.println("Section xpath miss match");
                scenarioNodes.warning("Section xpath miss match");
                scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,"Section xpath miss match"));
            }
        }
        isVisible = validateSectionNameWithExcel( driver, element, sectionText , scenarioNodes);
        String result = sectionId +","+ excelSectionName+","+cfiSuffix;
        if(isVisible){
            CsvUtils.writeDataToCSVFile(bookName, result+",PASS", true);
            scenarioNodes.pass(result+", >> PASS");
            scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,bookName +"_"+ excelSectionName));
            System.out.println("PASS :"+excelSectionName);
        } else {
            CsvUtils.writeDataToCSVFile(bookName, result+",FAILED", true);
            scenarioNodes.fail(result+", >> FAILED");
            scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,bookName +"_"+ excelSectionName));
            System.out.println("FAILED ==========:"+excelSectionName+" CFI_LINK "+cfiSuffix);
        }
    }

    private String getInitialPath(String bookName, String excelSectionName){
        switch (bookName){
            case "Huffman, Psychology in Action, 12e":
                return "//h2/b[text()='"+excelSectionName+"']";
            default:
                return "//h2[text()='"+excelSectionName+"']";
        }
    }

    private boolean validateSectionNameWithExcel(WebDriver driver ,WebElement element, String excelSectionName , ExtentTest scenarioNodes ) {
        String htmlAsText = getViewPortHtmlAsText(element);
        String viewPortSectionName = getTextFromHtmlString(htmlAsText);
        System.out.println("-----------:excelSectionName.trim():"+excelSectionName.trim()+":");
        System.out.println("---------------------:viewPortSectionName.trim():"+viewPortSectionName.trim()+":");
        if (excelSectionName.trim().equals(viewPortSectionName.trim())) {
            return true;
        }else{
            scenarioNodes.fail("Book Section Name With Excel Is failed");
            scenarioNodes.addScreenCaptureFromPath(takeScreenshot(driver,htmlAsText+" With Excel Is failed"));
            return false;
        }

    }

//    private String getXpathTag(String bookName, String sectionName) {
//        String tagMapper = bookName + "__" + sectionName;
//        switch (tagMapper) {
//            case "Nijman, The World Today, 8e__":
//                return "//h3";
//            default:
//                return "//h2/span[text()='"+sectionName+"']";
//        }
//    }

    private String getViewPortHtmlAsText(WebElement element) {
        try {
            WebDriver driver = ((RemoteWebElement) element).getWrappedDriver();
            return (String) ((JavascriptExecutor) driver).executeScript(
                    "var elem = arguments[0],                 " +
                            "  box = elem.getBoundingClientRect(),    " +
                            "  cx = box.left + box.width / 2,         " +
                            "  cy = box.top + box.height / 2,         " +
                            "  e = document.elementFromPoint(cx, cy); " +
                            "console.log(cx);                " +
                            "console.log(cy);                " +
                            "  return e.innerHTML;                "
                    , element);
        }catch (NullPointerException ex){
            System.out.println("return Null");
            return "No Section found";
        } catch (org.openqa.selenium.JavascriptException js){
            System.out.println("return JavascriptException");
            return "No Section found";
        }
    }

    private String getTextFromHtmlString(String stringWithHtml) {
        return Jsoup.parse(stringWithHtml).text();
    }

    private String returnMappedSectionName(String bookName, String sectionName) {
        switch (bookName) {
            case "Nijman, The World Today, 8e":
                WorldToday worldToday = new WorldToday();
                return worldToday.getActualSectionName(sectionName);
            case "Nijman, Regions, 18e":
                Regions regions = new Regions();
                return regions.getActualSectionName(sectionName);
            case "Fouberg, Understanding World Regional Geography, 2e":
                WorldRegionalGeography worldRegionalGeography = new WorldRegionalGeography();
                return worldRegionalGeography.getActualSectionName(sectionName);
            case "Tortora, Principles of Anatomy and Physiology, 16e":
                AnatomyAndPhysiology anatomyAndPhysiology = new AnatomyAndPhysiology();
                return anatomyAndPhysiology.getActualSectionName(sectionName);
            case "Young, College Algebra & Trigonometry, 4e":
                AlgebraAndTrigonometry algebraAndTrigonometry = new AlgebraAndTrigonometry();
                return algebraAndTrigonometry.getActualSectionName(sectionName);
            case "Lock, Statistics, 3e":
                LockStatistics lockStatistics = new LockStatistics();
                return lockStatistics.getActualSectionName(sectionName);
            case "Hughes-Hallett, Calculus, 8e":
                HallettCalculus hallettCalculus = new HallettCalculus();
                return hallettCalculus.getActualSectionName(sectionName);
            case "Cutnell, Physics, 11e":
                CutnellPhysics cutnellPhysics = new CutnellPhysics();
                return cutnellPhysics.getActualSectionName(sectionName);
            case "Weygandt, Financial Accounting, 11e":
                FinancialAccounting financialAccounting = new FinancialAccounting();
                return financialAccounting.getActualSectionName(sectionName);
            case "Huffman, Psychology in Action, 12e":
                PsychologyInAction psychologyInAction = new PsychologyInAction();
                return psychologyInAction.getActualSectionName(sectionName);
            case "Sanderson, Real World Psychology, 3e":
                RealWorldPhychology realWorldPhychology = new RealWorldPhychology();
                return realWorldPhychology.getActualSectionName(sectionName);
        }
        return sectionName;
    }

}
