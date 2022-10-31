package com.wiley.cfireader.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extentReport;

    private ExtentReportManager(){}

    public static ExtentReports getInstance() throws Exception {
        if(extentReport==null){
            return  createReportInstance();
        }else{
            return extentReport;
        }

    }


    public static ExtentReports createReportInstance() throws Exception{

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/test-out/ExtentReport.html");

        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setCss("css-string");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setJs("js-string");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().setProtocol(Protocol.HTTPS);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setReportName("Wiley-Etext-CFI-Automation-Report");
        htmlReporter.config().setOfflineMode(true);
        htmlReporter.config().setDocumentTitle("Wiley-Etext");
        htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
 
        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);

        return extentReport;

    }
}



