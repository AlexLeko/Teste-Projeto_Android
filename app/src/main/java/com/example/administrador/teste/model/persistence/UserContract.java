package com.example.administrador.teste.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserContract {

    public static final String TABLE = "user";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";



    public static final String[] COLUMNS = { ID, USERNAME, PASSWORD };

    public static String getCreateTable(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
            sql.append(" ( ");
                sql.append( ID + " INTEGER PRIMARY KEY, " );
                sql.append( USERNAME + " TEXT, " );
                sql.append( PASSWORD + " TEXT " );
            sql.append(" ); ");

        return sql.toString();
    }

    public static String getCreateUser(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE);
        sql.append(" ( "+ USERNAME +" , ");
        sql.append( PASSWORD +" ) ");
        sql.append(" VALUES ( ");
        sql.append( " 'admin', " );
        sql.append( " 'admin' " );
        sql.append(" ); ");

        return sql.toString();
    }

    public static ContentValues getContentValues(User user){        // Recupera os Valores do Cliente.
        ContentValues values = new ContentValues();

        values.put(UserContract.ID, user.getId());
        values.put(UserContract.USERNAME, user.getUserName());
        values.put(UserContract.PASSWORD, user.getPassword());

        return values;
    }

    public static User bindCursorUser(Cursor cursor){                 // Recupera a Listagem.
        if(!cursor.isBeforeFirst() || cursor.moveToNext() ){
            User user = new User();

            user.setId(cursor.getInt(cursor.getColumnIndex(UserContract.ID)));
            user.setUserName(cursor.getString(cursor.getColumnIndex(UserContract.USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));

            return user;
        }

        return null;
    }

    public static User bindUser(Cursor cursor){

        if (cursor.moveToNext()){
            return bindCursorUser(cursor);
        }

        return null;
    }
}
