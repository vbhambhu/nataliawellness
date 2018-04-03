package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.services.MenuService;
import com.nataliawellness.nataliawellness.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class APIController {

    @Autowired
    StorageService storageService;

    @Autowired
    MenuService menuService;

    @ResponseBody
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file){
        return storageService.store(file);
    }


    @ResponseBody
    @RequestMapping(value = "/api/menu/delete", method = RequestMethod.POST)
    public String deleteMenu(@RequestParam("menu_id") Long id){
        menuService.deleteById(id);
        return "deleted";
    }

    @ResponseBody
    @RequestMapping(value = "/api/menu/update", method = RequestMethod.POST)
    public String updateMenu(@RequestBody Menu menuList){

        System.out.println(menuList);

        //menuService.deleteById(id);
        return "done";
    }

}
