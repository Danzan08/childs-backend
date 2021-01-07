package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.Document;

public interface DocumentService {
    Document findByDocumentId(Integer id);
//    List<Document> findAllDocumentNativeQuery();

}
