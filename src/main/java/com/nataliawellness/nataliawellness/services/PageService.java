package com.nataliawellness.nataliawellness.services;

import com.nataliawellness.nataliawellness.entities.Page;
import com.nataliawellness.nataliawellness.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;


    public List<Page> findAll() {
       return pageRepository.findAll();
    }

    public void create(Page page) {
        page.setCreatedAt(new Date());
        page.setUpdatedAt(new Date());
        pageRepository.save(page);
    }

    public Page getById(Long id) {
        return pageRepository.getOne(id);
    }

    public Page getBySlug(String slug) {
        return pageRepository.findBySlug(slug);
    }
}
