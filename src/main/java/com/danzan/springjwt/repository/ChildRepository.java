package com.danzan.springjwt.repository;

import com.danzan.springjwt.models.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findBySurNameContainingIgnoreCase(String surName);
}
