package com.danzan.springjwt.Childs.repository;

import com.danzan.springjwt.Childs.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<Organization, Integer> {
}
