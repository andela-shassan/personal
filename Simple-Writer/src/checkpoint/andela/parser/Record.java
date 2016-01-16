package checkpoint.andela.parser;

import java.util.ArrayList;

public class Record {
  
  private ArrayList<OrderedPair> aRecord;
  
  
  public Record() {
    aRecord = new ArrayList<OrderedPair>();
  }
  
  
  public void addPair(OrderedPair pair) {
      aRecord.add(pair);
  }
  
  public boolean pairAttributeExists(OrderedPair pair){
    int size = recordSize();
    for(int i = 0; i < size; i++){
      OrderedPair p = aRecord.get(i);
      if(p.getAttribute().equals(pair.getAttribute())){
        return true;
      }
    }
    return false;
  }
  
  
  public void removeDuplicate(){
    int size = recordSize();
    for(int i = 0; i < size; i++){
      int j = i + 1;
      OrderedPair p = aRecord.get(i);
      OrderedPair q = aRecord.get(j);
      if(p.getAttribute().equals(q.getAttribute())){
        aRecord.remove(i);
      } 
    }
  }
  
  
  public void removePair(int i){
    aRecord.remove(i);
  }
  
  
  public int recordSize(){
    return aRecord.size();
  }
  
  public ArrayList<OrderedPair> getRecord(){
    
    return aRecord;
  }
  
}









