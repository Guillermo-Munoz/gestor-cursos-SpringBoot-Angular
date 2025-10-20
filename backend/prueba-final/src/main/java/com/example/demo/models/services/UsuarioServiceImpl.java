package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IUsuarioDAO;
import com.example.demo.models.entities.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public List<Usuario> findAllActive() {
        return usuarioDAO.findByActivo(1);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario u) {
        return usuarioDAO.save(u);
    }

    

}
