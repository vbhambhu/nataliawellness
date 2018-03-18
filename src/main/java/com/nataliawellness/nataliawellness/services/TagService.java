package com.nataliawellness.nataliawellness.services;

import com.nataliawellness.nataliawellness.entities.Category;
import com.nataliawellness.nataliawellness.entities.Tag;
import com.nataliawellness.nataliawellness.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAll() {
       return tagRepository.findAll();
    }

    public Tag findBySlug(String slug) {
        return tagRepository.findBySlug(slug);
    }

    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getById(Long id) {
        return tagRepository.getOne(id);
    }

    public void deleteById(Long id) {

        Tag tag = tagRepository.getOne(id);

        if(tag != null){
            tagRepository.deleteById(id);
        }
    }
}
