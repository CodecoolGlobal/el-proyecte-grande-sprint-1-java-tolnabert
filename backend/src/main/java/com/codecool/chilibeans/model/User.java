package com.codecool.chilibeans.model;

import com.codecool.chilibeans.model.recipe.Recipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID publicId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    // TODO:look up "email annotation"
    @Email
    private String email;
    @OneToMany(mappedBy = "user")
    private Set<Recipe> ownRecipes;
    @ManyToMany(mappedBy = "user")
    private Set<Recipe> favoredRecipes;
    private LocalDate creationDate;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Recipe> getOwnRecipes() {
        return ownRecipes;
    }

    public void setOwnRecipes(Set<Recipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
    }

    public Set<Recipe> getFavoredRecipes() {
        return favoredRecipes;
    }

    public void setFavoredRecipes(Set<Recipe> favoredRecipes) {
        this.favoredRecipes = favoredRecipes;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "publicId=" + id +
                ", publicId=" + publicId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", ownRecipes=" + ownRecipes +
                ", favoredRecipes=" + favoredRecipes +
                ", creationDate=" + creationDate +
                '}';
    }
}
