package com.ozdemir.ogrenci;

import com.ozdemir.ogrenci.controller.ErrorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ozdemir.ogrenci.repositories"})
@ComponentScan(basePackages = {"com.ozdemir.ogrenci.dao.Imp"},basePackageClasses = ErrorController.class)
@EntityScan(basePackages = {"com.ozdemir.ogrenci.entity"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})//Spring Boot’a classpath bazında beans,
// diğer bean’leri ve çeşitli özellik ayarlarını eklemeye başlasın der.
public class SystemApplication
{
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}