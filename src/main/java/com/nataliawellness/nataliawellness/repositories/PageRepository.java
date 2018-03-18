package com.nataliawellness.nataliawellness.repositories;


import com.nataliawellness.nataliawellness.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {


    Page findBySlug(String slug);

}
