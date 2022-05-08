package com.leukanz.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "ilustraciones")
public class Ilustracion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	 
	@Column(name = "fileType")
	private String fileType;
	
	@Column(name = "fileData")
	private byte[] fileData;
	
	@Column(name = "fecha")
	private String localDateTime;
	
	public Ilustracion() {
		// TODO Auto-generated constructor stub
	}
	
	public Ilustracion(String name, String description, String fileType, byte[] fileData) {
		
		this.name = name;
		this.description = description;
		this.fileType = fileType;
		this.fileData = fileData;
	}

	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	
}