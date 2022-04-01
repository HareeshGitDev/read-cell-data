package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.model.EwasteBatch;

public class EwasteBatchOperations {
	public void startInsertingData() {
		List<String> data;
		EwasteBatchOperations eb=new EwasteBatchOperations(); 
		
		//get data from csv file
		ReadAndReturnData rrd=new ReadAndReturnData();
		data=rrd.getCsvData("/Users/macair/Downloads/cells.csv",3);
		
		//remove duplicate
		data=eb.removeDuplicate(data);

		//insert data into db
		eb.insertData(data);
		for(int i=0;i<data.size();i++) {
			System.out.println(data.get(i));
		}
		System.out.println(data.size());
	}
	private List<String> removeDuplicate(List<String> data){
		List<String> cleanData=new ArrayList<String>();
		for(int i=0;i<data.size();i++) {
			if(cleanData.contains(data.get(i)) || data.get(i).isEmpty() || data.get(i).equals("NA")) {
				
			}
			else {
				cleanData.add(data.get(i));
			}
		}
		return cleanData;
	}
	public int getRowsCountFromDb() {
		PostgresConnection connection = new PostgresConnection();
		Query query = connection.SESSION.createQuery("select max(id) from EwasteBatch");
		Object object = query.uniqueResult();
		if(object==null) {
			return 0;
		}
		
		return (Integer) object;
	}
	private void insertData(List<String> data) {
		EwasteBatchOperations eb=new EwasteBatchOperations(); 
		PostgresConnection connection = new PostgresConnection();
		for(int i=1;i<data.size();i++) {
			EwasteBatch ebData=new EwasteBatch(); 
			ebData.setId(eb.getRowsCountFromDb()+1);
			ebData.setName(data.get(i));
			connection.SESSION.save(ebData);
		}
		EwasteBatch ebData=new EwasteBatch(); 
		ebData.setId(0);
		ebData.setName("no data");
		connection.SESSION.save(ebData);
		
	}
}
