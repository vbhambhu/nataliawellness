package com.nataliawellness.nataliawellness.repositories;

import com.nataliawellness.nataliawellness.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
