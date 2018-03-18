package com.nataliawellness.nataliawellness.controlleradvices;


import com.nataliawellness.nataliawellness.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class AppAdvice {

    @Autowired
    MenuRepository menuRepository;

//    @Value("${app.category.param}")
//    private String categoryUrl;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menuItems", menuRepository.findAll());
    }




}
