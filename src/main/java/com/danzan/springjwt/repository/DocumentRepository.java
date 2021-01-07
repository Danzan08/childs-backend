package com.danzan.springjwt.repository;


import com.danzan.springjwt.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentRepository extends JpaRepository<Document, Integer> {
}