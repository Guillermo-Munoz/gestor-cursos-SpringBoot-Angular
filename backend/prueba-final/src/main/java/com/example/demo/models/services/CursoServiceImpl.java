package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.ICursoDAO;
import com.example.demo.models.entities.Curso;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoDAO cursoDAO;

    @Override
    public List<Curso> findAll() {
        return (List<Curso>) cursoDAO.findAll();
    }

    @Override
    public List<Curso> findAllActive() {
        return cursoDAO.findByActivo(1);
    }

    @Override
    public Curso findById(Long id) {
        return cursoDAO.findById(id).orElse(null);
    }

    @Override
    public Curso save(Curso u) {
        return cursoDAO.save(u);
    }

	
}
