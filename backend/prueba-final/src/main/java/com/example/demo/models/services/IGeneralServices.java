package com.example.demo.models.services;

import java.util.List;

public interface IGeneralServices<T> {

	  List<T> findAll();
	  List<T> findAllActive();
	  T save(T t);
	  T findById(Long id);
	 
}
