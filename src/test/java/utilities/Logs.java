package utilities;

import org.apache.log4j.Logger;

public class Logs {  private static Logger Log = Logger.getLogger(Logs.class.getName());//

    public  void startTestCase(String sTestCaseName){

        Log.info("---------------------------------------------------------------------------------------");
        Log.info("***********************        "+sTestCaseName+ " START    **************************");
        Log.info("---------------------------------------------------------------------------------------");


    }

    public  void endTestCase(String sTestCaseName){
        Log.info("---------------------------------------------------------------------------------------");
        Log.info("**************************        "+sTestCaseName+ " END    **************************");
        Log.info("---------------------------------------------------------------------------------------");

    }

    public  void info(String message) {

        Log.info(message);

    }

    public  void warn(String message) {

        Log.warn(message);

    }

    public  void error(String message) {

        Log.error(message);

    }

    public static void fatal(String message) {

        Log.fatal(message);

    }

    public static void debug(String message) {

        Log.debug(message);

    }

}
