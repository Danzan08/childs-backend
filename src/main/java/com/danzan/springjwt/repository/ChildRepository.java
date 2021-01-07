package com.danzan.springjwt.repository;

import com.danzan.springjwt.models.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    Page<Child> findByIsActive(boolean isActive, Pageable pageable);

    Page<Child> findBySurNameContaining(String surName, Pageable pageable);
    Page<Child> findBySurNameAndFirstNameContaining(String surName,String firstName, Pageable pageable);
    Page<Child> findBySurNameAndFirstNameAndPatronymicContaining(String surName, String firstname,String patronymic, Pageable pageable);
}
