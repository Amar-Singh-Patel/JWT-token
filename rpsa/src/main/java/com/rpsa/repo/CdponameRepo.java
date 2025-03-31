package com.rpsa.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rpsa.entity.Cdponame;



@Repository
public interface CdponameRepo extends JpaRepository<Cdponame, Long> {

    @Query(value = "SELECT * FROM cdponame u WHERE u.cdpo = ?1 LIMIT 1", nativeQuery = true)
    Optional<Cdponame> findByCdpoId(String cdpocode);
    
    // Alternative: Use Spring Data JPA method without @Query
    Optional<Cdponame> findByCdpo(String cdpo);}