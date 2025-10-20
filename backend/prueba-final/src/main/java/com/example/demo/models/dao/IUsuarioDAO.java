package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.entities.Usuario;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario,Long>{

	
		//////////////////////////////////////////////////////////////
		//    Propósito	               Método                       //
	    //                                                          // 
		//    Todos los registros	  findAll()                     //
	    //                                                          //
		//    Por ID	              findById(Long id)             //
	    //                                                          //
		//    Por campo específico	  findByNombre(String nombre)   //
	    //                                                          //
		//    Por campo booleano	  findByActivoTrue()            //
	    //                                                          //
		//    Por campo numérico	  findByActivo(Integer activo)  //
	    //                                                          //
		//////////////////////////////////////////////////////////////
	
	
	List<Usuario> findByActivo(Integer activo);

	
}
