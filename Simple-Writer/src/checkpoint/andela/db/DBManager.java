package checkpoint.andela.db;


import java.sql.*;

public class DBManager {
  
  private Connection connection;
  private Statement statement;

  private static final String DRIVER = "com.mysql.jdbc.Driver";  
  private static final String DB_URL = "jdbc:mysql://localhost/";
  private static final String USER = "root";
  private static final String PASS = "Nobest@86";

    
  public DBManager() {}
  
  
  public Connection getConnection() {
    
    try{
      
      Class.forName(DRIVER);
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
     
    }catch(SQLException se){
      
      se.printStackTrace();
      
    }catch(Exception e){
     
      e.printStackTrace();
    }
    return connection;
  }
  
  
  
  public void createDatabase(String DBName ){
    
    if(databaseExists(DBName)) {
      String message = "Database exists! Please choose another name";
      System.out.println(message);
      return;
    }
      
      try{
        statement = connection.createStatement();
        
        String database = "CREATE DATABASE " + DBName;
        statement.executeUpdate(database);
        System.out.println("Database created successfully ");
       
      }
      catch(SQLException sqle){
        sqle.printStackTrace();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    
  }
  

  public void createTable(String DBName, String tableName, String... tableColumns) throws SQLException {
    
    //Delete table if the tableName already exist.
    deleteTable(DBName, tableName);
    
    String table = "CREATE TABLE " + DBName + "." + tableName + " (";
    
    for(String string : tableColumns){
      table += "`" + string +"`" + " TEXT,";
    }
    table = table.substring(0, table.length() - 1) + ")";
    
    execute(table);
    System.out.println("Table created successfully ");
  }
  

  public  void execute(String table) {
    try {
      statement = connection.createStatement();
      statement.executeUpdate(table);
    }
    catch (SQLException sqle){
      sqle.printStackTrace();
    }
  }
  
  
  public void deleteTable(String DBName, String tableName){
    getConnection();
    
    String deleteTable = "DROP TABLE IF EXISTS "+ DBName + "." + tableName;
    execute(deleteTable);
  }
  
  
  public boolean databaseExists(String DBName){
  
    try{
      ResultSet resultSet = getConnection().getMetaData().getCatalogs();
      while (resultSet.next()) {
        if(resultSet.getString(1).equals(DBName)){
          return true;
        }
      }
      resultSet.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return false;
  }
  
  
  public void deleteDatabase(String DBName){
    
    if(!databaseExists(DBName)){
      return;
    }
    String deleteDB = "DROP DATABASE "+ DBName;
    execute(deleteDB);
    System.out.println("Database deleted successfully! ");
  }
  
  
  public void closeConnection() {
    try{
      if(connection!=null) {
        connection.close();
      }
      if(statement != null){
        statement.close();
      }
         
    }catch(SQLException sqle){
       sqle.printStackTrace();
    }
  }
  

  public void insertData(){
    String insertQuery = " INSERT INTO reactiondb.reactions (UNIQUE-ID, TYPES, COMMON-NAME)" +
        " values (Semiu, Hassan, Ayomon)";
    execute(insertQuery);
  }
}