package com.fetching.storing.data.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.json.JSONObject;

import com.fetching.storing.data.constants.PostgresConnection;
import com.fetching.storing.data.dto.CellDto;
import com.fetching.storing.data.model.BMS;
import com.fetching.storing.data.model.Cell;
import com.fetching.storing.data.model.Ewaste;
import com.fetching.storing.data.model.EwasteBatch;
import com.fetching.storing.data.model.Manufacturer;
import com.fetching.storing.data.model.PhysicalCondition;

public class CellOperations  {
	PostgresConnection pc=new PostgresConnection();
	public void insertCellDataIntoDb(List<CellDto> cellDtoList) {
		List<Cell> cellList=new ArrayList<Cell>();
		for(int i=1665;i<cellDtoList.size();i++) {
			//System.out.println("testing purpose"+i);
			Cell cell=new Cell(cellDtoList.get(i));
			
			cell.setCellId(this.getRowsCountFromDb()+1);
			cell.setBatchId(this.mappingEwasteBatch(cellDtoList.get(i).getBatchId()));
			cell.setEwasteSourceId(this.mappingEwaste(cellDtoList.get(i).getEwasteSourceId()));
			cell.setBmsId(this.mappingBms(cellDtoList.get(i).getBmsId()));
			cell.setManufacturerId(this.mappingManufacturer(cellDtoList.get(i).getManufacturerId()));
			
			//set value for cell_condition many to many table
			List<PhysicalCondition> pcList = getPhysicalConditionData(cellDtoList.get(i));
			for(int j=0;j<pcList.size();j++) {
				System.out.println(" pc list "+ i+" =" +pcList.get(j).toString());
			}
			cell.setCellConditionId(pcList);
			
			
			cellList.add(cell);
			pc.SESSION.save(cell);
			
		}
		System.out.println("insert cell entity complete");
	}
	private EwasteBatch mappingEwasteBatch(String batch) {
		if(batch==null||batch.isEmpty()) {
			EwasteBatch eb=new EwasteBatch();
			eb.setId(0);
			return eb;
		}
		
		SQLQuery query = pc.SESSION.createSQLQuery("select count(*) from EwasteBatch where name='"+batch+"'");
		Object object = query.getSingleResult();
		if((Integer) object == 0) {
			EwasteBatchOperations operation = new EwasteBatchOperations();
			EwasteBatch batchId=new EwasteBatch();
			batchId.setId(operation.getRowsCountFromDb()+1);
			batchId.setName(batch);
			pc.SESSION.save(batchId);
			return batchId;
		}
		else {
			query=pc.SESSION.createSQLQuery("select * from EwasteBatch where name='"+batch+"'");
			object = query.getSingleResult();
			return (EwasteBatch) object;
		}
	}
	
	private Manufacturer mappingManufacturer(String manufacturerName) {
		if(manufacturerName==null) {
			Manufacturer m=new Manufacturer();
			m.setId(0);
			return m;
		}
		SQLQuery query = pc.SESSION.createSQLQuery("select count(*) from Manufacturer where name='"+manufacturerName+"'");
		Object object = query.getFirstResult();
		if((Integer) object == 0) {
			ManufacturerOperations operation = new ManufacturerOperations();
			Manufacturer manufacturer=new Manufacturer();
			manufacturer.setId(operation.getRowsCountFromDb()+1);
			manufacturer.setName(manufacturerName);
			pc.SESSION.save(manufacturer);
			return manufacturer;
		}
		else {
			query=pc.SESSION.createSQLQuery("select * from Manufacturer where name='"+manufacturerName+"'");
			object = query.getSingleResult();
			return (Manufacturer) object;
		}
	}
	
	private PhysicalCondition mappingPhysicalCondition(String physicalConditionName) {
		if(physicalConditionName==null) {
			PhysicalCondition p=new PhysicalCondition();
			p.setId(0);
			return p;
		}
		SQLQuery query = pc.SESSION.createSQLQuery("select count(*) from PhysicalCondition where name='"+physicalConditionName+"'");
		Object object = query.getFirstResult();
		if((Integer) object == 0) {
			PhysicalConditionOperations operation = new PhysicalConditionOperations();
			PhysicalCondition physicalCondition=new PhysicalCondition();
			physicalCondition.setId(operation.getRowsCountFromDb()+1);
			physicalCondition.setName(physicalConditionName);
			pc.SESSION.save(physicalCondition);
			return physicalCondition;
		}
		else {
			query=pc.SESSION.createSQLQuery("select * from PhysicalCondition where name='"+physicalConditionName+"'");
			object = query.getSingleResult();
			return (PhysicalCondition) object;
		}
	}
	
	private Ewaste mappingEwaste(String ewasteName) {
		if(ewasteName==null||ewasteName.isEmpty()) {
			Ewaste e=new Ewaste();
			e.setId(0);
			return e;
		}
		//Query query = pc.SESSION.createQuery("select count(*) from ewaste e where e.name='"+ewasteName+"'");
		
		SQLQuery query = pc.SESSION.createSQLQuery("select count(*) from Ewaste  where name='"+ewasteName+"'");
		Object object = query.getFirstResult();
		if((Integer) object == 0) {
			EwasteOperations operation = new EwasteOperations();
			Ewaste ewaste=new Ewaste();
			ewaste.setId(operation.getRowsCountFromDb()+1);
			ewaste.setName(ewasteName);
			pc.SESSION.save(ewaste);
			return ewaste;
		}
		else {
			query=pc.SESSION.createSQLQuery("select * from Ewaste where name='"+ewasteName+"'");
			object = query.getFirstResult();
			return (Ewaste) object;
		}
	}
	
	private BMS mappingBms(String bmsData) {
		if(bmsData==null) {
			BMS bms=new BMS();
			bms.setId(0);
			return bms;
		}
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("value",bmsData );
		String data="'{"+"\"value\":\""+bmsData+"\"}'";
		SQLQuery query = pc.SESSION.createSQLQuery("select count(*) from BMS where data="+data);
		Object object = query.getFirstResult();
		if((Integer) object == 0) {
			BMSOperation operation = new BMSOperation();
			BMS bms=new BMS();
			Map<String,String> map=new HashMap<String,String>();
			map.put("value", bmsData);
			bms.setId(operation.getRowsCountFromDb()+1);
			bms.setData(map);
			pc.SESSION.save(bms);
			return bms;
		}
		else {
			query=pc.SESSION.createSQLQuery("select * from BMS where name="+data);
			object = query.getSingleResult();
			return (BMS) object;
		}
	}
	
	private List<PhysicalCondition> dataInsertToCellConditionEntity(List<String> cellDataList,Cell cell) {
		
		PostgresConnection connection=new PostgresConnection();
		if(cellDataList.size()==0) {
			PhysicalCondition pc=new PhysicalCondition();
			pc.setId(0);
			pc.setName("no data");
			List<PhysicalCondition> result=new ArrayList<>();
			result.add(pc);
			return result;
		}
		List<PhysicalCondition> pc=new ArrayList<>();
		for(int i=1;i<cellDataList.size();i++) {
			String str="cellDataList.get(i).replaceAll(\" \", \"\").toLowerCase()";
			SQLQuery query = connection.SESSION.createSQLQuery("select * from PhysicalCondition where name='"+str+"'");
			Object result = query.getSingleResult();
			pc.add((PhysicalCondition)result);
			
		}
		return pc;
	}
	private List<PhysicalCondition> getPhysicalConditionData(CellDto cellDto){
		if(cellDto.getCellConditionId().isEmpty() || cellDto.equals("NA")) {
			List<PhysicalCondition> pcList=new ArrayList<>();
			//System.out.println("Entere first if");
			PhysicalCondition pc=new PhysicalCondition();
			pc.setId(0);
			pc.setName("no data");
			pc.setType(1);
			pcList.add(pc);
			return pcList;
		}
		List<String> cellCondition=Arrays.asList(cellDto.getCellConditionId().split(","));
		//System.out.println("cell condition before remove duplciate=" +cellCondition);
		cellCondition =this.removeDuplicate(cellCondition);
		//System.out.println("cell condition=" +cellCondition);
		//System.out.println("cell condition size=" +cellCondition.size());
		if(cellCondition.size()==1) {
			List<PhysicalCondition> pcList=new ArrayList<>();
			//System.out.println("Entere second if");
			//String str="cellData.getCellConditionId().replaceAll(\" \", \"\").toLowerCase()";
			String str="perfect";
			Query query = pc.SESSION.createQuery("from PhysicalCondition pc where pc.name='"+str+"'");
			Object result = query.getSingleResult();
			PhysicalCondition condition= (PhysicalCondition) result;
			pcList.add(condition);
			return pcList;
		}
		//System.out.println(" before for loop");
		List<PhysicalCondition> pcList=new ArrayList<>();
		for(int i=0;i<cellCondition.size();i++) {
			System.out.println("cell condition "+cellCondition.get(i));
			String str="cellCondition.get(i)";
			Query query = pc.SESSION.createQuery("from PhysicalCondition pc where pc.name='"+cellCondition.get(i)+"'");
			Object result = query.getSingleResult();
			PhysicalCondition condition= (PhysicalCondition) result;
			//System.out.println("data from db = " +condition.toString());
			pcList.add(condition);
			
		}
		return pcList;
		
	}
	private List<String> removeDuplicate(List<String> data){
		List<String> cleanData=new ArrayList<String>();
		for(int i=0;i<data.size();i++) {
			if(cleanData.contains(data.get(i).replaceAll(" ","").toLowerCase())) {			
			}
			else {
				cleanData.add(data.get(i).replaceAll(" ","").toLowerCase());
			}
		}
		return cleanData;
	}
	public long getRowsCountFromDb() {
		PostgresConnection connection = new PostgresConnection();
		Query query = connection.SESSION.createQuery("select max(id) from Cell");
		Object object = query.getSingleResult();
		if(object==null) {
			return 0;
		}
		
		return (long) object;
	}
	
}
