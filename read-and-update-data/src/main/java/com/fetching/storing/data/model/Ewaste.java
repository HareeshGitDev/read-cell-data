package com.fetching.storing.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ewaste")
public class Ewaste {
	@Id
	private long id;
	private String name;
	private String location;
	@OneToMany(mappedBy="ewasteSourceId")
	private List<Cell> cell;
	
	public List<Cell> getCell() {
		return cell;
	}
	public void setCell(List<Cell> cell) {
		this.cell = cell;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
