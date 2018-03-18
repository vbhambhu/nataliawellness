package com.nataliawellness.nataliawellness.services;


import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;


    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }
}
