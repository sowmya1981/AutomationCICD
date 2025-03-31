package rahulShettyAcademy.Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() {
		
		
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			// are saved
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation results");
			reporter.config().setDocumentTitle("Test Results");

			// ExtentReports extent = new ExtentReports(); - Added this in global level so
			// that the main test "initialDemo" can access
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Sowmya");
			return extent;
			

		

	}
	}

