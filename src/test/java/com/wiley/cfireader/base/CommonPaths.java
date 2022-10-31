package com.wiley.cfireader.base;

import com.wiley.cfireader.books.Regions;
import com.wiley.cfireader.books.WorldRegionalGeography;
import com.wiley.cfireader.books.WorldToday;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.HTML;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CommonPaths {

    public boolean sectionName(WebDriver driver, String bookName, String excelSectionName, int index, String cfiSuffix) throws Exception{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wiley-ereader-frame")));
        String actualSectionName = "(//h2)[num]";
        String elementText;
        WebElement SectionElement;
        try {
            Thread.sleep(3000);
            SectionElement = driver.findElement(By.xpath(actualSectionName.replace("num",String.valueOf(index))));
            wait.until(ExpectedConditions.visibilityOf(SectionElement));
            elementText = SectionElement.getText();
        }catch (org.openqa.selenium.NoSuchElementException e){
            Thread.sleep(3000);
            SectionElement = driver.findElement(By.xpath((actualSectionName.replace("num",String.valueOf(index))).replace("h2", "h2/span")));
            wait.until(ExpectedConditions.visibilityOf(SectionElement));
            elementText = SectionElement.getText();
        }
      //  System.out.println("Actual section name for the section number is :"+elementText);
        excelSectionName = returnSectionName(driver, bookName, excelSectionName);

        if(excelSectionName.contains("'")){
            excelSectionName = excelSectionName.replace("'", "’");
        }
        String xpathDisplay ="//h2[text()='"+excelSectionName+"']";

        WebElement section = null;
        boolean isVisible = false;
        try {
            section = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathDisplay))));
            isVisible = isVisibleInViewport(driver.findElement(By.xpath(xpathDisplay)));

            String element = sampleJs(driver.findElement(By.xpath(xpathDisplay)));
            String sectionNameT = getTextFromHtmlString(element);
            System.out.println("=====================++++++++++++++++:"+sectionNameT);
        }catch (org.openqa.selenium.NoSuchElementException e){
            try {
                xpathDisplay = xpathDisplay.replace("h2", "h2/span");
                section = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathDisplay))));
                isVisible = isVisibleInViewport(driver.findElement(By.xpath(xpathDisplay)));

                String element = sampleJs(driver.findElement(By.xpath(xpathDisplay)));
                String sectionNameT = getTextFromHtmlString(element);
                System.out.println("=====================++++++++++++++++:"+sectionNameT);
            }catch (org.openqa.selenium.NoSuchElementException ex){
                try{
                    xpathDisplay = xpathDisplay.replace("h2/span", "h3");
                    section = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathDisplay))));
                    isVisible = isVisibleInViewport(driver.findElement(By.xpath(xpathDisplay)));

                    String element = sampleJs(driver.findElement(By.xpath(xpathDisplay)));
                    String sectionNameT = getTextFromHtmlString(element);
                    System.out.println("=====================++++++++++++++++:"+sectionNameT);
                }catch (org.openqa.selenium.NoSuchElementException ext){
                    try {
                        xpathDisplay = getXpathWithDoubleQuotes(excelSectionName);
                        section = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathDisplay))));
                        isVisible = isVisibleInViewport(driver.findElement(By.xpath(xpathDisplay)));
                        String element = sampleJs(driver.findElement(By.xpath(xpathDisplay)));
                        String sectionNameT = getTextFromHtmlString(element);
                        System.out.println("=====================++++++++++++++++:"+sectionNameT);
                    }catch (org.openqa.selenium.NoSuchElementException extq) {
                        System.out.println("Exception for the sectionName :" + excelSectionName + ": CFI Link :" + cfiSuffix);
                        ScreenShot.takeSnapShot(driver, excelSectionName+"_"+cfiSuffix);
                    }
                }
            }
        }
        //boolean isDisplay = driver.findElement(By.xpath(xpathDisplay)).isDisplayed();
        if(section!= null && isVisible && excelSectionName.contains(elementText)){
            System.out.println("PASS:"+excelSectionName);
            ScreenShot.takeSnapShot(driver, excelSectionName+"_"+cfiSuffix);
            return true;
        } else if (section!= null && isVisible && !excelSectionName.contains(elementText)){
            System.out.println("PASS:"+excelSectionName);
            ScreenShot.takeSnapShot(driver, excelSectionName+"_"+cfiSuffix);
            //    System.out.println("CFI Link works fine but section number is incorrect for :"+excelSectionName+ ": ");
            return true;
        } else {
            System.out.println("=====================This is Failed :"+excelSectionName+ ": CFI Link:"+cfiSuffix);
            ScreenShot.takeSnapShot(driver, excelSectionName+"_"+cfiSuffix);
            return false;
        }

    }

    public static Boolean isVisibleInViewport(WebElement element) {
        WebDriver driver = ((RemoteWebElement)element).getWrappedDriver();

        //String js;
      //  JavascriptExecutor exec = (JavascriptExecutor) driver;
        //js = String.format("",element.getText());
      //  return false;
        return (Boolean)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "console.log(cx);                " +
                        "console.log(cy);                " +
                        "console.log(e.innerHTML);                " +
                        "console.log('rajith tryyyy....');        " +
                        "console.log(e.innerHTML.innerText);      " +
                        "console.log('rajith console....');       " +
                        "for (; e; e = e.parentNode) {            " +
                        "console.log('rajith test');              " +
                        "console.log(e.childNodes.length);                 " +
                        "console.log('rajith test222');              " +
                        "console.log(e.nodeName);                 " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }

    private String sampleJs(WebElement element){
        WebDriver driver = ((RemoteWebElement)element).getWrappedDriver();

        return (String)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "  return e.innerHTML;                "
                , element);
    }
    private String returnSectionName(WebDriver driver, String bookName, String sectionName){
      switch (bookName){
          case "Nijman, The World Today, 8e":
              WorldToday worldToday = new WorldToday();
              return worldToday.getActualSectionName(sectionName);
          case "Nijman, Regions, 18e":
              Regions regions = new Regions();
              return regions.getActualSectionName(sectionName);
          case "Fouberg, Understanding World Regional Geography, 2e":
              WorldRegionalGeography worldRegionalGeography = new WorldRegionalGeography();
              return worldRegionalGeography.getActualSectionName(sectionName);
      }
      return sectionName;
    }

    public String getSectionNamByXPath(WebDriver driver, String sectionNum){
        String sectionName="";
        try{
            sectionName = driver.findElement(By.xpath("(//h2)[num]".replace("num", sectionNum))).getText();
        }catch (org.openqa.selenium.NoSuchElementException ext){

        }
        return sectionName;
    }

    public String getXpathWithDoubleQuotes(String sectionName){
        try{
            if (sectionName.equals("What Are the Caribbean’s Unique Development Challenges and Opportunities?")) {
               sectionName = sectionName.replace("’", "'");
                return "//h2[text()=\"" + sectionName + "\"]";
            } else if (sectionName.equals("What Is South Asia’s Role in the World Economy?")) {
                return "//h2[text()=\"What is South Asia's Role in the World Economy?\"]";
            }else if (sectionName.startsWith("What Regions Are Used in ")) {
                return "//h2[text()='What Regions Are Used in ' ]"; ///i[text()='Understanding World Regional Geography?']
            }
        }catch (org.openqa.selenium.NoSuchElementException ext){

        }
        return sectionName;
    }

    private String getTextFromHtmlString(String stringWithHtml){
        return Jsoup.parse(stringWithHtml).text();
    }
}
