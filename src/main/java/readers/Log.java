package readers;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Log {

    public static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void testFailed(String message) {
        logger().error("TEST FAILED: " + message);
        logStep("TEST FAILED: " + message);
    }

    public static void testPassed(String message) {
        logger().info("TEST PASSED: " + message);
        logStep("TEST PASSED: " + message);
    }


    public static void info(String ... message){

        logger().info(String.join(" " , message));
    }
    public static void error(String ... message){
        logger().error(String.join(" " , message));
    }
    public static void warn(String ... message){
        logger().warn(String.join(" " , message));
    }
    public static void debug(String ... message){
        logger().debug(String.join(" " , message));
    }

    private static void logStep(String message) {
        try {
            if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
                Allure.step(message);
            }
        } catch (Exception ignored) {
            // Prevent errors when logging outside of a test context
        }
    }


}
