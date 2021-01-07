package com.danzan.springjwt.repository;

import com.danzan.springjwt.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<Organization, Integer> {
}
