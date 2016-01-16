package checkpoint.andela.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import checkpoint.andela.parser.OrderedPair;
import checkpoint.andela.parser.Record;


public class DBWriter extends DBManager implements Runnable {
  
  private BlockingQueue<Record> records;

  
  public DBWriter(BlockingQueue<Record> records ) {
    this.records = records;
    
  }
  
  
  @Override
  public void run() {
   
  }
  
  
  public Record getRecord() throws InterruptedException{
    Record record = records.take();
    return record;
  }
  
  public void selectDatabase(String DBName) throws SQLException{
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String useDB = "USE "+DBName;
    try {
      conn = getConnection();
      preparedStatement = conn.prepareStatement(useDB);
      preparedStatement.executeUpdate();
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
    }
  }
  
  
  public void writeToDB() throws InterruptedException, SQLException {
    Record record = getRecord();
    executePreparedStatement(record);
  }


  public void executePreparedStatement(Record record) throws SQLException {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String insertRecord = createQueryString(record);
    try {
      conn = getConnection();
      preparedStatement = conn.prepareStatement(insertRecord);
      preparedStatement.executeUpdate();
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
    }
  
  }


  /**
   * @param record
   * @return
   */
  private String createQueryString(Record record) {
    String field = null;
    String value = null;
    ArrayList<OrderedPair> rec = record.getRecord();
    
    for(OrderedPair pair : rec){
      field += "`pair.getAttribute()`, ";
      value += "'pair.getValue()', ";
    }
    
    field = field.substring(0, field.length() - 1);
    value = value.substring(0, value.length() - 1);
    
    String insertRecord = "INSERT INTO reactiondb.reactions (" + field + " )" + " VALUES (" + value + " )";
    return insertRecord;
  }

  
  
    public static void main(String[] args) throws Exception {
      
      String[] attribute = {
          "UNIQUE-ID",
          "TYPES",
          "COMMON-NAME",
          "ATOM-MAPPINGS",
          "CANNOT-BALANCE?",
          "CITATIONS",
          "COMMENT",
          "COMMENT-INTERNAL",
          "CREDITS",
          "DATA-SOURCE",
          "DBLINKS",
          "DELTAG0",
          "DOCUMENTATION",
          "EC-NUMBER",
          "ENZYMATIC-REACTION",
          "ENZYMES-NOT-USED",
          "EQUILIBRIUM-CONSTANT",
          "HIDE-SLOT?",
          "IN-PATHWAY",
          "INSTANCE-NAME-TEMPLATE",
          "LEFT",
          "MEMBER-SORT-FN",
          "ORPHAN?",
          "PATHOLOGIC-NAME-MATCHER-EVIDENCE",
          "PATHOLOGIC-PWY-EVIDENCE",
          "PHYSIOLOGICALLY-RELEVANT?",
          "PREDECESSORS",
          "PRIMARIES",
          "REACTION-DIRECTION",
          "REACTION-LIST",
          "REGULATED-BY",
          "REQUIREMENTS",
          "RIGHT",
          "RXN-LOCATIONS",
          "SIGNAL",
          "SPECIES",
          "SPONTANEOUS?",
          "STD-REDUCTION-POTENTIAL",
          "SYNONYMS",
          "SYSTEMATIC-NAME",
          "TEMPLATE-FILE",
          "^COEFFICIENT",
          "^COMPARTMENT"
      };


      BlockingQueue<Record>  tester = new ArrayBlockingQueue<Record>(30000);
      DBWriter test = new DBWriter(tester);
      //test.createDatabase("reactiondb");
      //test.useDatabase("KAMIYE");
      //test.createTable("reactiondb", "reactions", attribute);
      //test.closeConnection();
      //test.createTables("ojo");
      //test.deleteDatabase("reactiondb");
      //test.selectDatabase("reactiondb");
      //test.executeTheUpdate("reactiondb.reactions");
    }


}

/*System.out.println(attribute.length);
for(String s : attribute){
  System.out.println(s+",");
}

*      String[] attribute = {
    "UNIQUE-ID",
    "TYPES",
    "COMMON-NAME",
    "ATOM-MAPPINGS",
    "CANNOT-BALANCE?",
    "CITATIONS",
    "COMMENT",
    "COMMENT-INTERNAL",
    "CREDITS",
    "DATA-SOURCE",
    "DBLINKS",
    "DELTAG0",
    "DOCUMENTATION",
    "EC-NUMBER",
    "ENZYMATIC-REACTION",
    "ENZYMES-NOT-USED",
    "EQUILIBRIUM-CONSTANT",
    "HIDE-SLOT?",
    "IN-PATHWAY",
    "INSTANCE-NAME-TEMPLATE",
    "LEFT",
    "MEMBER-SORT-FN",
    "ORPHAN?",
    "PATHOLOGIC-NAME-MATCHER-EVIDENCE",
    "PATHOLOGIC-PWY-EVIDENCE",
    "PHYSIOLOGICALLY-RELEVANT?",
    "PREDECESSORS",
    "PRIMARIES",
    "REACTION-DIRECTION",
    "REACTION-LIST",
    "REGULATED-BY",
    "REQUIREMENTS",
    "RIGHT",
    "RXN-LOCATIONS",
    "SIGNAL",
    "SPECIES",
    "SPONTANEOUS?",
    "STD-REDUCTION-POTENTIAL",
    "SYNONYMS",
    "SYSTEMATIC-NAME",
    "TEMPLATE-FILE",
    "^COEFFICIENT",
    "^COMPARTMENT"
};

*
*/
