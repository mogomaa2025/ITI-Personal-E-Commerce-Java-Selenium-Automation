package assertions;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;
import readers.Log;


public class Assertions {

    protected static SoftAssert softAssert = new SoftAssert();
    public static void assertAll(ITestResult result) {
        try {
            softAssert.assertAll();
        }
        catch (AssertionError e)
        {
            Log.error("Assertion failed:"+ e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }
        finally {
            softAssert = new SoftAssert(); // Reset the soft assert instance
        }
    }

    public   Assertions assertFalse(boolean condition ,String message) {
        softAssert.assertFalse(condition, message);
        return this;
    }

    public   Assertions assertEqual(String actual ,String expected,String message) {
        softAssert.assertEquals(actual, expected,message);
        return this;
    }
    //overloading double assertequal
    public   Assertions assertEqual(double actual ,double expected,String message) {
        softAssert.assertEquals(actual, expected,message);
        return this;
    }

    // overloading
    public   Assertions assertEqual(int actual ,int expected,String message) {
        softAssert.assertEquals(actual, expected,message);
        return this;
    }

    public  Assertions assertNotEqual(String actual ,String expected,String message) {
        softAssert.assertNotEquals(actual, expected,message);
        return this;
    }

    public  Assertions assertTrue(boolean condition ,String message) {
        softAssert.assertTrue(condition, message);
        return this;
    }
    //overloading double
    public  Assertions assertTrue(double actual ,double expected,String message) {
        softAssert.assertEquals(actual, expected,message);
        return this;
    }

    public Assertions assertContains(String actual, String expected, String message) {
        softAssert.assertTrue(actual.contains(expected), 
            message + " - Expected: '" + expected + "' to be contained in: '" + actual + "'");
        return this;
    }

    


}
