package com.wiley.cfireader.runner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.wiley.cfireader.base.WebRunner;
import com.wiley.cfireader.fileio.ExcelReader;
import com.wiley.cfireader.fileio.JSONReader;
import com.wiley.cfireader.pojo.ExcelBook;
import com.wiley.cfireader.utils.ExtentReportManager;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRunner {

    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();


    @BeforeSuite
    public synchronized void setup() throws Exception {
        extent = ExtentReportManager.getInstance();
    }


    @Test
    @Parameters("bookname")
    public synchronized void cfiTest(String bookname) throws Exception{


        // Read configurations
        JSONObject configObj = JSONReader.readConfigFile();
        // get excel file path
        String excelPath = configObj.get("excelFilePath").toString()+bookname+".xlsx";
        // get browser url
        String appUrl = configObj.get("browserUrl").toString();
        ExtentTest testClassObj  = extent.createTest("Test "+ bookname);
        parentTest.set(testClassObj);


        ExtentTest test  = parentTest.get().createNode("CFI links Verification On :" +bookname);
        // get username and password
        String username = configObj.get("username").toString();
        String password = configObj.get("password").toString();

        ExcelReader excelReader = new ExcelReader();
        ExcelBook excelBook = excelReader.readeExcelSheet(excelPath, appUrl);
        // pass app url and excel file data to CFI link test
        WebRunner webRunner = new WebRunner();
        webRunner.webRunner(username, password, appUrl, excelBook,test);

    }

    @AfterSuite
    public synchronized void tearDown() throws Exception {
        WebRunner.driver.quit();
        extent.flush();
    }
}
