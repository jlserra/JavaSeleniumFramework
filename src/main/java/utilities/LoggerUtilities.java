package utilities;

import org.apache.log4j.Logger;

public class LoggerUtilities {

    static Logger log = Logger.getLogger("test");

    public void startTestCase(String TestCaseName){

        log.info("****************************************************************************************");

        log.info("****************************************************************************************");

        log.info(""+ TestCaseName +"");

        log.info("****************************************************************************************");

        log.info("****************************************************************************************");

    }

    public void endTestCase(String TestCaseName){

        log.info("****************************************************************************************");

        log.info("****************************************************************************************");

        log.info(""+ TestCaseName +"");

        log.info("****************************************************************************************");

        log.info("****************************************************************************************");

    }

    public void info(String message) {

        log.info(message);

    }

    public  void warn(String message) {

        log.warn(message);

    }

    public  void error(String message) {

        log.error(message);

    }

    public void fatal(String message) {

        log.fatal(message);

    }

    public void debug(String message) {

        log.debug(message);
    }

}


