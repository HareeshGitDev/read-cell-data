package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.query.Query;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.model.PhysicalCondition;

public class PhysicalConditionOperations {
	public void startInsertingData() {
		List<String> data;
		PhysicalConditionOperations pc=new PhysicalConditionOperations (); 
		
		//get data from csv file
		ReadAndReturnData rrd=new ReadAndReturnData();
		data=rrd.getCsvData("/Users/macair/Downloads/cells.csv",19);
		for(int i=1640;i<1700;i++) {
			System.out.println(data.get(i));
		}
		//remove duplicate
		data=pc.removeDuplicate(data);
		

		//insert data into db
		//pc.insertData(data);
		
	}
	private List<String> removeDuplicate(List<String> data){
		List<String> cleanData=new ArrayList<String>();
		for(int i=0;i<data.size();i++) {
			List<String> eachString= new ArrayList<>();
			 eachString = Arrays.asList(data.get(i).split(","));
			if(eachString.size()>1) {
				for(int j=0;i<eachString.size();j++) {
					if(cleanData.contains(eachString.get(j).replaceAll(" ", "").toLowerCase())) {
						
					}
					else {
						cleanData.add(eachString.get(j).replaceAll(" ", "").toLowerCase());
					}
				}
			}
			else {
				if(cleanData.contains(data.get(i).replaceAll(" ", "").toLowerCase()) || data.get(i).isEmpty() || data.get(i).equals("NA")) {
					
				}
				else {
					cleanData.add(data.get(i).replaceAll(" ", "").toLowerCase());
				}
			}	
		}
		return cleanData;
	}
	public long getRowsCountFromDb() {
		PostgresConnection connection = new PostgresConnection();
		Query query = connection.SESSION.createQuery("select max(id) from PhysicalCondition");
		Object object = query.uniqueResult();
		if(object==null) {
			return 0;
		}
		
		return (long) object;
	}
	private void insertData(List<String> data) {
		PhysicalConditionOperations pc=new PhysicalConditionOperations (); 
		PostgresConnection connection = new PostgresConnection();
		for(int i=1;i<data.size();i++) {
			PhysicalCondition pcData=new PhysicalCondition();
			pcData.setId(pc.getRowsCountFromDb()+1);
			pcData.setName(data.get(i));
			connection.SESSION.save(pcData);
		}
		PhysicalCondition pcData=new PhysicalCondition();
		pcData.setId(0);
		pcData.setName("no data");
		connection.SESSION.save(pcData);
		
	}
}
