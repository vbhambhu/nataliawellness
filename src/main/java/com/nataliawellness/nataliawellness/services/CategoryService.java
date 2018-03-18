package com.nataliawellness.nataliawellness.services;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> findAll() {

        return categoryRepository.findAll();

    }

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public Category getById(Long id) {
        return categoryRepository.getOne(id);
    }

    public List<Category> findAllExceptId(Long id) {

       return null;

    }
}
