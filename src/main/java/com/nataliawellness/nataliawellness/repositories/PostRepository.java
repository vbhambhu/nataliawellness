package com.nataliawellness.nataliawellness.repositories;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByTitleContaining(String query);

    Post findBySlug(String slug);

    List<Post> findBySlugIn(List<String> slugList);

    List<Post> findByShowOnHomeEqualsAndStatus(boolean onHome, boolean status);

    List<Post> findAllByCategoriesContains(Category category);


}
