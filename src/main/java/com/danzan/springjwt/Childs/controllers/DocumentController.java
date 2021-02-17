package com.danzan.springjwt.Childs.controllers;

import com.danzan.springjwt.Childs.models.Document;
import com.danzan.springjwt.Childs.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/documents")
public class DocumentController {
    @Autowired
    DocumentRepository documentRepository;

    // проверка на наличие снилса в базе данных, если нет выдаем ошибку
    @GetMapping("/snils")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    List<Document> findAllDocumentNativeQuery() {
        return documentRepository.findAll();
    }
}
