package com.leukanz.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import com.leukanz.response.Response;

public interface ProductService {
	public Response getIlustracionesFiltered(String searchbar, String orderBy, String ascDesc)  throws SQLException ;
	public Response getFotos() throws SQLException;
	public Response getFotosFiltered(String searchbar, int[] albumList, String orderBy, String ascDesc) throws SQLException;
	public Response getAllAlbums() throws SQLException, IOException, DataFormatException;
}
