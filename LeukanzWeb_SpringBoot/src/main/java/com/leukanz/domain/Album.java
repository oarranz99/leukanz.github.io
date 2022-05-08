package com.leukanz.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "imagen")
	private byte[] fileData;
	
	@Column(name = "time", columnDefinition = "TIMESTAMP")
	private String localDateTime;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column
	private String ubicacion;
	
	@Column(name = "fileType")
	private String fileType;
	
	@OneToMany(mappedBy="album")
	@JsonIgnore
	private List<Fotografia> fotos;

	public Album() {
		// TODO Auto-generated constructor stub
	}
	
	public Album(String name, byte[] fileData, String descripcion,String ubicacion, String fileType) {
		super();
		this.name = name;
		this.fileData = fileData;
		this.descripcion = descripcion;
		this.fileType = fileType;
		this.ubicacion = ubicacion;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileType() {
		return fileType;
	} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Fotografia> getFotos() {
		return fotos;
	}

	public void setFotos(List<Fotografia> fotos) {
		this.fotos = fotos;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	

}
