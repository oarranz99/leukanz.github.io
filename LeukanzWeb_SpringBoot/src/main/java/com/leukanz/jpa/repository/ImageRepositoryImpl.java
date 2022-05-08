package com.leukanz.jpa.repository;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.errors.products.ProductNotFoundException;
import com.leukanz.domain.Fotografia;
import com.leukanz.utils.ImgConfigUtils;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
	
	@Autowired protected EntityManager em;

  
    @Transactional
	public boolean saveFoto(Fotografia p) {
		// TODO Auto-generated method stub
    	p.setLocalDateTime(ImgConfigUtils.getRealTimeDate());
		em.persist(p);
		return true;
	}
    
    /**
    * Este metodo se usa para recoger todo el contenido de Fotografia de la BD
   	* @return List<MultiPartImg> -> Esto devuelve una lista de los elementos obtenidos
   	*/
    @Transactional
    public List<Fotografia> getFotos() {
    	List<Fotografia> imageList = null;
	    	// Creamoas peticion criteria
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Fotografia> cq = cb.createQuery(Fotografia.class);
	        
	        //Root define la query y lo que llevara dentro
	        Root<Fotografia> from = cq.from(Fotografia.class);
	        
	        //Creamos la peticion select where, reamos la query y recogemos
	        CriteriaQuery<Fotografia> all = cq.select(from);
	        
	        TypedQuery<Fotografia> allQuery = em.createQuery(all);
	        imageList = allQuery.getResultList();
//	        	
//	        }
    	return imageList;
    }
    
    
    /**
     * Este metodo se usa para editar una fotografía de la BD
    	* @return boolean -> Devuelve true si los datos de han modificado
     * @throws IOException 
    	*/
     @Transactional
     public boolean updateFoto(Fotografia newImg , int oldId ){
    	 CriteriaBuilder cb = this.em.getCriteriaBuilder();
    	 
         // create update
         CriteriaUpdate<Fotografia> update = cb.createCriteriaUpdate(Fotografia.class);
  
         // set the root class
         Root<Fotografia> e = update.from(Fotografia.class);
  
         // set update and where clause !!<poner aqui los campios >!!
         update.where(cb.greaterThanOrEqualTo(e.get("id"), oldId));
  
         // perform update
         this.em.createQuery(update).executeUpdate();
         
         return true;

     }

     @Transactional
     public boolean deleteFoto(int id) throws ProductNotFoundException{
    	 Fotografia dbImg = em.find(Fotografia.class, id);
    	 if(dbImg != null) {
    		 em.remove(dbImg);
    		 return true;
    	 }
    	 else {
    		 return false;
    	 }
     }

	@Override
	public Fotografia getImagen(int id) {
		// TODO Auto-generated method stub
		Fotografia foto = em.find(Fotografia.class, id);
		if(foto == null) {
			throw new ProductNotFoundException();
		}
		return foto;
	}

	@Override
	public List<Fotografia> getFotosFiltered(String searchbar, int[] albumList, String orderBy, String ascDesc) {
		List<Fotografia> imageList = null;
		try {
	    	// Creamoas peticion criteria
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Fotografia> cq = cb.createQuery(Fotografia.class);
	        
	        //Root define la query y lo que llevara dentro
	        Root<Fotografia> from = cq.from(Fotografia.class);
	        
	        //Creamos la peticion select where, reamos la query y recogemos
	        CriteriaQuery<Fotografia> all = cq.select(from);
	       	cq.where(cb.equal(from.get("name"), searchbar));
	       	if(ascDesc == "asc") {
	       		cq.orderBy(cb.asc(from.get(orderBy)));
	       	}
	       	else {
	       		cq.orderBy(cb.desc(from.get(orderBy)));
	       	}
	        
	        TypedQuery<Fotografia> allQuery = em.createQuery(all);
	        imageList = allQuery.getResultList();
//	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return imageList;
	}

	
}
