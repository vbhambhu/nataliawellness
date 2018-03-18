package com.nataliawellness.nataliawellness.repositories;

import com.nataliawellness.nataliawellness.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findBySlug(String slug);

    //List<Category> findByIdNot(Long id);

}
