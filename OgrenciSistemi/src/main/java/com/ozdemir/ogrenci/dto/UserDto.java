package com.ozdemir.ogrenci.dto;

import com.ozdemir.ogrenci.entity.OrderEntity;

import java.util.Set;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int active;
    private Set<com.ozdemir.ogrenci.dto.RoleDto> roles;
    private Set<OrderEntity> orderEntities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<com.ozdemir.ogrenci.dto.RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<com.ozdemir.ogrenci.dto.RoleDto> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(Set<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }



}
