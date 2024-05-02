package com.codecool.chilibeans.model.recipe;

import com.codecool.chilibeans.model.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID publicId;
    private String name;
    private String description;
    @OneToMany
    @JoinColumn(name = "dietId")
    private Set<Diet> diets;
    @OneToMany
    @JoinColumn(name = "ingredientId")
    private Set<Ingredient> ingredients;
    @OneToMany
    @JoinColumn(name = "stepId")
    private List<Step> steps;
    private int portions;
    private String image;
    private LocalDate createdAt;
    @ManyToOne
    private Client createdBy;
    @ManyToMany
    private Set<Client> favoredBy;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Diet> getDiets() {
        return diets;
    }

    public void setDiets(Set<Diet> diets) {
        this.diets = diets;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Client getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Client createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Client> getFavoredBy() {
        return favoredBy;
    }

    public void setFavoredBy(Set<Client> favoredBy) {
        this.favoredBy = favoredBy;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "publicId=" + id +
                ", publicId=" + publicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", diets=" + diets +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", portions=" + portions +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", favoredBy=" + favoredBy +
                '}';
    }
}
