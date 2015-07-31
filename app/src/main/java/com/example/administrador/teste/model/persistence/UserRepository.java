package com.example.administrador.teste.model.persistence;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.User;

import java.util.List;

/**
 * Created by Administrador on 21/07/2015.
 */
public interface UserRepository {

    public void save(User user); // SAVE / EDIT

    public User getOneUser();

    public void delete(User user);




}
