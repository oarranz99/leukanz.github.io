package com.leukanz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.mapping.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fotografia")
public class Fotografia implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String name;
	@Column(name = "fileData", columnDefinition = "LONGBLOB")
	private byte[] fileData;
	@Column(name = "fileType")
	private String fileType;
	
	@Column(name = "fecha")
	private String localDateTime;
	
	@Column(name = "id_album")
	private int id_album;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_album",  insertable = false, updatable = false)
	private Album album;
	
	public Fotografia() {
		
	}
	
	public Fotografia(String name, byte[] fileData, Album id_album) {
		super();
		this.name = name;
		this.fileData = fileData;
		this.album = id_album;
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
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
}
