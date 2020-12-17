package com.ozdemir.ogrenci.dao;

import com.ozdemir.ogrenci.dto.UserDto;
import com.ozdemir.ogrenci.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    UserDto findUserByEmail(String email);
    void save(UserEntity user);
    void update(UserEntity userEntity);
    List<UserDto> findAll();
    UserDto findOne(int id);
    void deleteUser(int id);

}
