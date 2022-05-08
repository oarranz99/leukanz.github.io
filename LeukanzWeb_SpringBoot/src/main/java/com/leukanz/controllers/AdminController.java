package com.leukanz.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.google.gson.Gson;
import com.leukanz.domain.Album;
import com.leukanz.domain.Fotografia;
import com.leukanz.domain.Ilustracion;
import com.leukanz.services.AdminServiceImpl;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {
	
		@Autowired
		private AdminServiceImpl adminService;

	    /**
	    * Éste método se usa para una fotografía en la BD
	    * @param file Objeto -> Objeto tipo MultipartImg con los datos de la imagen
	    * @param nombre -> String, titulo de la imagen
	    * @param lugar -> String, lugar al que pertenece la fotografía
	    * @param provincia -> String, provincia a la que pertenece la fotografía
	    * @return pais -> String, Devuelve un objeto tipo Response en formato JSON
	    * @param id -> int, con el id de la imagen que se quiere modificar
	    * @param album -> int, id del album al que se va a añadir
	     * @throws DataFormatException 
	    */
	   
		@PostMapping("/addFoto")
		public ResponseEntity<?> addFoto(
				@RequestParam("file") MultipartFile myFile, 
				@RequestParam("nombre") String nombre,
				@RequestParam("album") int album
				)throws IOException, SQLException, DataFormatException{
					Fotografia p = new Fotografia(nombre, myFile.getBytes(), adminService.getAlbum(album));
					Object jsonResponse = new Gson().toJson(adminService.saveImg(p));
					return ResponseEntity.ok(jsonResponse);
		}
		@PostMapping("/updateFoto")
		public ResponseEntity<?> updateFoto(
				@RequestParam("file") MultipartFile myFile, 
				@RequestParam("nombre") String nombre, 
				@RequestParam("lugar") String lugar,
				@RequestParam("provincia") String provincia,
				@RequestParam("pais") String pais,
				@RequestParam("id") int id,
				@RequestParam("album") int album
				)throws IOException, SQLException, ProductNotFoundException, IncorrectParametersException, DataFormatException{
					
					//Fill object type MultipartImg with requested parameters
					Fotografia p = new Fotografia();
					p.setName(nombre);
					p.setFileData(myFile.getBytes());
					p.setAlbum(adminService.getAlbum(album));
					//Call productService.saveImg() functions that returns response object type and parses to json
					
					Object jsonResponse = new Gson().toJson(adminService.updateImg(p, id));
					return ResponseEntity.ok(jsonResponse);
		}

		@PostMapping("/deleteFoto")
		public ResponseEntity<?> deleteImage(@RequestParam("id") String id) throws ProductNotFoundException{
			System.out.println("Entra");
			int id2 = Integer.parseInt(id);
			Object jsonResponse = new Gson().toJson(adminService.deleteImg(id2));
			return ResponseEntity.ok(jsonResponse);
		}
		
	    /**
	    * Éste método se usa paramodificar una imagen tanto ilustración como fotografía de la BD
	    * @param file Objeto -> Objeto tipo MultipartImg con los datos de la imagen
	    * @param name -> String con el titulo de la imagen
	    * @param description -> String con la descripción de la imagen
	    * @param type -> String "I" o "F" ilustración o fotografía 
	    * @param id -> String con el id de la foto que va a actualizarse
	    * @return ResponseEntity ->  Devuelve un objeto tipo Response en formato JSON
	     * @throws DataFormatException 
	    */
		@PostMapping("/updateIlustracion")
		public ResponseEntity<?> updateIlustracion(
			@RequestParam("file") MultipartFile myFile, 
			@RequestParam("name") String name,
			@RequestParam("description") String description , 
			@RequestParam("id") int oldId) throws IOException, SQLException, ProductNotFoundException, IncorrectParametersException, DataFormatException{
				Ilustracion p = new Ilustracion();
				p.setName(name);
				p.setDescription(description);
				p.setFileData(myFile.getBytes());
				p.setFileType(myFile.getContentType());
				
				Object jsonResponse = new Gson().toJson(adminService.updateIlustracion(p, oldId));
				return ResponseEntity.ok(jsonResponse);
		}
		@PostMapping("/addIlustracion")
		public ResponseEntity<?> addIlustracion(
				@RequestParam("file") MultipartFile myFile, 
				@RequestParam("name") String name,
				@RequestParam("description") String description
				)throws IOException, SQLException, DataFormatException{
			
					Ilustracion p = new Ilustracion(name, description, myFile.getContentType(),myFile.getBytes());
					Object jsonResponse = new Gson().toJson(adminService.saveIlustracion(p));
					return ResponseEntity.ok(jsonResponse);
		}
		@PostMapping("/deleteIlustracion")
		public ResponseEntity<?> deleteIlustracion(
				@RequestParam("id") int id)throws ProductNotFoundException{
					
					Object jsonResponse = new Gson().toJson(adminService.deleteIlustracion(id));
					return ResponseEntity.ok(jsonResponse);
		}
		@PostMapping("/addAlbum")
		public ResponseEntity<?> addAlbum(
				@RequestParam("file") MultipartFile myFile, 
				@RequestParam("name") String name,
				@RequestParam("description") String description,
				@RequestParam("ubicacion") String ubicacion
				)throws IOException, SQLException, IncorrectParametersException, DataFormatException{
				System.out.println(myFile.getContentType());
					Album p = new Album(name, myFile.getBytes(), description, ubicacion, myFile.getContentType());
					Object jsonResponse = new Gson().toJson(adminService.createAlbum(p));
					return ResponseEntity.ok(jsonResponse);
		}

		@PostMapping("/deleteAlbum")
		public ResponseEntity<?> deleteAlbum(
				@RequestParam("id") int id)throws ProductNotFoundException{
					
					Object jsonResponse = new Gson().toJson(adminService.deleteAlbum(id));
					return ResponseEntity.ok(jsonResponse);
		}
}
