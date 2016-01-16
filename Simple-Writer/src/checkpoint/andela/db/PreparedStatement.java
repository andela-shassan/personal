package checkpoint.andela.db;

import checkpoint.andela.parser.Record;

public  class PreparedStatement {

  public PreparedStatement() {
    
  }
  
  
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
  
  
  public static String generateInsertQuery(Record record){
    String insertQuery = " INSERT INTO reactions (UNIQUE-ID, TYPES, COMMON-NAME, ATOM-MAPPINGS, CANNOT-BALANCE?, CITATIONS, COMMENT, COMMENT-INTERNAL, CREDITS, DATA-SOURCE, DBLINKS, DELTAG0, DOCUMENTATION, EC-NUMBER, ENZYMATIC-REACTION, ENZYMES-NOT-USED, EQUILIBRIUM-CONSTANT, HIDE-SLOT?, IN-PATHWAY, INSTANCE-NAME-TEMPLATE, LEFT, MEMBER-SORT-FN, ORPHAN?, PATHOLOGIC-NAME-MATCHER-EVIDENCE, PATHOLOGIC-PWY-EVIDENCE, PHYSIOLOGICALLY-RELEVANT?, PREDECESSORS, PRIMARIES, REACTION-DIRECTION, REACTION-LIST, REGULATED-BY, REQUIREMENTS, RIGHT, RXN-LOCATIONS, SIGNAL, SPECIES, SPONTANEOUS?, STD-REDUCTION-POTENTIAL, SYNONYMS, SYSTEMATIC-NAME, TEMPLATE-FILE, ^COEFFICIENT, ^COMPARTMENT)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
    
    return insertQuery;
  }
  
}
