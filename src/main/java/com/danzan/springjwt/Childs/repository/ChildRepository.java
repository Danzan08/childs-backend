package com.danzan.springjwt.Childs.repository;

import com.danzan.springjwt.Childs.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findBySurNameContainingIgnoreCase(String surName);

    @Query("FROM Child c where c.organization.id = (SELECT u.organization.id FROM User u where u.username = :username)")
    List<Child> findAllNative(String username);

    @Query("SELECT c.snils FROM Child c")
    List<Object> snilsList();

}
