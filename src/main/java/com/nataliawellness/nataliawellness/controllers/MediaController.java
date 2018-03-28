package com.nataliawellness.nataliawellness.controllers;

import com.nataliawellness.nataliawellness.entities.Media;
import com.nataliawellness.nataliawellness.entities.Tag;
import com.nataliawellness.nataliawellness.repositories.MediaRepository;
import com.nataliawellness.nataliawellness.services.StorageService;
import com.nataliawellness.nataliawellness.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MediaController {

    @Autowired
    TagService tagService;

    @Autowired
    StorageService storageService;

    @Autowired
    MediaRepository mediaRepository;

    @RequestMapping(value = "/admin/media/list", method = RequestMethod.GET)
    public String listTags(Model model){
        model.addAttribute("mediaList", mediaRepository.findAll());
        return "media/list";
    }

    @RequestMapping(value = "/admin/media/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("successMsg", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/admin/media/list";
    }

    @RequestMapping(value = "/admin/media/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "media_id") Long id,
                         RedirectAttributes redirectAttributes) {

        mediaRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMsg", "Media deleted successfully!");
        return "redirect:/admin/media/list";

    }




}
