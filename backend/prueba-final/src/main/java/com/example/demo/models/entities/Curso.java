package com.example.demo.models.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="cursos")
public class Curso implements Serializable{

	
	private static final long serialVersionUID = 7731679797860315078L;

	
	//Creamos las tablas en la base de datos para la identidad Curso con su id autoinncremental
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)//Usamos IDENTITY para autoincremental en MySQL
		@Column
		private Long id;
		
		@Column
		private String nombre;
		
		@Column
		private String duracion;
		
		@Column
		private Integer activo=1;
		

		//Inversa de la relacion
		@ManyToMany(mappedBy = "cursosInscritos")
		private Set<Usuario> usuariosQueCursan;
		
		
		
		
		
		
		
		
		
		
		
		
		
}
