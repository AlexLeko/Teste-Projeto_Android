package com.example.administrador.teste.model.persistence;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 21/07/2015.
 */
public class MemoryUserRepository implements UserRepository {

    private static MemoryUserRepository singletonInstance;    // instancia unica do objeto.

    private List<User> users;

    private MemoryUserRepository(){       // construtor que bloqueia mais de uma instancia��o.
        super();
        users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if(MemoryUserRepository.singletonInstance == null){           // Verifica: SAVE / EDIT
            MemoryUserRepository.singletonInstance = new MemoryUserRepository();
        }
        return MemoryUserRepository.singletonInstance;
    }

    // CONTRACT USER
    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User getOneUser() {
        return null;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
