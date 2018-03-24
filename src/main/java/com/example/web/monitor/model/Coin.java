package com.example.web.monitor.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String code;
	String description;
	Timestamp timestamp;
	double value;

	public Coin() {
		this.code = "";
		this.description = "";
		new Timestamp(new Date().getTime());
		this.value = 0;

	}

	public Coin(String code) {
		this.code = code;

		this.description = "";
		this.timestamp = new Timestamp(new Date().getTime());
		this.value = 0;
	}

	public Coin(String code2, double value) {
		this(code2);
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		 StringBuilder sb=new StringBuilder();
		 sb.append("Coin code: ").append(code).append(", value: ").append(value);
		return sb.toString();
	}

}
