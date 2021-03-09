package utilities;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LoggerUtilities {

    public static Logger log = Logger.getLogger("test");
    public static String testlog;

    public void clearLog(){
        testlog = "";
    }

    public void appendLog(String message){
        testlog = testlog + message + "\n";
    }

    public void info(String message) {
        log.info(message);
        appendLog(message);
    }

    public void warn(String message) {
        log.warn(message);
        appendLog(message);
    }

    public void error(String message) {
        log.error(message);
        appendLog(message);
    }

    public void error(Exception e, String message) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        error(message + ": " + errors.toString());
    }

    public void fatal(String message) {
        log.fatal(message);
        appendLog(message);
    }

    public void debug(String message) {
        log.debug(message);
        appendLog(message);
    }


}


