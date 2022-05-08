package com.leukanz.jpa.repository;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Album;

public interface AlbumRepository {
	boolean createAlbum(Album a) throws IncorrectParametersException;
	Album getAlbum(int id) throws IOException, DataFormatException, ProductNotFoundException ;
	boolean deleteAlbum(int a) throws ProductNotFoundException;
	boolean updateAlbum(Album newAl, int oldAl) throws ProductNotFoundException, IncorrectParametersException;
	List<Album> getAllAlbums()throws IOException, DataFormatException;
}
