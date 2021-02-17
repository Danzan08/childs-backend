package com.danzan.springjwt.Childs.repository;


import com.danzan.springjwt.Childs.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepository extends JpaRepository<Document, Integer> {
}