package com.ozdemir.ogrenci.dao.Imp;

import com.ozdemir.ogrenci.dto.DocumentDto;
import com.ozdemir.ogrenci.entity.DocumentEntity;
import com.ozdemir.ogrenci.repositories.DocumentRepository;
import com.ozdemir.ogrenci.dao.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.ozdemir.cafe.repositories")
public class DocumentDAOImp implements DocumentDAO {

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<DocumentDto> findAll() {

        Iterator<DocumentEntity> documents = documentRepository.findAll().iterator();
        List<DocumentDto> documentList = new ArrayList<DocumentDto>();

        while (documents.hasNext()){

            DocumentEntity documentEntity = documents.next();
            DocumentDto documentDto = new DocumentDto();

            documentDto.setId(documentEntity.getId());
            documentDto.setDocumentName(documentEntity.getDocumentName());

            documentList.add(documentDto);
        }

        return documentList;
    }

    @Override
    public DocumentDto findByID(int id) {
        DocumentEntity documentEntity = documentRepository.findByID(id);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(documentEntity.getId());
        documentDto.setDocumentName(documentEntity.getDocumentName());

        return documentDto;
    }

    @Override
    public void save(DocumentDto documentDto) {
        DocumentEntity documentEntity = documentRepository.findByID(documentDto.getId());
        documentRepository.save(documentEntity);
    }
    /*
    @Override
    public List<ProductDto> listByCategory(int categoryid) {
        //Iterator, bir toplama, alma veya çıkarma öğeleri arasında gezinmenizi sağlar.
        // ListIterator, listenin iki yönlü geçişine ve öğelerin değiştirilmesine izin vermek için Iteratoru genişletir.
        Iterator<ProductEntity> productEntityList = productRepository.listByCategory(categoryid).iterator();
        List<ProductDto> productList = new ArrayList<ProductDto>();

        while (productEntityList.hasNext()){

            ProductEntity entity = productEntityList.next();
            ProductDto productDto = new ProductDto();

            productDto.setId(entity.getId());
            productDto.setProductCategory(entity.getProductCategory());
            productDto.setProductPrice(entity.getProductPrice());
            productDto.setProductName(entity.getProductName());

            productList.add(productDto);
        }

        return productList;
    }*/

}
