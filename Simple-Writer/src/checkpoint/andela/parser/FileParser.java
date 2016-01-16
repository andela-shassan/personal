package checkpoint.andela.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 14/01/2016.
 */
public class FileParser implements Runnable {
  
  private BufferedReader bufferedReader;
  private BlockingQueue<Record> records;
  

  public FileParser(BlockingQueue<Record> records, String filePath) throws Exception {
    bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
    this.records = records;
  }


  @Override
  public void run() {
    try{
      Record aRecord = new Record();
      String line;
      while ((line = bufferedReader.readLine()) != null){
        if(line.startsWith("#")){
          continue;
        }
        if (line.startsWith("//")) {
          //aRecord.removeDuplicate();
          records.put(aRecord);
          System.out.println(aRecord.recordSize());
          aRecord = new Record();
        }
        OrderedPair pair = setOrderedPair(line);
        aRecord.addPair(pair);
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    System.out.println(records.size());
    
  }

  private OrderedPair setOrderedPair(String line) {
    if(line.length() > 2){
      OrderedPair pair = new OrderedPair();
      pair.setAttribute(line.substring(0, line.indexOf(" ")));
      pair.setValue(line.substring((line.indexOf(" ") + 3)));
      return pair;
    }
    return null;
  }
    
  
  public static void main(String[] args)
      throws Exception {
    BlockingQueue<Record> test = new ArrayBlockingQueue<Record>(30000);
    FileParser trial = new FileParser(test, "C:\\Users\\Semiu\\Desktop\\edit\\reactions.dat");
    trial.run();
  }
}



/*String[] attribute = {
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
};*/

/*for(String s : attribute){
System.out.println(s);
}*/

/*
for(OrderedPair pai : records){
  System.out.println(pai.join());
  list.add(pai);
}
System.out.println(list.size());

for(OrderedPair op : records){
  String s1 = op.getAttribute();
  continue;
  String s2 = op.getAttribute();
  OrderedPair op2 = records.peek();
  if(s1 == s2){
    System.out.println("They are equal " + records.remainingCapacity());
  }else{
    System.out.println("They are not equal " + records.remainingCapacity());
  }
}

//int i = list.size() -1;
   
   // while (index < list.size()) // size is re-evaluated with each iteration
    {
       // Object o = list.get(i);
        // do something with o
      //  i++;
    }


for(OrderedPair pai : records){
      System.out.println(pai.join());
    }
    //ArrayList<OrderedPair> list = new ArrayList<OrderedPair>();
    //records.drainTo(list);
    System.out.println(records.size());
    
    
    int s = record.size();
          for(int i = 0; i < s; i++){
            int j = i+1;
            OrderedPair p = record.get(i);
            OrderedPair q = record.get(j);
            if(p.getAttribute().equals(q.getAttribute())){
              record.remove(i);
            }
          }
    
    
    
    
    
    System.out.println(allRecords.size());
    ArrayList<OrderedPair> rec = null;
    try {
      rec = allRecords.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for(OrderedPair p : rec){
      System.out.println(p.getPair());
    }
    

*/



/*while(!records.isEmpty()){
  Record rec = null;
  try {
    rec = records.take();
  } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  int size = rec.recordSize();
  for(int i = 0; i < size; i++){
    int j = i+1;
    OrderedPair p = rec.getPair(i);
    OrderedPair q = rec.getPair(j);
    if(p.getAttribute().equals(q.getAttribute())){
      rec.removePair(i);
    }
  }
}*/

/* ArrayList<OrderedPair> rec = null;
try {
  rec = allRecord.take();
} catch (InterruptedException e) {
  e.printStackTrace();
}
for(OrderedPair p : rec){
  System.out.println(p.getPair());
}*/
