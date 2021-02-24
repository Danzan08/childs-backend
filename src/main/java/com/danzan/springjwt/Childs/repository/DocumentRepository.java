package com.danzan.springjwt.Childs.repository;


import com.danzan.springjwt.Childs.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DocumentRepository extends JpaRepository<Document, Integer> {

}