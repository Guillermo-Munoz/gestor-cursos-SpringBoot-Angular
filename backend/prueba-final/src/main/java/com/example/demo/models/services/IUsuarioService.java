package com.example.demo.models.services;

import java.util.List;
import com.example.demo.models.entities.Usuario;

public interface IUsuarioService extends IGeneralServices<Usuario> {
    List<Usuario> findAllActive();
}