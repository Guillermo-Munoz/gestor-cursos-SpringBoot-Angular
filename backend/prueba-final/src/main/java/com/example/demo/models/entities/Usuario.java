package com.example.demo.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name="usuarios")
public class Usuario implements Serializable{

	/*SE ENTIENDE QUE LOS DATOS DEL USUAIRO PODRIAN SER MÁS SI SE NECESITAN....APELLIDOS....TELÉFONO...ETC*/
	
	private static final long serialVersionUID = 7897240702219189726L;

	//Creamos las tablas en la base de datos para la identidad Usuario con su id autoinncremental
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Usamos IDENTITY para autoincremental en MySQL
	@Column
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String password;
	
	@Column(unique=true , nullable = false)
	private String email;
	
	/*TODO añadir array para mostrar cursos del usuario??*/
	
	@Column
	private String rol = "ROLE_USER";
	
	@Column
	private Integer activo=1;
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "usuario_cursos",
			joinColumns = { @JoinColumn(name="usuario_id")},
			inverseJoinColumns = {@JoinColumn(name="curso_id")}
			
			)
	
	private Set<Curso> cursosInscritos;
	
	
	
}
