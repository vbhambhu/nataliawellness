package com.nataliawellness.nataliawellness.repositories;


import com.nataliawellness.nataliawellness.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByOrderByPositionAsc();

    Menu findBySlug(String slug);
}
