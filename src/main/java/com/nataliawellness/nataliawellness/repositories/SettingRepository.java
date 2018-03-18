package com.nataliawellness.nataliawellness.repositories;

import com.nataliawellness.nataliawellness.entities.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingRepository extends JpaRepository<Setting, Long> {

    List<Setting> getAllByName(String name);
}
