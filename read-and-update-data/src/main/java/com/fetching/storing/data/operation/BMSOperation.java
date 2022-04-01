package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.model.BMS;
import com.fetching.storing.data.model.EwasteBatch;

public class BMSOperation {
	public void startInsertingData() {
		List<String> data;
		BMSOperation eb=new BMSOperation(); 
		
		//get data from csv file
		ReadAndReturnData rrd=new ReadAndReturnData();
		data=rrd.getCsvData("/Users/macair/Downloads/cells.csv",13);
		
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
	public long getRowsCountFromDb() {
		PostgresConnection connection = new PostgresConnection();
		Query query = connection.SESSION.createQuery("select max(id) from BMS");
		Object object = query.uniqueResult();
		if(object==null) {
			return 0;
		}
		
		return (long) object;
	}
	private void insertData(List<String> data) {
		BMSOperation eb=new BMSOperation(); 
		PostgresConnection connection = new PostgresConnection();
		for(int i=1;i<data.size();i++) {
			BMS ebData=new BMS(); 
			ebData.setId(eb.getRowsCountFromDb()+1);
			Map<String,String> map=new HashMap<String,String>();
			map.put("value", data.get(i));
			ebData.setData(map);
			connection.SESSION.save(ebData);
		}
		BMS ebData=new BMS(); 
		Map<String,String> map=new HashMap<String,String>();
		ebData.setId(0);
		map.put("value", "no data");
		ebData.setData(map);
		connection.SESSION.save(ebData);
		
	}
}
