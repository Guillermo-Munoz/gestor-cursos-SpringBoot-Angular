package com.example.demo.controllers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entities.Usuario;
import com.example.demo.models.services.IUsuarioService;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;
    
    
	///////////////////////////////////
	/*  LISTAR ESTUDIANTES ACTIVOS   */
	///////////////////////////////////
    
    @GetMapping("/estudiantes")
        public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new LinkedHashMap<>();
		List<Usuario> resultado = new ArrayList<>();
		
		try {
			
			 resultado = usuarioService.findAllActive();
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda de todos los estudiantes en la BBDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.INTERNAL_SERVER_ERROR );//500
			
		}
		
		response.put("mensaje", "El listado de todos los estudiantes se ha efectuado con éxito");
		response.put("datos", resultado);
		return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
	}
    
    
	///////////////////////////////////
	/* 	BUSCAR ESTUDIANTE POR ID     */
	///////////////////////////////////

	@GetMapping("/estudiante/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Map<String, Object> response = new LinkedHashMap<>();
		Usuario resultado = null;
		
		try {
			
			resultado =  usuarioService.findById(id);
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la búsqueda del Estudiante con id: " + id + " en la BBDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.INTERNAL_SERVER_ERROR );//500
			
		}
		
		response.put("mensaje", "La búsqueda del Estudiante con id: " + id + " se ha efectuado con éxito");
		response.put("datos", resultado);
		return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
		
	}
	
	///////////////////////////////////
	/*   	CREAR ESTUDIANTE         */
	///////////////////////////////////
	
	@PostMapping("/estudiante")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		
		Map<String, Object> response = new LinkedHashMap<>();
		Usuario resultado = null;
		
		try {
			
			resultado =  usuarioService.save(usuario);
		
		}catch(DataIntegrityViolationException e) {
				
			response.put("mensaje", "Error al intentar crear el Estudiante en la BBDD porque probablemente ya exista");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.BAD_REQUEST );//400
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al intentar crear el Estudiante en la BBDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.INTERNAL_SERVER_ERROR );//500
			
		}
		
		response.put("mensaje", "El Estudiante se ha creado con éxito");
		response.put("datos", resultado);
		return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
	}
   
		
	///////////////////////////////////
	/*   ACTUALIZAR ESTUDIANTES      */
	///////////////////////////////////
	
	@PutMapping("/estudiante")
	public ResponseEntity<?> update(@RequestBody Usuario usuario) {
		
		Map<String, Object> response = new LinkedHashMap<>();
		Usuario resultado = null;
		
		try {
			
			resultado =  usuarioService.save(usuario);
		
		}catch(DataIntegrityViolationException e) {
				
			response.put("mensaje", "Error al intentar modificar el Estudiante en la BBDD porque probablemente ya exista");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.BAD_REQUEST );//400
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al intentar crear el Estudiante en la BBDD");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response,HttpStatus.INTERNAL_SERVER_ERROR );//500
			
		}
		
		response.put("mensaje", "El Estudiante se ha modificado con éxito");;
		response.put("datos", resultado);
		return new ResponseEntity<  Map<String,  Object > >(response,HttpStatus.OK);//200
	}


	
	///////////////////////////////////
	/*   ELIMINAR ESTUDIANTES       */
	///////////////////////////////////
	
	@DeleteMapping("/estudiante/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {

	    Map<String, Object> response = new LinkedHashMap<>();
	    Usuario estudiante = null;

	    try {
	     
	        estudiante = usuarioService.findById(id);

	        if (estudiante == null) {
	            response.put("mensaje", "No se ha encontrado ningún Estudiante con el id: " + id);
	            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); // 404
	        }
	        
            //EN ESTE CASO PROCEDEMOS A RELIZAR UN BORRADO LOGICO, EN VEZ DE ELIMNAR AL USUARIO DE LA BBDD, CAMBIAMOS A INACTIVO
	        estudiante.setActivo(0);
	        usuarioService.save(estudiante);

	    } catch (DataIntegrityViolationException e) {

	        response.put("mensaje", "Error al intentar eliminar el Estudiante en la BBDD (restricciones de integridad)");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); // 400

	    } catch (DataAccessException e) {

	        response.put("mensaje", "Error al intentar eliminar el Estudiante en la BBDD");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
	    }

	    response.put("mensaje", "El Estudiante con id " + id + " se ha marcado como inactivo correctamente");
	    response.put("datos", estudiante);

	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); // 200
	}
	
	

	
	
	
	}
	
	
