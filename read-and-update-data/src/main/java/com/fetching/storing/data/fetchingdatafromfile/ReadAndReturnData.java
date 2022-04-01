package com.fetching.storing.data.fetchingdatafromfile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fetching.storing.data.dto.CellDto;
import com.fetching.storing.data.operation.FindDataType;
import com.opencsv.CSVReader;

public class ReadAndReturnData {
	public List<String> getCsvData(String filePath,int index){
		List<String> csvData=new ArrayList<String>();
		try {
			FileReader fileReader=new FileReader(filePath);
			CSVReader csvReader=new CSVReader(fileReader);
			String[] nextLine=csvReader.readNext();
			while(nextLine!=null) {
				csvData.add(nextLine[index]);
				nextLine=csvReader.readNext();
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("file not file ");
			csvData.clear();
		}
		catch(IOException e) {
			System.out.println("IO exception ");
			csvData.clear();
		}
		
		return csvData;
	}
	public List<CellDto> getCsvData() {
		List<CellDto> cellList=new ArrayList<CellDto>();
		FindDataType findData=new FindDataType();
		try {
			FileReader fileReader=new FileReader("/Users/macair/Downloads/cells.csv");
			CSVReader csvReader=new CSVReader(fileReader);
			String[] nextLine=csvReader.readNext();
			nextLine= csvReader.readNext();
			int count=0;
			while(nextLine!=null) {
				//System.out.println(count);
				count++;
				CellDto cell=new CellDto();
				
				// cell_id not be count value, in cell table use getrowscountcell methods to fetch count and store;
				cell.setCellId(count);
			
				cell.setBatchId(nextLine[3].equals("NA")?null:nextLine[3]);
				cell.setNominalVoltage(findData.isFloat(nextLine[4]) ? Float.parseFloat(nextLine[4]) : (float)0);
				cell.setVoltage(findData.isFloat(nextLine[5]) ? Float.parseFloat(nextLine[5]) : (float)0);
				cell.setCellCapacity(findData.isLong(nextLine[7]) ? Long.parseLong(nextLine[7]) : (long)0);
				cell.setOutputWh(findData.isLong(nextLine[8]) ? Long.parseLong(nextLine[8]) : (long)0);
				cell.setManufacturerId(nextLine[9].equals("NA")?null:nextLine[9]);
				cell.setManufacturerYear(findData.isInt(nextLine[11])?Integer.parseInt(nextLine[11]):0);
				cell.setManufacturerMonth(findData.isInt(nextLine[12])?Integer.parseInt(nextLine[12]):0);
				cell.setBmsId(nextLine[13].equals("NA")?null:nextLine[13]);
				cell.setEwasteSourceId(nextLine[14].equals("NA")?null:nextLine[14]);
				cell.setLaptopBrand(nextLine[16].equals("NA")?null:nextLine[16]);
				cell.setCellVoltage(findData.isFloat(nextLine[18]) ? Float.parseFloat(nextLine[18]) : (float)0);
				cell.setCellConditionId(nextLine[19].equals("NA")?null:nextLine[19]);
				cell.setRecordNo(findData.isLong(nextLine[21]) ? Long.parseLong(nextLine[21]) : (long)0);
				cell.setIr(findData.isFloat(nextLine[22]) ? Float.parseFloat(nextLine[22]) : (float)0);
				cell.setCommand(nextLine[23].equals("NA")?null:nextLine[23]);
				
				cell.setDate(nextLine[24].isEmpty() || nextLine[24].equals("NA")? null:this.getTimeAsInstantFormat(nextLine[24]));
				
				cellList.add(cell);
				nextLine=csvReader.readNext();
			}
			return cellList;
		}
		catch(FileNotFoundException e) {
			System.out.println("file not file ");
			List<CellDto> cellEmpty=new ArrayList<CellDto>();
			return cellEmpty;
		}
		catch(IOException e) {
			System.out.println("IO exception ");
			List<CellDto> cellEmpty=new ArrayList<CellDto>();
			return cellEmpty;
		}
		catch(NumberFormatException e) {
			System.out.println(" number format exception = "+e+" "+cellList.size());
			
			List<CellDto> cellEmpty=new ArrayList<CellDto>();
			return cellEmpty;
		}
		
	}
	private Instant getTimeAsInstantFormat(String str) {
		Instant instant=Instant.parse("20"+str.substring(6,8)+"-"+str.substring(3, 5)+"-"+str.substring(0, 2)+"T"+str.substring(9, 14)+":00Z");
		return instant;
	}
}
