package com.wiley.cfireader.runner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.wiley.cfireader.base.BookEntitlementRunner;
import com.wiley.cfireader.fileio.JSONReader;
import com.wiley.cfireader.utils.ExtentReportManager;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PerfUserSetTestRunner {

    public static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();


    @BeforeSuite
    public synchronized void setup() throws Exception {
        extent = ExtentReportManager.getInstance();
    }


    @Test(dataProvider = "username")
    public synchronized void addingTitlesToPerfUsers(String username) throws Exception{

        // Read configurations
        JSONObject configObj = JSONReader.readConfigFile();

        String appUrl = configObj.get("browserUrl").toString();
        ExtentTest testClassObj  = extent.createTest("Test "+ username);
        parentTest.set(testClassObj);


        ExtentTest test  = parentTest.get().createNode("CFI links Verification On :" +username);

        // pass app url and excel file data to CFI link test
        BookEntitlementRunner webRunner = new BookEntitlementRunner();
        webRunner.webRunner(username);

    }

    @AfterSuite
    public synchronized void tearDown() throws Exception {
        BookEntitlementRunner.driver.quit();
        extent.flush();
    }

    @DataProvider(name = "username")
    public Object[][] ReadfromCSV() throws Exception {

        int count =0;
        String[] data= null;
        String returnObj[][] = null;

        //System.out.println(System.getProperty("user.dir"));
        String csvFile = System.getProperty("user.dir")+ "/src/test/resources/csv/email.csv";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> content = new ArrayList<String>();

        try {

            //this loop is pseudo code
            br = new BufferedReader(new FileReader(csvFile));
            int datalength = 0;
            int listsize =0;;

            while ((line = br.readLine()) != null) {
                // use comma as separator

                content.add(line);
            }
            System.out.println(content);

            listsize = content.size();
            datalength = content.get(0).split(cvsSplitBy).length;
            returnObj = new String[listsize][datalength];

            for (int i = 0; i<listsize; i++) {

                data = content.get(i).split(cvsSplitBy);
                for (int j=0; j< datalength ; j++) {
                    returnObj[i][j] = data[j];

                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Done");
        return returnObj;

    }

}
