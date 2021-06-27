package com.agoda.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" },
glue = {"com.agoda.stepDef" },
plugin={"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
tags = "@positive or @negative", // based on tags scenarios will run
monochrome = true, 
dryRun = false)



public class MyTestRunner {
	
	
//	@BeforeClass
//    public static void setup() {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//        extentProperties.setReportPath("Reports\\cucumber-reports\\extentReport_"+timeStamp.replace(":","_").replace(".","_")+".html");
//    }
//
//
//    @AfterClass
//    public static void writeExtentReport() {
//    	Properties props = ConfigFileReader.getPropertyFile();
//        Reporter.loadXMLConfig(new File(".\\"+props.getProperty("extentPath")+"\\extent-config.xml"));
//        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
//        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
//      }
}
