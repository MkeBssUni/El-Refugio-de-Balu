package com.balu.backend.modules.people.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {
    @Query(value = "select * from people where people.user_id not in (select id from users where role_id = 1) and (lower(name) like %:name% or lower(last_name) like %:lastName% or lower(sur_name) like %:surName%)", nativeQuery = true)
    Page<Person> findAllPaged(String name, String lastName, String surName, Pageable pageable);
}
