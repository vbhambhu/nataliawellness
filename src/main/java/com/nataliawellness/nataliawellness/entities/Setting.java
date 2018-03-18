package com.nataliawellness.nataliawellness.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "settings")
public class Setting {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Size(min=2, max=255, message = "Name field must be between 2 and 255 characters.")
    private String name;

    @Size(min=2, max=255, message = "Value field must be between 2 and 255 characters.")
    private String value;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
