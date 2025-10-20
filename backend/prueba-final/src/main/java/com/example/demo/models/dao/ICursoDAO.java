package com.example.demo.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.entities.Curso;

@Repository
public interface ICursoDAO extends CrudRepository<Curso, Long>{

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
	
	
	
	List<Curso> findByActivo(Integer activo);
}
