package com.danzan.springjwt.Fias.repository;

import com.danzan.springjwt.Fias.model.Fias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FiasRepository extends JpaRepository<Fias, UUID> {


    @Query("SELECT f FROM Fias f where f.aolevel = 1 order by f.formalName")
    List<Fias> findRegionsNative();

    @Query("SELECT DISTINCT new Fias (f.formalName, f.shortName,  f.aoguid)FROM Fias f where f.aolevel IN (3, 4) and  f.parentguid = :parentguid order by f.formalName")
    List<Fias> findCitiesNative(@Param("parentguid") UUID parentguid);

    @Query("SELECT f FROM Fias f where f.aolevel IN (6, 4) and  f.parentguid = :parentguid order by f.formalName")
    List<Fias> findRayonsNative(@Param("parentguid") UUID parentguid);


    @Query("SELECT f FROM Fias f where f.aolevel IN (7) and  f.parentguid = :parentguid order by f.formalName")
    List<Fias> findStreetsNative(@Param("parentguid") UUID parentguid);




    @Query(value = "select * from addrobj limit 100", nativeQuery = true)
    List<Fias> findAllNative();
}
