package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class APIController {

    @Autowired
    StorageService storageService;

    @ResponseBody
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file){
        return storageService.store(file);
    }

}
