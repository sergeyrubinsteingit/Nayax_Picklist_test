package ProgramFiles.TestUsedClasses;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.xml.sax.SAXException;

import java.util.regex.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import ProgramFiles.GloballyUsedClasses.DrawBorderAroundElement;
import ProgramFiles.GloballyUsedClasses.MoveElementIntoViewport;
import ProgramFiles.GloballyUsedClasses.NotificationAlertChecking;
import ProgramFiles.GloballyUsedClasses.OpenSelectedScreen;
import ProgramFiles.GloballyUsedClasses.RunTestNG;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;

public class AgreementFormTest {

	private static String[] agreementFormElements = { "agreement_list", "agreement_1", "continue_btn" };

	private static String[] agreementFormSelectors = { "//*[@id='agreement_list']/div[2]", "//*[@id='agreement_1']", "//*[@id='continue_btn']" };

	private static Actions Actions_ = new Actions(WebDriverConfiguration.webDriver);

	
	public static void waitForAgreementFormPresence()
			throws InterruptedException, ParserConfigurationException, SAXException, IOException, TransformerException {

		System.out.println(" <<<<<<<<<<<<<<<< AGREEMENT FORM TEST >>>>>>>>>>>>>>>> ");

		// For a screenshot title:
		OpenSelectedScreen.menuEntryName = "Agreement form";

		// Zoom in till the Continue button is visible
		int viewportHeight = WebDriverConfiguration.webDriver.manage().window().getSize().getHeight();

		WebElement continueButton = WebDriverConfiguration.webDriver.findElement(By.id(agreementFormElements[2]));

		// Moving an element into viewport to screenshot and other
		ProgramFiles.GloballyUsedClasses.MoveElementIntoViewport.MoveIntoViewport(continueButton);

		for (int i = 0; i < agreementFormElements.length; i++) {

			try {

				System.out.println("\n<<<<<<<<<<< wait For Agreement Form Presence >>>>>>>>>>>>\n");

				// Waits for the form elements to appear
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition
						.PresenceOfElementLocatedById(agreementFormElements[i], 120);
				WaitAndNotify.StartWait("Wait For Agreement Form Presence > Waits for the form elements to appear");

				// testing the form
				if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getSize() != null) {

					// Search element for special characters in the text
					Pattern pattern_ = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]", Pattern.CASE_INSENSITIVE);

					Matcher matcher_ = pattern_.matcher(
							ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getText());

					boolean foundByMatching = matcher_.find();

					// moves selected element into a viewport
					MoveElementIntoViewport.MoveIntoViewport(
							ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
					WaitAndNotify.StartWait("Agreement form > MoveIntoViewport");

					// flag for Extent Report evaluation
					com.Start.TestProcedure.elementStateForTestReports = foundByMatching ? false : true;

					// border around element
					DrawBorderAroundElement.DrawBorder(
							ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement,
							com.Start.TestProcedure.elementStateForTestReports);
					WaitAndNotify.StartWait("Agreement form 1");
					
					// Screenshot
					ProgramFiles.GloballyUsedClasses.ScreenShotTaker.ScreenShots(agreementFormElements[i],
							agreementFormElements[i], agreementFormElements[i], agreementFormSelectors[i],
							com.Start.TestProcedure.elementStateForTestReports);
					WaitAndNotify.StartWait("Agreement form > screenshot");

					// Run TEST NG for the record in Extent Report
					com.Start.TestProcedure.screenName = "Agreement form: " + agreementFormElements[i];
					RunTestNG.TestNgRun();
					WaitAndNotify.StartWait("MACHINES: RunTestNG");				

					if (i > 0) {

						TimeUnit.MILLISECONDS.sleep((int) Math
								.round(ProgramFiles.GloballyUsedClasses.NetTrafficControl.rateToInterval * 0.05));

						// Clicking every clickable element
						Actions_.moveToElement(
								ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click()
								.perform();

					} // if

				} else {

					System.out.println("\n<<<<<<<<<<< Cannot find element by a selector " + agreementFormElements[i]
							+ ". Shut down. >>>>>>>>>>>>\n");
					WebDriverConfiguration.webDriver.quit();
					System.exit(-1);

				} // if

			} catch (Exception e) {

				System.out.println("\n<<<<<<<<<<< Use pohano in Agreement Form. Shut down. >>>>>>>>>>>>\n");
				e.printStackTrace();
				WebDriverConfiguration.webDriver.quit();
				System.exit(-1);

			} // try

		} // for
		
		// Checking for a notification alert presence
		NotificationAlertChecking.NotificationAlertCheck();
		WaitAndNotify.StartWait("Agreement form => Notification Alert Check");

		// Clicking I Agree button in the bottom bar
		AgreeButtonInBottomBar();
		WaitAndNotify.StartWait("Agreement form => Agree Button In Bottom Bar");

		// Checking a model DCS/Cortex
		ProgramFiles.GloballyUsedClasses.IsCortexCheck.filterBarColor();
		WaitAndNotify.StartWait("Agreement form => Calling Filter bar color");

		// releases
		WaitAndNotify.NotifyAll("Agrrement form finished");

	} // waitTillAgreementFormPresence

	
	// Closes agree to have cookies bar in the bottom of page
	private static WebElement AgreeButtonInBottomBar() {

		System.out.println("<<<<<<<<<<<<<<<< AGREE BUTTON IN BOTTOM BAR >>>>>>>>>>>>>>>>");

		try {

			// Zooming in the view
			ProgramFiles.GloballyUsedClasses.ZoomOutPage.ZoomPage(4, false);

			WaitAndNotify.Notify("Agreement form > Agree Button In Bottom Bar");

		} catch (Exception e) {

			System.out.println(" <<<<<<<<<< Agree button in bottom bar wasn't found >>>>>>>>>> ");

		}

		return ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement;
	}

}// class




//private static void MakeScreenShot(String className_, String cssSelector_) {
//
//	try {
//
//		TimeUnit.SECONDS
//				.sleep((int) Math.round(ProgramFiles.GloballyUsedClasses.NetTrafficControl.rateToInterval * 0.25));
//
//		// Screenshot
//		ProgramFiles.GloballyUsedClasses.ScreenShotTaker.ScreenShots(className_, className_, className_,
//				cssSelector_, com.Start.TestProcedure.elementStateForTestReports);
//		WaitAndNotify.StartWait("Agreement form 2");
//
//	} catch (InterruptedException e) {
//
//		System.out.println("\n<<<<<<<<<<< Screen shot failure. >>>>>>>>>>>>\n");
//		e.printStackTrace();
//
//	}
//	;// try
//
//	WaitAndNotify.Notify("Agreement form > MakeScreenShot > end");
//
//}// MakeScreenShot
