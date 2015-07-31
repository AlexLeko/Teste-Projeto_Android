package com.example.administrador.teste.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.User;
import com.example.administrador.teste.util.AppUtil;

import java.util.List;

public class SQLiteUserRepository implements UserRepository {

    // SINGLETON
    private static SQLiteUserRepository singletonInstance;    // instancia unica do objeto.

    private SQLiteUserRepository(){       // construtor que bloqueia mais de uma instancia��o.
        super();
    }

    public static SQLiteUserRepository getInstance() {
        if(SQLiteUserRepository.singletonInstance == null){
            SQLiteUserRepository.singletonInstance = new SQLiteUserRepository();
        }
        return SQLiteUserRepository.singletonInstance;
    }
    // ==============================

    @Override
    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);        // ABRE A CONEXAO COM O BANCO.
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues values = UserContract.getContentValues(user);


        if(user.getId() == null){
            db.insert(UserContract.TABLE, null, values);          // INSERIR NO BD.
        }else{
            String where = UserContract.ID + " = ? ";         // TEM QUE SER TUDO STRING;
            String[] args = {user.getId().toString()};        // ARGUMENTOS DO WHERE.

            db.update(ClientContract.TABLE, values, where, args );          // UPDATE NO BD.
        }

        db.close();
        helper.close();
    }

    @Override
    public User getOneUser() {

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        // FAZ A BUSCA NAS COLUNAS RECUPERANDO, OS VALORES, ATRAVEZ DO CURSOR.
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, null, null, null, null, UserContract.USERNAME);

        User user = UserContract.bindUser(cursor);

        db.close();
        helper.close();

        return user;     // RETORNA User.
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = UserContract.ID + " = ? ";
        String[] args = {user.getId().toString()};   // ARGUMENTOS DO WHERE.

        db.delete(ClientContract.TABLE, where, args );

        db.close();
        helper.close();
    }
}
