package utilities;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggerUtilities {

    static Logger log = Logger.getLogger("test");

    public void startTestCase(String TestCaseName) {
        log.info("****************************************************************************************");
        log.info("START - " + TestCaseName + "");
        log.info("****************************************************************************************");
    }

    public void endTestCase(String TestCaseName) {
        log.info("****************************************************************************************");
        log.info("END - " + TestCaseName + "");
        log.info("****************************************************************************************");
    }

    public void startSuite() {
        log.info("***********************             -  SUITE START -            ************************");
    }

    public void endSuite() {
        log.info("***********************             -  SUITE END -             ***********************");
    }


    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void error(String message) {
        log.error(message);
    }

    public void error(Exception e, String message) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        error(message + ": " +errors.toString());
    }

    public void fatal(String message) {
        log.fatal(message);
    }

    public void debug(String message) {
        log.debug(message);
    }


}


