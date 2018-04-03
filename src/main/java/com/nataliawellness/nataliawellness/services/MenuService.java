package com.nataliawellness.nataliawellness.services;


import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.helpers.SiteHelper;
import com.nataliawellness.nataliawellness.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getSiteMenu() {
        List<Menu> menuList = menuRepository.findAllByOrderByPositionAsc();
        return SiteHelper.cleanMenu(menuList);
    }


    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public void create(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu getById(Long id) {
        return menuRepository.getOne(id);
    }

    public void deleteById(Long id) {
        Menu menu = menuRepository.getOne(id);

        if(menu != null){
            menuRepository.deleteById(id);
        }
    }


    public Menu findBySlug(String slug) {
        return menuRepository.findBySlug(slug);
    }
}
