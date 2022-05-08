package com.leukanz.services;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Album;
import com.leukanz.domain.Fotografia;
import com.leukanz.domain.Ilustracion;
import com.leukanz.jpa.repository.AlbumRepository;
import com.leukanz.jpa.repository.IlustracionesRepository;
import com.leukanz.jpa.repository.ImageRepository;
import com.leukanz.response.Response;
import com.leukanz.utils.ImgConfigUtils;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private ImageRepository imgRepository;
	@Autowired
	private IlustracionesRepository ilusRepository;
	@Autowired
	private AlbumRepository albumRepository;
//	FOTOGRAFIAS
	public Response updateImg(Fotografia newImg , int oldId ){
		Response response = new Response();
			newImg.setFileData(newImg.getFileData());
		
			if(imgRepository.updateFoto(newImg, oldId)) {
				response.setStatus("Ok");
			}else{
				response.setStatus("Error");
				response.setMessage("Can't update Image, error connecting database");
				
			}
		return response;
	}

	public Response saveImg(Fotografia p){
		Response response = new Response();
			 if(p.getFileData() != null) {
				 p.setFileData(p.getFileData());
			 }
			
			//If entity manager persists image
			if(imgRepository.saveFoto(p)) {
				response.setStatus("Ok");
				response.setMessage("Image saved");
			}
			//If entity manager had an error persisting
			else {
				response.setStatus("Error");
				response.setMessage("Error saving image");
			}
		return response;
	}

	public Response deleteImg(int id) throws ProductNotFoundException{
		Response response = new Response();
			if(imgRepository.deleteFoto(id)) {
				response.setStatus("Ok");
				response.setMessage("Image deleted successfully");
			}else {
				response.setStatus("Error");
				response.setMessage("Image not founded on DB");
				throw new ProductNotFoundException("Image not found");
			}
		return response;
	}

	public Response getImages() {
		Response response = new Response();
		response.setData(imgRepository.getFotos());
		response.setStatus("Ok");
		return response;
	}
	
	public Fotografia getImagen(int i ) {
		return imgRepository.getImagen(i);
	}

//ALBUMES
	public Response createAlbum(Album a) throws IncorrectParametersException, IOException, DataFormatException {
		Response response = new Response();
		if(albumRepository.createAlbum(a)) {
			a.setFileData(ImgConfigUtils.compress(a.getFileData()));
		}
		return response;
	}
	public Album getAlbum(int id) throws IOException, DataFormatException {
		return albumRepository.getAlbum(id);
	}
	public Response getAllAlbums() throws IOException, DataFormatException {
		Response response = new Response();
		response.setData(albumRepository.getAllAlbums());
		response.setStatus("Ok"); 
		return response;
	}
	@Override
	public Response deleteAlbum(int a) throws ProductNotFoundException {
		Response response = new Response();
		if(albumRepository.deleteAlbum(a)) {
			response.setStatus("Ok");
			response.setMessage("Album deleted");
		}
		//If entity manager had an error deleting
		else {
			response.setStatus("Error");
			response.setMessage("Error deleting album");
		}
		return response;
	}

	@Override
	public Response updateAlbum(Album newAl, int oldAl) throws ProductNotFoundException, IncorrectParametersException, IOException, DataFormatException {
		Response response = new Response();
		newAl.setFileData(newAl.getFileData());
			if(albumRepository.updateAlbum(newAl, oldAl)) {
				response.setStatus("Ok");
			}else{
				response.setStatus("Error");
				response.setMessage("Can't update Album, error has occurred");
			}
		
		return response;
	}
//	ILUSTRACIONES
	public Response getIlustraciones() {
		Response response = new Response();
		response.setData(ilusRepository.getIlustraciones());
		response.setStatus("Ok");
		return response;
	}
	
	public Response deleteIlustracion(int id) {
		Response response = new Response();
		if(ilusRepository.deleteIlustracion(id)) {
			response.setStatus("Ok");
			response.setMessage("Image deleted successfully");
		}else {
			response.setStatus("Error");
			response.setMessage("Image not founded on DB");
		}
		return response;
	}
	
	public Response updateIlustracion(Ilustracion newImg , int oldId ) throws IOException, DataFormatException {
		Response response = new Response();
			if(ilusRepository.updateIlustracion(newImg, oldId)) {
				response.setStatus("Ok");
			}else{
				response.setStatus("Error");
				response.setMessage("Can't update Image, error connecting database");
			}
		return response;
	}

	public Response saveIlustracion(Ilustracion i) throws IOException, DataFormatException {
		Response response = new Response();
			//If entity manager persists image
			if(ilusRepository.saveIlustracion(i)) {
				response.setStatus("Ok");
				response.setMessage("Image saved");
			}
			//If entity manager had an error persisting
			else {
				response.setStatus("Error");
				response.setMessage("Error saving image");
			}
			return response;
		
	}

	public Ilustracion getIlustracion(int id) throws IOException, DataFormatException {
		return ilusRepository.getIlustracion(id);
	}
}
