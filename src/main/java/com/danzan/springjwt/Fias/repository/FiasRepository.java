package com.danzan.springjwt.Fias.repository;

import com.danzan.springjwt.Fias.model.Fias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FiasRepository extends JpaRepository<Fias, String> {

    @Query("SELECT f FROM Fias f where f.aolevel IN (3, 4) and  f.parentguid = :aoguid order by f.formalName")
    List<Fias> findCityOrRayonNative(@Param("aoguid") UUID aoguid);

    @Query(value = "select * from addrobj limit 100", nativeQuery = true)
    List<Fias> findAllNative();
}
