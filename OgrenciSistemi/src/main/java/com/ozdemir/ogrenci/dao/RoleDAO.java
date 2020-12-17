package com.ozdemir.ogrenci.dao;

import com.ozdemir.ogrenci.dto.RoleDto;

public interface RoleDAO {

    void deleteRole(int userid);
    RoleDto findOne(int userid);

}
