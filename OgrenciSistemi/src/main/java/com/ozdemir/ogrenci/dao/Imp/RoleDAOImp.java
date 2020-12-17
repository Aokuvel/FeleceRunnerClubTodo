package com.ozdemir.ogrenci.dao.Imp;

import com.ozdemir.ogrenci.dao.RoleDAO;
import com.ozdemir.ogrenci.dto.RoleDto;
import com.ozdemir.ogrenci.entity.RoleEntity;
import com.ozdemir.ogrenci.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "com.ozdemir.ogrenci.config")
public class RoleDAOImp implements RoleDAO {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public RoleDto findOne(int userid) {

        RoleEntity roleEntity = roleRepository.findByUserId(userid);
        RoleDto roleDto = new RoleDto();

        roleDto.setId(roleEntity.getId());
        roleDto.setRole(roleEntity.getRole());

        return roleDto;
    }

    @Override
    public void deleteRole(int userid){
        RoleEntity roleEntity = roleRepository.findOne(userid);
        roleRepository.delete(roleEntity);
    }


}
