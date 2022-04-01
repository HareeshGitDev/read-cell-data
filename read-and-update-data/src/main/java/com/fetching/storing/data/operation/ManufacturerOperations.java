package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.model.Manufacturer;

public class ManufacturerOperations {
	public void startInsertingData() {
		ManufacturerOperations mo=new ManufacturerOperations();
		List<String> data;
		Manufacturer m=new Manufacturer();
		
		//get data from csv file
		ReadAndReturnData rrd=new ReadAndReturnData();
		data=rrd.getCsvData("/Users/macair/Downloads/cells.csv",9);
		
		//remove duplicate
		data=mo.removeDuplicate(data);

		//insert data into db
		mo.insertData(data);
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
		Query query = connection.SESSION.createQuery("select max(id) from Manufacturer");
		Object object = query.uniqueResult();
		if(object==null) {
			return 0;
		}
		
		return (long) object;
	}
	private void insertData(List<String> data) {
		ManufacturerOperations mo = new ManufacturerOperations();
		PostgresConnection connection = new PostgresConnection();
		for(int i=1;i<data.size();i++) {
			Manufacturer mData=new Manufacturer(); 
			mData.setId(mo.getRowsCountFromDb()+1);
			mData.setName(data.get(i));
			connection.SESSION.save(mData);
		}
		Manufacturer mData=new Manufacturer(); 
		mData.setId(0);
		mData.setName("no data");
		connection.SESSION.save(mData);
		
	}
}
