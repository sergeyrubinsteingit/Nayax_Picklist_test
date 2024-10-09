package ProgramFiles.GloballyUsedClasses;

import java.util.EnumMap;

enum TestData {
	
	ACTOR_QA, ACTOR_PROD, DASHBOARD_OPEREATOR, LOGIN_URL, PASS_QA1, PASS_QA2, PASS_PROD, DASH_OPERATOR_ID, PAYMENT_METHOD, SMS_LOGIN_FIELD, MOMA_LOGIN_FIELD, 
	SIGNIN_BUTTON, NOT_NOW_LINK, START_DATE, END_DATE, PRODUCT_MAP_TABLE;	
	
}

public class TestDataStorage {
	
	public static String ACTOR_QA_;
	public static String ACTOR_PROD_;
	public static String DASHBOARD_OPERATOR_;
	public static String LOGIN_URL_;
	public static String PASS_QA1_;
	public static String PASS_QA2_;
	public static String PASS_PROD_;
	public static String DASH_OPERATOR_ID_;
	public static String PAYMENT_METHOD_;
	public static String SMS_LOGIN_FIELD_;
	public static String MOMA_LOGIN_FIELD_;
	public static String SIGNIN_BUTTON_;
	public static String NOT_NOW_LINK_;
	public static String START_DATE_;
	public static String END_DATE_;
	public static String PRODUCT_MAP_TABLE_;


public static EnumMap<?, String> TestKeywords() {
	
	System.out.println("\n============> TEST KEYWORDS BEGINS <================\n");
	
	EnumMap<TestData, String> testDataValues = new EnumMap<TestData, String>(TestData.class);
	
		testDataValues.put(TestData.ACTOR_QA, "sergey_EatDrinkSleepDie57");
		testDataValues.put(TestData.ACTOR_PROD, "rubiserg_BuDrR1eluoT");
		testDataValues.put(TestData.DASHBOARD_OPEREATOR, "WeWork Haifa");
		testDataValues.put(TestData.LOGIN_URL, "https://qa.nayax.com/core/LoginPage.aspx");  // "https://qa2.nayax.com/core/LoginPage.aspx"
		testDataValues.put(TestData.PASS_QA1, "rubiserg69-qa1*");  // "rubi69nayaxqa-51*"   "rubiserg69-qa1*"
		testDataValues.put(TestData.PASS_QA2, "rubi69QA1******");  // "rubi69nayaxqa-51*"   "rubiserg69-qa1*"
		testDataValues.put(TestData.PASS_PROD, "rubi69production-1*");
		testDataValues.put(TestData.DASH_OPERATOR_ID, "8693701");
		testDataValues.put(TestData.PAYMENT_METHOD, "Prepaid Credit");
		testDataValues.put(TestData.SMS_LOGIN_FIELD, "second_factor_option_sms_input");
		testDataValues.put(TestData.MOMA_LOGIN_FIELD, "second_factor_option_totp_input");
		testDataValues.put(TestData.SIGNIN_BUTTON, "signin");
		testDataValues.put(TestData.NOT_NOW_LINK, "trustDeviceNo");
		testDataValues.put(TestData.START_DATE, "01/01/2023 12:00:00");
		testDataValues.put(TestData.END_DATE, "31/01/2023 12:00:00");
		testDataValues.put(TestData.PRODUCT_MAP_TABLE, "//table[contains(@id, 'machine_products')]");
		
		
		ACTOR_QA_ = testDataValues.get(TestData.ACTOR_QA);
		ACTOR_PROD_ = testDataValues.get(TestData.ACTOR_PROD);
		DASHBOARD_OPERATOR_ = testDataValues.get(TestData.DASHBOARD_OPEREATOR);
		LOGIN_URL_ = testDataValues.get(TestData.LOGIN_URL);
		PASS_QA1_ = testDataValues.get(TestData.PASS_QA1);
		PASS_QA2_ = testDataValues.get(TestData.PASS_QA2);
		PASS_PROD_ = testDataValues.get(TestData.PASS_PROD);
		DASH_OPERATOR_ID_ = testDataValues.get(TestData.DASH_OPERATOR_ID);
		PAYMENT_METHOD_ = testDataValues.get(TestData.PAYMENT_METHOD);
		SMS_LOGIN_FIELD_ = testDataValues.get(TestData.SMS_LOGIN_FIELD);
		MOMA_LOGIN_FIELD_ = testDataValues.get(TestData.MOMA_LOGIN_FIELD);
		SIGNIN_BUTTON_ = testDataValues.get(TestData.SIGNIN_BUTTON);
		NOT_NOW_LINK_ = testDataValues.get(TestData.NOT_NOW_LINK);
		START_DATE_ = testDataValues.get(TestData.START_DATE);
		END_DATE_ = testDataValues.get(TestData.END_DATE);
		PRODUCT_MAP_TABLE_ = testDataValues.get(TestData.PRODUCT_MAP_TABLE);

		System.out.println("\n============> TEST KEYWORDS ENDS <================\n");
		
		return testDataValues;
	
	}//SetEnum

}//TestDataStorage