package checkpoint.andela.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LogManager {
  private static BlockingQueue logFile;
  private static String action;
  
  public LogManager() {
   logFile = new ArrayBlockingQueue(50);
  }
  
  private  static void writeLog(String logger, String value) throws InterruptedException{
    DateFormat formatter = new SimpleDateFormat("yyyy-mm=-dd HH:mm:ss");
    Date  date = new Date();
    String log = logger + " Thread( " + formatter.format(date) + ")---" + action  +" UNIQUE-ID " + value + " to  "+ destination("logger"); 
    
    logFile.put(log);
  }

  private static String destination(String logger) {
    if(logger.equals("FileParser")){
      action = "wrote";
      return "buffer.";
    }
    action = "collected";
    return "database";
  }

}
