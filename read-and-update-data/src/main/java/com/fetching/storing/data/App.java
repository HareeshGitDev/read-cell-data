package com.fetching.storing.data;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.operation.BMSOperation;
import com.fetching.storing.data.operation.EwasteBatchOperations;
import com.fetching.storing.data.operation.EwasteOperations;
import com.fetching.storing.data.operation.ManufacturerOperations;
import com.fetching.storing.data.operation.PhysicalConditionOperations;

public class App {
  public static void main(String[] args) {
    PhysicalConditionOperations p=new  PhysicalConditionOperations();
    p.startInsertingData();
	  
//    EwasteBatchOperations eb=new EwasteBatchOperations();
//    eb.startInsertingData();
//    
//	  ManufacturerOperations mo=new ManufacturerOperations();
//	  mo.startInsertingData();
//	  
//	  EwasteOperations e=new EwasteOperations();
//	  e.startInsertingData();
//	  
//	  BMSOperation bms=new BMSOperation();
//	  bms.startInsertingData();
//	
	// close postgres connections  
    PostgresConnection connection = new PostgresConnection();
    connection.closeConnections();
  }
}
