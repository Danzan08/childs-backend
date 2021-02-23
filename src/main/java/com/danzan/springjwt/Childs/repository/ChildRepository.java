package com.danzan.springjwt.Childs.repository;

import com.danzan.springjwt.Childs.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findBySurNameContainingIgnoreCase(String surName);



}
