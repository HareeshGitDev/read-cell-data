package com.fetching.storing.data.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="physical_condition")
public class PhysicalCondition {
	@Id
	private long id;
	private String name;
	private int type;
	
	public float getId() {
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PhysicalCondition [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
