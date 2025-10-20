package com.example.demo.models.services;

import java.util.List;
import com.example.demo.models.entities.Curso;

public interface ICursoService extends IGeneralServices<Curso> {
    List<Curso> findAllActive();
}
