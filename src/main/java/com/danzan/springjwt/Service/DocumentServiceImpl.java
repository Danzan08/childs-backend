package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.Document;
import com.danzan.springjwt.repository.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document findByDocumentId(Integer id) {
        return documentRepository.findById(id).get();
    }




}
