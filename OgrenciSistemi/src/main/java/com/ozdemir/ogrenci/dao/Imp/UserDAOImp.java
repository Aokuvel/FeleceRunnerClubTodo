package com.ozdemir.ogrenci.dao.Imp;

import com.ozdemir.ogrenci.dao.UserDAO;
import com.ozdemir.ogrenci.dto.UserDto;
import com.ozdemir.ogrenci.entity.RoleEntity;
import com.ozdemir.ogrenci.entity.UserEntity;
import com.ozdemir.ogrenci.repositories.DocumentRepository;
import com.ozdemir.ogrenci.repositories.RoleRepository;
import com.ozdemir.ogrenci.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@ComponentScan(basePackages = "com.ozdemir.ogrenci.config")
public class UserDAOImp implements UserDAO {


    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto findUserByEmail(String email) {
        UserDto userDto = new UserDto();
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null)
        {
            return null;
        }
        userDto.setActive(userEntity.getActive());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setId(userEntity.getId());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }


    @Override
    public void save(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));//Bu metod sayesinde şifreyi kriptoluyoruz
        userEntity.setActive(1);
        RoleEntity userRole = roleRepository.findByRole("USER");
        userEntity.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(userEntity);//crud u extend ettiğinden repository sınıfı save metodunu oradan alıyor
    }

    @Override
    public void update(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDto> findAll() {

        //Iterator, bir toplama, alma veya çıkarma öğeleri arasında gezinmenizi sağlar.
        // ListIterator, listenin iki yönlü geçişine ve öğelerin değiştirilmesine izin vermek için Iteratoru genişletir.
        Iterator<UserEntity> userEntityIterator = userRepository.findAll().iterator();
        List<UserDto> userList = new ArrayList<UserDto>();

        while (userEntityIterator.hasNext()){

            UserEntity entity = userEntityIterator.next();
            UserDto userDto = new UserDto();

            userDto.setActive(entity.getActive());
            userDto.setEmail(entity.getEmail());
            userDto.setFirstName(entity.getFirstName());
            userDto.setId(entity.getId());
            userDto.setLastName(entity.getLastName());
            userDto.setPassword(entity.getPassword());

            userList.add(userDto);
        }

        return userList;
    }

    @Override
    public UserDto findOne(int userid) {

        UserEntity userEntity = userRepository.findOne(userid);
        UserDto userDto = new UserDto();

        userDto.setActive(userEntity.getActive());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setId(userEntity.getId());
        userDto.setLastName(userEntity.getLastName());
        userDto.setPassword(userEntity.getPassword());

        return userDto;
    }

    @Override
    @Transactional
    public void deleteUser(int userid){
        UserEntity userEntity = userRepository.findOne(userid);
        userRepository.delete(userEntity);
    }




}
