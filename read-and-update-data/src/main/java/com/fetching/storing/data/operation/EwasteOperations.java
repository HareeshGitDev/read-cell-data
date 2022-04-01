package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.model.Ewaste;
import com.fetching.storing.data.model.EwasteBatch;

public class EwasteOperations {
	public void startInsertingData() {
		List<String> data;
		EwasteOperations e=new EwasteOperations(); 
		
		//get data from csv file
		ReadAndReturnData rrd=new ReadAndReturnData();
		data=rrd.getCsvData("/Users/macair/Downloads/cells.csv",14);
		
		//remove duplicate
		data=e.removeDuplicate(data);

		//insert data into db
		e.insertData(data);
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
	public long getRowsCountFromDb() {
		PostgresConnection connection = new PostgresConnection();
		Query query = connection.SESSION.createQuery("select max(id) from Ewaste");
		Object object = query.getSingleResult();
		if(object==null) {
			return 0;
		}
		
		return (long) object;
	}
	private void insertData(List<String> data) {
		EwasteOperations eb=new EwasteOperations(); 
		PostgresConnection connection = new PostgresConnection();
		for(int i=1;i<data.size();i++) {
			Ewaste ebData=new Ewaste(); 
			ebData.setId(eb.getRowsCountFromDb()+1);
			ebData.setName(data.get(i));
			connection.SESSION.save(ebData);
		}
		Ewaste ebData=new Ewaste(); 
		ebData.setId(0);
		ebData.setName("no data");
		connection.SESSION.save(ebData);
		
	}
}
