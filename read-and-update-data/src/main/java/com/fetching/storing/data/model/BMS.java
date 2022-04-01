package com.fetching.storing.data.model;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name="bms")
@TypeDef(
		name="jsonb",
		 typeClass = JsonBinaryType.class
		)
public class BMS {
	@Id
	private long id;
	@Type(type="jsonb")
	private Map<String,String> data;
	private Instant time;
	@OneToMany(mappedBy="bmsId")
	private List<Cell> cell;
	
	public BMS(long id, Map<String, String> data) {
		super();
		this.id = id;
		this.data = data;
	}
	public BMS() {
		
	}
	public List<Cell> getBms_id() {
		return cell;
	}
	public void setBms_id(List<Cell> cell) {
		this.cell = cell;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}	
}
