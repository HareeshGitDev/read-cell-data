package com.fetching.storing.data;


import java.util.ArrayList;
import java.util.List;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.dto.CellDto;
import com.fetching.storing.data.fetchingdatafromfile.ReadAndReturnData;
import com.fetching.storing.data.operation.CellOperations;
import com.fetching.storing.data.operation.CellTesting;


public class CellApp {
	
	public static void main(String args[]) {
		
		ReadAndReturnData data=new ReadAndReturnData();
		List<CellDto> cellDto=new ArrayList<CellDto>();
		System.out.println("begin process start");
		
		//get csv cells data.
		cellDto=data.getCsvData();
		System.out.println(cellDto.size());
		
		//insert cell data into db
		CellOperations cellOperations=new CellOperations();
		cellOperations.insertCellDataIntoDb(cellDto);
		
		
		//connection closed
		PostgresConnection connection = new PostgresConnection();
		connection.closeConnections();
		
	}
	
	

}
