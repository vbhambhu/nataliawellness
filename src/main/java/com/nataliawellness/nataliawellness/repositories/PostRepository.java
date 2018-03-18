package com.nataliawellness.nataliawellness.repositories;

import com.nataliawellness.nataliawellness.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

   //Post findBySlugAndIdNot(String newSlug, Long id);

    List<Post> findAllByStatus(boolean status);

    List<Post> findByTitleContaining(String query);

    Post findBySlug(String slug);

    List<Post> findBySlugIn(List<String> slugList);


}
