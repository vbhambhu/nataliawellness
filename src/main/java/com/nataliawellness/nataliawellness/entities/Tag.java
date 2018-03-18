package com.nataliawellness.nataliawellness.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Size(min=2, max=255, message = "Slug field must be between 2 and 255 characters.")
    @Column(unique=true)
    private String slug;

    @Size(min=2, max=255, message = "Name field must be between 2 and 255 characters.")
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
