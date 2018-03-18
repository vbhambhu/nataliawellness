package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Setting;
import com.nataliawellness.nataliawellness.entities.Tag;
import com.nataliawellness.nataliawellness.services.SettingService;
import com.nataliawellness.nataliawellness.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SettingController {

    @Autowired
    SettingService settingService;

    @RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
    public String listTags(Model model, Setting setting){
        model.addAttribute("settings", settingService.findAll());
        return "settings/list";
    }

    @RequestMapping(value = "/admin/settings", method = RequestMethod.POST)
    public String listTags(Model model, @Valid Setting setting, BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            model.addAttribute("settings", settingService.findAll());
            return "settings/list";
        }

        settingService.create(setting);

        return "redirect:/admin/settings";



    }



}
