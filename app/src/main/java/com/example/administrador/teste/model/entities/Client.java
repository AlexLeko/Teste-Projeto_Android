package com.example.administrador.teste.model.entities;

import com.example.administrador.teste.model.persistence.MemoryClientRepository;

import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class Client {

    private String name;
    private Integer age;
    private String Phone;
    private String Address;


    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (Phone != null ? !Phone.equals(client.Phone) : client.Phone != null) return false;
        return !(Address != null ? !Address.equals(client.Address) : client.Address != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (Phone != null ? Phone.hashCode() : 0);
        result = 31 * result + (Address != null ? Address.hashCode() : 0);
        return result;
    }

    public void save(){
        MemoryClientRepository.getInstance().save(this);    // salva ele mesmo.
    }

    public static List<Client> getAll(){
        return MemoryClientRepository.getInstance().getAll();
    }

}
