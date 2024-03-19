package com.balu.backend.modules.people.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {
    @Query(value = "select p.id, concat(p.name, ' ', p.last_name, ' ', p.sur_name) as fullname, p.phone_number as phone,u.username,u.blocked,r.name as role from people p inner join users u on p.user_id = u.id inner join roles r on u.role_id = r.id where p.user_id not in (select id from users where role_id = 1) and (lower(p.name) like %:name% or lower(p.last_name) like %:lastName% or lower(p.sur_name) like %:surName%)", nativeQuery = true)
    Page<IPersonViewPaged> findAllPaged(String name, String lastName, String surName, Pageable pageable);
    Optional<Person> findByUserId(Long userId);
}
