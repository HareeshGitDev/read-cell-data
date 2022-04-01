package com.fetching.storing.data.dto;

import java.time.Instant;

public class CellDto {
	private long cellId;
	private Instant date;
	private String batchId;
	private float nominalVoltage;
	private float voltage;
	private long cellCapacity;
	private long outputWh;
	private String manufacturerId;
	private int manufacturerYear;
	private int manufacturerMonth;
	private String bmsId;
	private String ewasteSourceId;
	private String laptopBrand;
	private float cellVoltage;
	private String cellConditionId;
	private long recordNo;
	private float ir;
	private String command;
	
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batch) {
		this.batchId = batch;
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

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
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

	public String getBmsId() {
		return bmsId;
	}

	public void setBmsId(String bmsId) {
		this.bmsId = bmsId;
	}

	public String getEwasteSourceId() {
		return ewasteSourceId;
	}

	public void setEwasteSourceId(String ewasteSourceId) {
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

	public String getCellConditionId() {
		return cellConditionId;
	}

	public void setCellConditionId(String cellConditionId) {
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
		return "CellDto [cellId=" + cellId + ", date=" + date + ", batch=" + batchId + ", nominalVoltage="
				+ nominalVoltage + ", voltage=" + voltage + ", cellCapacity=" + cellCapacity + ", outputWh=" + outputWh
				+ ", manufacturerId=" + manufacturerId + ", manufacturerYear=" + manufacturerYear
				+ ", manufacturerMonth=" + manufacturerMonth + ", bmsId=" + bmsId + ", ewasteSourceId=" + ewasteSourceId
				+ ", laptopBrand=" + laptopBrand + ", cellVoltage=" + cellVoltage + ", cellConditionId="
				+ cellConditionId + ", recordNo=" + recordNo + ", ir=" + ir + ", command=" + command + "]";
	}

	
}
