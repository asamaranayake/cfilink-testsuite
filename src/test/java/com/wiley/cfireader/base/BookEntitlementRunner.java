package com.wiley.cfireader.base;

import com.wiley.cfireader.fileio.CsvUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookEntitlementRunner {

    public static WebDriver driver;


    public void webRunner(String username) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        String[] courses = {"B74546", "B74544", "B74543","B74545"};
        String Results = "Results Not Append";
        CsvUtils.writeDataToCSVFiles(username,Results, true);
         for (String course : courses) {

             System.out.println(username+" >>>>> course "+ course +" Entitlement is Started >>>>>>");
             ChromeOptions option = new ChromeOptions();
             //option.setHeadless(true);
             option.addArguments("--incognito");
             option.addArguments("disable-infobars");
             driver = new ChromeDriver(option);
             driver.manage().window().maximize();
             driver.manage().deleteAllCookies();

             driver.get("https://education-qa.wiley.com/ngonboard/index.html#/Login");


             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpng-login-email-field")));
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpng-login-password-field")));

             //Login to Wiley Plus ng-board
             driver.findElement(By.id("wpng-login-email-field")).click();
             driver.findElement(By.id("wpng-login-email-field")).sendKeys(username);
             driver.findElement(By.id("wpng-login-password-field")).click();
             driver.findElement(By.id("wpng-login-password-field")).sendKeys("Welcome@123");
             driver.findElement(By.id("wpng-login-submit-button")).click();

             Thread.sleep(10000);
             //Check Popup
             try {
                 driver.findElement(By.xpath("//button[text()='Got it']")).click();
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }

             //Click Add Course button
             try {
                 driver.findElement(By.xpath("//*[@data-testid='add-course-button']")).click();
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }
             Thread.sleep(3000);
             driver.findElement(By.xpath("//*[@data-testid='course-id-finder__input']")).click();
             Thread.sleep(3000);
             driver.findElement(By.xpath("//*[@data-testid='course-id-finder__input']")).sendKeys(course);
             Thread.sleep(3000);
             driver.findElement(By.xpath("//*[@data-testid='course-id-finder__input']")).click();
             Thread.sleep(3000);
             driver.findElement(By.xpath("//*[@id='wpng-find-course-by-id-page-find-course-button']")).click();
             Thread.sleep(6000);

            /* wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpng-find-course-by-id-page-dont-have-button")));
             driver.findElement(By.id("wpng-find-course-by-id-page-dont-have-button")).click();
             Thread.sleep(3000);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpng-locate-course-page-change-school-link")));
             driver.findElement(By.id("wpng-locate-course-page-change-school-link")).click();
             Thread.sleep(3000);
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpng-change-school-page-enter-school-typeahead")));
             driver.findElement(By.id("wpng-change-school-page-enter-school-typeahead")).click();
             driver.findElement(By.id("wpng-change-school-page-enter-school-typeahead")).sendKeys("WILEY ADMIN TEST FICE");
             driver.findElement(By.id("wpng-change-school-page-enter-school-typeahead")).click();
             Thread.sleep(3000);
             System.out.println(driver.findElement(By.xpath("//mark")).getText());
             driver.findElement(By.xpath("//mark")).click();
             Thread.sleep(1000);
             driver.findElement(By.id("wpng-change-school-page-select-button")).click();
             Thread.sleep(3000);

             driver.findElement(By.xpath("//*[@id='wpng-locate-course-page-instructor-name-radio-button']//following-sibling::span")).click();

             driver.findElement(By.xpath("//*[@id='wpng-locate-course-page-next-button']")).click();
             Thread.sleep(5000);

             driver.findElement(By.id("wpng-find-course-by-instructor-page-enter-instructor-name-typeahead")).click();
             driver.findElement(By.id("wpng-find-course-by-instructor-page-enter-instructor-name-typeahead")).sendKeys("PerfInst Last");
             Thread.sleep(5000);
             System.out.println(driver.findElement(By.xpath("//span[contains(text(),'PerfInst Last')]")).getText());
             driver.findElement(By.xpath("//span[contains(text(),'PerfInst Last')]")).click();
             Thread.sleep(1000);
             driver.findElement(By.xpath("//*[@id='wpng-find-course-by-instructor-page-find-instructor-button']")).click();
             Thread.sleep(5000);

             // Adding Statistics Course
             System.out.println("//*[@data-course-id='" + course + "']//button");
             driver.findElement(By.xpath("//*[@data-course-id='" + course + "']//button")).click();
             Thread.sleep(1000);
             */

             System.out.println(course + " Clicked >>>>> ");
             driver.findElement(By.id("wpng-course-details-page-next-button")).click();
             Thread.sleep(6000);
             driver.findElement(By.xpath("//*[@data-testid='register-course-free-trial-button']")).click();
             Thread.sleep(6000);

             System.out.println("Register button Clicked >>>>> ");

             driver.findElement(By.xpath("//button[text()='Access My Course Now!']")).click();
             Thread.sleep(9000);
             System.out.println("Access My Course Now! button Clicked >>>>> ");
             String windoHn = driver.getWindowHandle();
             driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div/nav/ul/li[2]/a")).click();
             Thread.sleep(6000);
             driver.switchTo().window(windoHn);
             Thread.sleep(6000);
             driver.findElement(By.xpath("//*[@id='header']/div[1]/div/a")).click();
             Thread.sleep(6000);

             try {
                 driver.findElement(By.xpath("//button[text()='Got it']")).click();
             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }

             Results = username + " Successfully entitlement for >>  " + course;
             System.out.println(Results);
             CsvUtils.writeDataToCSVFiles(username,Results, true);
             driver.quit();
             System.out.println(username+" >>>>> course "+ course +" Entitlement is End >>>>>>");
             ////*[@data-testid='add-course-button']
         }
    }

}


