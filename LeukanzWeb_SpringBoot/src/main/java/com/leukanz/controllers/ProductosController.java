package com.leukanz.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.leukanz.response.Response;
import com.leukanz.services.ProductServiceImpl;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductosController {
	
	@Autowired
	private ProductServiceImpl productService;
	
    /**
    * Éste método se usa para insertar una imagen tanto ilustración como fotografía en la BD
    * @param file Objeto -> Objeto tipo MultipartImg con los datos de la imagen
    * @param name -> String con el titulo de la imagen
    * @param description -> String con la descripción de la imagen
    * @param type -> String "I" o "F" ilustración o fotografía 
    * @return ResponseEntity ->  Devuelve un objeto tipo Response en formato JSON
    */

	@GetMapping("/getIlustraciones")
	public ResponseEntity<?> getIlustraciones()throws IOException, SQLException{
		ResponseEntity<Response> entity = new ResponseEntity<>(productService.getIlustraciones(),HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getFotografias")
	public ResponseEntity<?> getFotografias()throws IOException, SQLException {
		ResponseEntity<Response> entity = new ResponseEntity<>(productService.getFotos(),HttpStatus.OK);
		return entity;
	}
	@GetMapping("/getAlbumes")
	public ResponseEntity<?> getAllAlbums()throws IOException, SQLException, DataFormatException {
		ResponseEntity<Response> entity = new ResponseEntity<>(productService.getAllAlbums(),HttpStatus.OK);
		return entity;
		
		
	}
}
