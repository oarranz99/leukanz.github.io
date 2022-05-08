package com.leukanz.jpa.repository;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import com.leukanz.domain.Fotografia;
import com.leukanz.domain.Ilustracion;

public interface IlustracionesRepository {
	
	public List<Ilustracion> getIlustraciones();
	public boolean updateIlustracion(Ilustracion newImg , int oldId ) throws IOException, DataFormatException;
	public boolean deleteIlustracion(int id) ;
	public boolean saveIlustracion(Ilustracion p) throws IOException, DataFormatException;
	public Ilustracion getIlustracion(int id) throws IOException, DataFormatException;
	public List<Ilustracion> getIlustracionesFiltered(String searchbar, String orderBy, String ascDesc);
}
