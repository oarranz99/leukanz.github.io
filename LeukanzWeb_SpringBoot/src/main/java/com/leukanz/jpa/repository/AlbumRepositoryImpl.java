package com.leukanz.jpa.repository;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.persistence.EntityManager;
import javax.persistence.TableGenerator;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.errors.products.IncorrectParametersException;
import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Album;
import com.leukanz.domain.Ilustracion;
import com.leukanz.utils.ImgConfigUtils;

@Repository
@Transactional
public class AlbumRepositoryImpl implements AlbumRepository{
	
	@Autowired protected EntityManager em;
	
	@Override
	public boolean createAlbum(Album a) throws IncorrectParametersException {
		a.setLocalDateTime(ImgConfigUtils.getRealTimeDate());
		em.persist(a);
		return false;
	}

	@Override
	public Album getAlbum(int id) throws IOException, DataFormatException  {
		Album al = em.find(Album.class, id);
		if(al == null) {
			throw new ProductNotFoundException();
		}
		//al.setFileData(ImgConfigUtils.decompress(al.getFileData()));
		return al;
	}

	@Override
	public boolean deleteAlbum(int a) {
		Album al = em.find(Album.class, a);
		if(al == null) {
			throw new ProductNotFoundException();
		}else {
			em.remove(al);
		}
		
		return false;
	}

	@Override
	public boolean updateAlbum(Album newAl, int oldAl) throws ProductNotFoundException, IncorrectParametersException {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
   	 
        // create update
        CriteriaUpdate<Album> update = cb.createCriteriaUpdate(Album.class);
 
        // set the root class
        Root<Album> e = update.from(Album.class);
 
        // set update and where clause !!<poner aqui los campios >!!
        update.set("name", newAl.getName());
        update.set("fileData", newAl.getFileData());
        update.set("descripcion", newAl.getDescripcion());
        update.set("fileType", newAl.getFileType());
        
        update.where(cb.greaterThanOrEqualTo(e.get("id"), oldAl));
 
        // perform update
        this.em.createQuery(update).executeUpdate();
        
		return true;
	}

	@Override
	public List<Album> getAllAlbums() throws IOException, DataFormatException {
		List<Album> albumList = null;
		// Creamoas peticion criteria
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Album> cq = cb.createQuery(Album.class);
        
        //Root define la query y lo que llevara dentro
        Root<Album> from = cq.from(Album.class);
        
        //Creamos la peticion select where, reamos la query y recogemos
        CriteriaQuery<Album> all = cq.select(from);
        
        TypedQuery<Album> allQuery = em.createQuery(all);
        albumList = allQuery.getResultList();
        for (int i = 0; i < albumList.size(); i++) {
        	albumList.get(i).setFileData(albumList.get(i).getFileData());;
        }
		return albumList;
	}

}
