package com.codecool.chilibeans.model.recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID publicId;
    private String name;

    public Diet() {
    }

    public Diet(UUID publicId, String name) {
        this.publicId = publicId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "publicId=" + id +
                ", publicId=" + publicId +
                ", name='" + name + '\'' +
                '}';
    }
}
