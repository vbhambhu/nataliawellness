package com.nataliawellness.nataliawellness.services;

import com.nataliawellness.nataliawellness.entities.Setting;
import com.nataliawellness.nataliawellness.repositories.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class SettingService {

    @Autowired
    SettingRepository settingRepository;


    public List<Setting> getAllByName(String name) {
        return settingRepository.getAllByName(name);
    }

    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    public void create(@Valid Setting setting) {
        settingRepository.save(setting);
    }
}
