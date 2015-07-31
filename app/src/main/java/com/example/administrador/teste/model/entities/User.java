package com.example.administrador.teste.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.teste.model.persistence.SQLiteClientRepository;
import com.example.administrador.teste.model.persistence.SQLiteUserRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class User implements Serializable, Parcelable {

    private Integer id;
    private String userName;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null)
            return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static User getOneUser() {
        return SQLiteUserRepository.getInstance().getOneUser();
    }

    public void save() {
        SQLiteUserRepository.getInstance().save(this);    // salva ele mesmo.
    }

    public void delete() {
        SQLiteUserRepository.getInstance().delete(this);
    }


    // CONSTRUTOR DO PARCELABLE.
    public User() {
        super();
    }

    public User(Parcel in) {
        super();
        readToParcel(in);
    }

    // PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(userName == null ? "" : userName);
        dest.writeString(password == null ? "" : password);

    }

    public void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        userName = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {

        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
