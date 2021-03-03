package com.danzan.springjwt.Childs.repository;

import com.danzan.springjwt.Childs.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findBySurNameContainingIgnoreCase(String surName);

    @Query("FROM Child c where c.organization.id = (SELECT u.organization.id FROM User u where u.username = :username)")
    List<Child> findAllWithParams(String username);

    @Query("FROM Child c where c.id = :id AND c.organization.id = (SELECT u.organization.id FROM User u where u.username = :username)")
    Optional<Child> findByIdWithParam(Integer id, String username);

    @Query("SELECT c.snils FROM Child c")
    List<Object> snilsList();

}
