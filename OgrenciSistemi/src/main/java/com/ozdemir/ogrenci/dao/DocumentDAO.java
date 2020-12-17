package com.ozdemir.ogrenci.dao;

import com.ozdemir.ogrenci.dto.DocumentDto;

import java.util.List;

public interface DocumentDAO {

    List<DocumentDto> findAll();
    DocumentDto findByID(int id);
    void save(DocumentDto document);
}
