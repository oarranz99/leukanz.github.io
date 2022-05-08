package com.leukanz.services;

import java.io.IOException;
import java.util.zip.DataFormatException;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Album;
import com.leukanz.domain.Fotografia;
import com.leukanz.domain.Ilustracion;
import com.leukanz.response.Response;

public interface AdminService {
	//Fotografias
	Response updateImg(Fotografia newImg , int oldId) throws IncorrectParametersException, ProductNotFoundException;
	Response saveImg(Fotografia p) throws IncorrectParametersException;
	Response deleteImg(int id)throws IncorrectParametersException;
	Response getImages()throws Exception;
	Fotografia getImagen(int i);
	
	//Ilustraciones
	Response getIlustraciones() throws Exception;
	Response deleteIlustracion(int id)throws IncorrectParametersException, ProductNotFoundException;
	Response updateIlustracion(Ilustracion newImg , int oldId )throws IncorrectParametersException, ProductNotFoundException, IOException, DataFormatException;
	Response saveIlustracion(Ilustracion i) throws IncorrectParametersException, IOException, DataFormatException;
	Ilustracion getIlustracion(int id) throws IOException, DataFormatException;
	//Albumes
	Album getAlbum(int id) throws ProductNotFoundException, IOException, DataFormatException;
	Response createAlbum(Album a) throws IncorrectParametersException, IOException, DataFormatException;
	Response deleteAlbum(int a) throws ProductNotFoundException;
	Response updateAlbum(Album newAl, int oldAl) throws ProductNotFoundException, IncorrectParametersException, IOException, DataFormatException;
}
