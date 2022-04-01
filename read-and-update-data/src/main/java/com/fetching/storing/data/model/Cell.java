package com.fetching.storing.data.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fetching.storing.data.dto.CellDto;

@Entity
@Table(name="cell")
public class Cell {
	@Id
	@Column(name="cell_id")
	private long cellId;
	private Instant date;
	@ManyToOne
	@JoinColumn(name="batch_id")
	private EwasteBatch batchId;
	@Column(name="nominal_voltage")
	private float nominalVoltage;
	private float voltage;
	@Column(name="cell_capacity")
	private long cellCapacity;
	@Column(name="output_wh")
	private long outputWh;
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturerId;
	@Column(name="manufacturer_year")
	private int manufacturerYear;
	@JoinColumn(name="manufacturer_month")
	private int manufacturerMonth;
	@ManyToOne
	@JoinColumn(name="bms_id")
	private BMS bmsId;
	@ManyToOne
	@JoinColumn(name="ewaste_source_id")
	private Ewaste ewasteSourceId;
	@Column(name="laptop_brand")
	private String laptopBrand;
	@Column(name="cell_voltage")
	private float cellVoltage;
	
	@ManyToMany(targetEntity=PhysicalCondition.class)
	@JoinTable(
			name="cell_condition",
			joinColumns= {@JoinColumn(name="cell_id")},
			inverseJoinColumns= {@JoinColumn(name="condition_id")})
	private List<PhysicalCondition> cellConditionId = new ArrayList<>();
	@Column(name="record_no")
	private long recordNo;
	private float ir;
	private String command;
	
	public Cell(CellDto cell) {
		super();
		this.date = cell.getDate();
		this.nominalVoltage = cell.getNominalVoltage();
		this.voltage = cell.getVoltage();
		this.cellCapacity =cell.getCellCapacity();
		this.outputWh = cell.getOutputWh();
		this.manufacturerYear = cell.getManufacturerYear();
		this.manufacturerMonth = cell.getManufacturerMonth();
		this.laptopBrand = cell.getLaptopBrand();
		this.cellVoltage = cell.getCellVoltage();
		this.recordNo = cell.getRecordNo();
		this.ir = cell.getIr();
		this.command = cell.getCommand();
	}
	public long getCellId() {
		return cellId;
	}
	public void setCellId(long cellId) {
		this.cellId = cellId;
	}
	public Instant getDate() {
		return date;
	}
	public void setDate(Instant date) {
		this.date = date;
	}
	public EwasteBatch getBatchId() {
		return batchId;
	}
	public void setBatchId(EwasteBatch batchId) {
		this.batchId = batchId;
	}
	public float getNominalVoltage() {
		return nominalVoltage;
	}
	public void setNominalVoltage(float nominalVoltage) {
		this.nominalVoltage = nominalVoltage;
	}
	public float getVoltage() {
		return voltage;
	}
	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}
	public long getCellCapacity() {
		return cellCapacity;
	}
	public void setCellCapacity(long cellCapacity) {
		this.cellCapacity = cellCapacity;
	}
	public long getOutputWh() {
		return outputWh;
	}
	public void setOutputWh(long outputWh) {
		this.outputWh = outputWh;
	}
	public Manufacturer getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Manufacturer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public int getManufacturerYear() {
		return manufacturerYear;
	}
	public void setManufacturerYear(int manufacturerYear) {
		this.manufacturerYear = manufacturerYear;
	}
	public int getManufacturerMonth() {
		return manufacturerMonth;
	}
	public void setManufacturerMonth(int manufacturerMonth) {
		this.manufacturerMonth = manufacturerMonth;
	}
	public BMS getBmsId() {
		return bmsId;
	}
	public void setBmsId(BMS bmsId) {
		this.bmsId = bmsId;
	}
	public Ewaste getEwasteSourceId() {
		return ewasteSourceId;
	}
	public void setEwasteSourceId(Ewaste ewasteSourceId) {
		this.ewasteSourceId = ewasteSourceId;
	}
	public String getLaptopBrand() {
		return laptopBrand;
	}
	public void setLaptopBrand(String laptopBrand) {
		this.laptopBrand = laptopBrand;
	}
	public float getCellVoltage() {
		return cellVoltage;
	}
	public void setCellVoltage(float cellVoltage) {
		this.cellVoltage = cellVoltage;
	}
	public List<PhysicalCondition> getCellConditionId() {
		return cellConditionId;
	}
	public void setCellConditionId(List<PhysicalCondition> cellConditionId) {
		this.cellConditionId = cellConditionId;
	}
	public long getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(long recordNo) {
		this.recordNo = recordNo;
	}
	public float getIr() {
		return ir;
	}
	public void setIr(float ir) {
		this.ir = ir;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Cell [cellId=" + cellId + ", date=" + date + ", batchId=" + batchId + ", nominalVoltage="
				+ nominalVoltage + ", voltage=" + voltage + ", cellCapacity=" + cellCapacity + ", outputWh=" + outputWh
				+ ", manufacturerId=" + manufacturerId + ", manufacturerYear=" + manufacturerYear
				+ ", manufacturerMonth=" + manufacturerMonth + ", bmsId=" + bmsId + ", ewasteSourceId=" + ewasteSourceId
				+ ", laptopBrand=" + laptopBrand + ", cellVoltage=" + cellVoltage + ", cellConditionId="
				+ cellConditionId + ", recordNo=" + recordNo + ", ir=" + ir + ", command=" + command + "]";
	}
	
}
