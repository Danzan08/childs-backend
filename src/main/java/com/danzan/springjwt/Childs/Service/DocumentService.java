package com.danzan.springjwt.Childs.Service;

import com.danzan.springjwt.Childs.models.Document;

public interface DocumentService {
    Document findByDocumentId(Integer id);
//    List<Document> findAllDocumentNativeQuery();

}
