package com.leukanz.jpa.repository;

import java.util.List;

import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Fotografia;

public interface ImageRepository {
	
	public boolean saveFoto(Fotografia p) ;
	public List<Fotografia> getFotos();
	public boolean updateFoto(Fotografia newImg , int oldId ) ;
	public boolean deleteFoto(int id) throws ProductNotFoundException;
	public Fotografia getImagen(int id);
	public List<Fotografia> getFotosFiltered(String searchbar, int[]  albumList,  String orderBy, String ascDesc);
}
