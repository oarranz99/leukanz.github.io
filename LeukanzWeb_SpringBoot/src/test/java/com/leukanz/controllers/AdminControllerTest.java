package com.leukanz.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Album;
import com.leukanz.domain.Fotografia;
import com.leukanz.domain.Ilustracion;
import com.leukanz.response.Response;
import com.leukanz.services.AdminServiceImpl;

@SpringBootTest
public class AdminControllerTest {
	
    @Autowired
    AdminServiceImpl adminServiceMock;
     
    public int a = 20;// Changing number for deleting album prove
    public final int b = 15; // Fixed album for testing not deleting;
    public final int i = 1;//Fotografias
    public final int ilus = 3; //Ilustraciones
    
    //Albums
	@Test
	public void testInsertAlbum() throws IOException, IncorrectParametersException, DataFormatException {
	      Album a = new Album();
	      a.setName("Album 1");
	      a.setDescripcion("Desc album 1");
	      a.setFileType("Jpeg");
	      adminServiceMock.createAlbum(a);
	      
	      assertNotNull(adminServiceMock.getAlbum(15));
	}
	@Test 
	public void testGetAlbum() throws IOException, DataFormatException {
		Album album = adminServiceMock.getAlbum(this.b);
		assertSame(album.getClass(), Album.class);
	}
	@Test
	public void testDeleteAlbum() throws IOException, DataFormatException, ProductNotFoundException {
		adminServiceMock.deleteAlbum(this.a);
		
		assertThrows(ProductNotFoundException.class,() -> {
			adminServiceMock.getAlbum(this.a);
		});
	}
	@Test
	public void testUpdateAlbum() throws IOException, IncorrectParametersException, DataFormatException {
		Album alb = adminServiceMock.getAlbum(b);			
	    alb.setDescripcion("Desc album 1 modificado al 4");  
	    
	    assertNotNull(adminServiceMock.updateAlbum(alb, this.b));
	}
	
	//Fotografias
	@Test
	public void testInsertImg() throws IOException, IncorrectParametersException, DataFormatException {
		Fotografia f = new Fotografia();
		f.setName("Foto 1");
		f.setAlbum(adminServiceMock.getAlbum(b));
		adminServiceMock.saveImg(f);
  
  assertNotNull(adminServiceMock.getImagen(i));
	}
	@Test 
	public void testGetImages() throws IOException, DataFormatException {
		assertNotNull(adminServiceMock.getImages());
	}
	@Test
	public void testUpdateImg() throws IOException, IncorrectParametersException, DataFormatException {		
	    Fotografia fot =  adminServiceMock.getImagen(i); 
	    assertNotNull(adminServiceMock.updateImg(fot, i));
	}
	@Test
	public void testDeleteImg() throws IOException, DataFormatException, ProductNotFoundException {
		Response resp = adminServiceMock.deleteImg(this.i);
		assertSame("Ok" , resp.getStatus());
	}
	
	//Ilustraciones
	@Test
	public void testInsertIlus() throws IOException, IncorrectParametersException, DataFormatException {
		Ilustracion i = new Ilustracion();
	    i.setName("Ilustracion de Velita");
	    i.setFileType("jpeg");
	    i.setDescription("Descripcion Velita");
	    adminServiceMock.saveIlustracion(i);
	    assertNotNull(adminServiceMock.getIlustracion(ilus));
	}
	@Test 
	public void testGetIlus() throws IOException, DataFormatException {
		assertNotNull(adminServiceMock.getIlustracion(ilus));
	}
	@Test
	public void testUpdateIlus() throws IOException, IncorrectParametersException, DataFormatException {
		Ilustracion i = adminServiceMock.getIlustracion(ilus);
		i.setDescription("Ilustracion modificada");
		assertNotNull(adminServiceMock.updateIlustracion(i, ilus));
	}
	@Test
	public void testDeleteIlus() throws IOException, DataFormatException, ProductNotFoundException {
		adminServiceMock.deleteIlustracion(ilus);
		assertThrows(ProductNotFoundException.class,() -> {
			adminServiceMock.getIlustracion(ilus);
		});
	}
}

