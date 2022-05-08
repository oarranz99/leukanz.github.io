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
import com.leukanz.domain.Ilustracion;
import com.leukanz.utils.ImgConfigUtils;

@Repository
public class IlustracionesRepositoryImpl implements IlustracionesRepository{
	
		@Autowired protected EntityManager em;
		
		
		
		/**
	    * Este metodo se usa para recoger todo el contenido de Ilustraciones de la BD
	    * @return List<MultiPartImg> -> Esto devuelve una lista de los elementos obtenidos
	    */
		
	    @Transactional
		public List<Ilustracion> getIlustraciones(){
	    		List<Ilustracion> productList = null;
		    		// Creamoas peticion criteria
		            CriteriaBuilder cb = em.getCriteriaBuilder();
		            CriteriaQuery<Ilustracion> cq = cb.createQuery(Ilustracion.class);
		            
		            //Root define la query y lo que llevara dentro
		            Root<Ilustracion> from = cq.from(Ilustracion.class);
		            
		            //Creamos la peticion select where...
		            CriteriaQuery<Ilustracion> all = cq.select(from);
		            //Creamos la query
		            TypedQuery<Ilustracion> allQuery = em.createQuery(all);
		            //Recogemos los datos de la bd con el array
		            productList = allQuery.getResultList();
		            
	          
	            return productList;
		}

	    /**
	     * Este metodo se usa para editar una ilustracion de la BD
	     * @return boolean -> Devuelve true si los datos de han modificado
	     * @throws DataFormatException 
	     * @throws IOException 
	    */

	    @Transactional
		public boolean updateIlustracion(Ilustracion newIlus, int oldId) throws IOException, DataFormatException {
	    		
			if(newIlus.getFileData() != null) {
				newIlus.setFileData(ImgConfigUtils.compress(newIlus.getFileData()));
			}
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
	    	 
	        // create update
	        CriteriaUpdate<Ilustracion> update = cb.createCriteriaUpdate(Ilustracion.class);
	  
			// set the root class
			Root<Ilustracion> e = update.from(Ilustracion.class);
			
			
			//update.set("nombre",variable)
			update.set("description", newIlus.getDescription());
			update.set("name", newIlus.getName());
			update.set("fileType", newIlus.getFileType());
			update.set("fileData", newIlus.getFileData());
			
			update.where(cb.greaterThanOrEqualTo(e.get("id"), oldId));
			  
			         // perform update
			 this.em.createQuery(update).executeUpdate();
	         
	         return true;

		}



	    @Transactional
		public boolean deleteIlustracion(int id){
			Ilustracion dbImg = em.find(Ilustracion.class, id);
	    	 if(dbImg != null) {
	    		 em.remove(dbImg);
	    		 return true;
	    	 }
	    	 else {
	    		 throw new ProductNotFoundException();
	    	 }
		}

	    @Transactional
		public boolean saveIlustracion(Ilustracion p) throws IOException, DataFormatException{
	    	p.setLocalDateTime(ImgConfigUtils.getRealTimeDate());
			em.persist(p);
			return true;
		}

		@Override
		public Ilustracion getIlustracion(int id) throws IOException, DataFormatException {
			Ilustracion ilus = em.find(Ilustracion.class, id);
			
			return ilus;
		}

		@Override
		public List<Ilustracion> getIlustracionesFiltered(String searchbar, String orderBy, String ascDesc) {
			List<Ilustracion> productList = null;
			
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ilustracion> cq = cb.createQuery(Ilustracion.class);
            
            Root<Ilustracion> from = cq.from(Ilustracion.class);
            
            //Creamos la peticion select where...
            CriteriaQuery<Ilustracion> all = cq.select(from);
            cq.where(cb.equal(from.get("name"), searchbar));
            if(ascDesc == "asc") {
	       		cq.orderBy(cb.asc(from.get(orderBy)));
	       	}
	       	else {
	       		cq.orderBy(cb.desc(from.get(orderBy)));
	       	}
            //Creamos la query
            TypedQuery<Ilustracion> allQuery = em.createQuery(all);
            //Recogemos los datos de la bd con el array
            productList = allQuery.getResultList();
           
      
        return productList;
		}


}
