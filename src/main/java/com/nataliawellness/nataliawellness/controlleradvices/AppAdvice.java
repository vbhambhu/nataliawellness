package com.nataliawellness.nataliawellness.controlleradvices;


import com.nataliawellness.nataliawellness.repositories.MenuRepository;
import com.nataliawellness.nataliawellness.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class AppAdvice {

    @Autowired
    MenuService menuService;

    @Value("${app.image.cdn}")
    private String imageCDN;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("menuItems", menuService.getSiteMenu());
        model.addAttribute("imageCDN", imageCDN);

    }




}
