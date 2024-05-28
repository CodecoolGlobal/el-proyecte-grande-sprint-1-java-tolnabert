package com.codecool.chilibeans.model;

import com.codecool.chilibeans.model.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID publicId;//UUID generationType
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String username;
    private String password;//constraints
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Email
    private String email;
    @OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    private Set<Recipe> ownRecipes;//letting Entity out to frontend, using another DTO instead
    @ManyToMany(mappedBy = "favoredBy")
    private Set<Recipe> favoredRecipes;
    private LocalDate creationDate;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "client_roles", joinColumns = @JoinColumn(name = "client_id"))
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
