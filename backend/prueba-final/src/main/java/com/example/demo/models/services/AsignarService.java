package com.example.demo.models.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.ICursoDAO;
import com.example.demo.models.dao.IUsuarioDAO;
import com.example.demo.models.entities.Curso;
import com.example.demo.models.entities.Usuario;

@Service
public class AsignarService {

	
	@Autowired
	private IUsuarioDAO  usuarioDAO;
	
	@Autowired
	private ICursoDAO cursoDAO;
	
	public void addCurso(Long idUsuario, Long idCurso) {
		
		Usuario usuario = usuarioDAO.findById(idUsuario).orElseThrow(  () ->  new RuntimeException("Usuario no encontrado en el proceso de asignar curso"));
		Curso curso = cursoDAO.findById(idCurso).orElseThrow(  () ->  new RuntimeException("Curso no encontrado en el proceso de asignar curso"));

		if (usuario.getCursosInscritos().contains(curso)) {
            throw new RuntimeException("El curso con ID " + idCurso + " ya estaba asignado al usuario con ID " + idUsuario);
        }
		usuario.getCursosInscritos().add(curso);
		usuarioDAO.save(usuario);
	}
	
	public void deleteCurso(Long idUsuario, Long idCurso) {
		
		Usuario usuario = usuarioDAO.findById(idUsuario).orElseThrow(  () ->  new RuntimeException("Usuario no encontrado en el proceso de eliminar  curso"));
		Curso curso = cursoDAO.findById(idCurso).orElseThrow(  () ->  new RuntimeException("Curso no encontrado en el proceso de eliminar  curso"));

		usuario.getCursosInscritos().remove(curso);
		usuarioDAO.save(usuario);
	}
	
	//A partir de un id de Usuario vamos a obtebner sus cursos
	
	public Set<Curso> listarCurso(Long idUsuario) {
		
		Usuario usuario = usuarioDAO.findById(idUsuario).orElseThrow(  () ->  new RuntimeException("Usuario no encontrado en el proceso de listar  cursos"));

		return usuario.getCursosInscritos();
		
		
	}
}
