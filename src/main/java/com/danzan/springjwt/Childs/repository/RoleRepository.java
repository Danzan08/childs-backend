package com.danzan.springjwt.Childs.repository;

import java.util.Optional;

import com.danzan.springjwt.Childs.models.ERole;
import com.danzan.springjwt.Childs.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
