package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Page;
import com.nataliawellness.nataliawellness.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/page/create", method = RequestMethod.GET)
    public String createPage(){
        return "pages/create";
    }

    @ResponseBody
    @RequestMapping(value = "/page/create", method = RequestMethod.POST)
    public String createPages(@RequestBody Page page){

        pageService.create(page);
        return "done";
    }

    @ResponseBody
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public List<Page> pages(){
        return pageService.findAll();
    }


    @ResponseBody
    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String rrrrr(){

        return "Hello, I am private";
    }



}
