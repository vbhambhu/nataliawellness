package com.nataliawellness.nataliawellness.helpers;

import com.nataliawellness.nataliawellness.entities.Menu;

import java.util.ArrayList;
import java.util.List;

public class SiteHelper {

    public static String encodeForUrl(String input) {
        return input.toLowerCase().replaceAll("[^a-z\\s]", "").replaceAll("\\s", "-");
    }

    public static List<Menu> cleanMenu(List<Menu> menuList) {

        List<Menu> tempList = new ArrayList<>();

        for(Menu menu : menuList){
            if(!menu.getChildren().isEmpty()){
                for(Menu menu1: menu.getChildren()){
                    tempList.add(menu1);
                }
            }
        }

        for(Menu menu : tempList){
            menuList.remove(menu);
        }





        return menuList;
    }
}
