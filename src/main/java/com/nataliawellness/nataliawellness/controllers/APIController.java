package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Menu;
import com.nataliawellness.nataliawellness.services.MenuService;
import com.nataliawellness.nataliawellness.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Iterator;
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
    @RequestMapping(value = "/api/menu/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateMenu(@RequestBody Map<String, String> [] menuList){




        for (int i = 0; i < menuList.length; i++) {

            Long menuId = Long.valueOf(menuList[i].get("id"));

            Menu menu = menuService.getById(menuId);

            if(menu != null){

                int position = Integer.parseInt(menuList[i].get("position"));
                menu.setPosition(position);

                if(menuList[i].get("parent_id") == null || menuList[i].get("parent_id").equals("0")){
                    menu.setParent(null);
                } else{
                    Long parentId = Long.valueOf(menuList[i].get("parent_id"));
                    Menu parentMenu = menuService.getById(parentId);
                    menu.setParent(parentMenu);
                }

                menuService.save(menu);
            }



        }





//        for (Menu menu : menuList){
//            System.out.println(menu.getParent());
//            System.out.println(menu.getPosition());
//        }

        //menuService.deleteById(id);
        return "done";
    }

}
