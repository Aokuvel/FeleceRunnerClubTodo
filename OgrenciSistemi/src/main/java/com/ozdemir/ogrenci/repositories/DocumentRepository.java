package com.ozdemir.ogrenci.repositories;

import com.ozdemir.ogrenci.entity.DocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository <DocumentEntity, Long>  {

    @Query("from DocumentEntity d where d.id=:documentID")
    DocumentEntity findByID(@Param("documentID") int id);
}
