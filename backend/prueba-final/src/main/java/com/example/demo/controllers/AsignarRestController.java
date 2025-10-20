package com.example.demo.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.models.services.AsignarService;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class AsignarRestController {

	
	@Autowired
	private  AsignarService asignarService ;
	
	
	///////////////////////////////////
	/* ASIGNAMOS EL CURSO AL USUARIO */
	///////////////////////////////////
	
	@PostMapping("/estudiante/{idEstudiante}/curso/{idCurso}")
    public ResponseEntity<?> asignarCurs(@PathVariable  Long idEstudiante,@PathVariable  Long idCurso) {
	
	Map<String, Object> response = new LinkedHashMap<>();
	
	try {
		
		asignarService.addCurso(idEstudiante, idCurso);
	
	}catch(RuntimeException  e) {
			
		response.put("mensaje", e.getMessage());
		return new ResponseEntity< Map<String, Object> >(response,HttpStatus.BAD_REQUEST );//400
		
	}
	
	response.put("mensaje", "El curso se asigno correctamente al estudiante con el id " + idEstudiante );
	return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
    }

	
		
	///////////////////////////////////
	/* ELIMINAR  EL CURSO AL USUARIO */
	///////////////////////////////////
	
	@DeleteMapping("/estudiante/{idEstudiante}/curso/{idCurso}")
    public ResponseEntity<?> delete(@PathVariable  Long idEstudiante,@PathVariable  Long idCurso) {
	
	Map<String, Object> response = new LinkedHashMap<>();
	
	
	try {
		
		asignarService.deleteCurso(idEstudiante, idCurso);
	
	} catch (RuntimeException e) {
        response.put("mensaje", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
	
	response.put("mensaje", "Curso eliminado correctamente");
	return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
}
	
	
	
	///////////////////////////////////
	/* 	LISTAR CURSO AL USUARIO      */
	///////////////////////////////////
	
	@GetMapping("/estudiante/{idEstudiante}/cursos")
    public ResponseEntity<?> list(@PathVariable  Long idEstudiante) {
	
	Map<String, Object> response = new LinkedHashMap<>();
	
	
	try {
		
		Object cursos = asignarService.listarCurso(idEstudiante);
        response.put("datos", cursos);
	
	}catch (RuntimeException e) {
        response.put("mensaje", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    } 
	
	response.put("mensaje", "Los cursos se listaron correctamente");
	return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
}
	
	
	//Nota estudiar más a fondo como manejar las excepciones XD
	
	
}
