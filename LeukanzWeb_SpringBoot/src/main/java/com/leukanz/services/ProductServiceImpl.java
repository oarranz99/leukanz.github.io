package com.leukanz.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leukanz.domain.Ilustracion;
import com.leukanz.jpa.repository.AlbumRepository;
import com.leukanz.jpa.repository.IlustracionesRepository;
import com.leukanz.jpa.repository.ImageRepository;
import com.leukanz.response.Response;
import com.leukanz.utils.ImgConfigUtils;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ImageRepository imgRepository;
	@Autowired
	private IlustracionesRepository iluRepository;
	@Autowired
	private AlbumRepository albumRepository;
	

	public Response getIlustraciones()  throws SQLException {
		Response response = new Response();
		response.setData(iluRepository.getIlustraciones());
		response.setStatus("Ok");
		return response;
	}
	public Response getFotos() throws SQLException{
		Response response = new Response();
		response.setData(imgRepository.getFotos());
		response.setStatus("Ok");
		return response;
	}
	public Response getAllAlbums() throws SQLException, IOException, DataFormatException{
		Response response = new Response();
		response.setData(albumRepository.getAllAlbums());
		response.setStatus("Ok");
		return response;
	}
	public Response getIlustracionesFiltered(String searchbar, String orderBy, String ascDesc) throws SQLException {
		Response response = new Response();
		response.setData(iluRepository.getIlustracionesFiltered(searchbar, orderBy, ascDesc));
		response.setStatus("Ok");
		return response;
	}
	@Override
	public Response getFotosFiltered(String searchbar, int[] albumList, String orderBy, String ascDesc)  throws SQLException {
		Response response = new Response();
		response.setData(imgRepository.getFotosFiltered(searchbar, albumList, orderBy, ascDesc));
		response.setStatus("Ok");
		return response;
	}
		
}	

