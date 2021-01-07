package com.danzan.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@JsonIgnoreProperties({"password"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;

    @Size(max = 60)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 60)
    @Column(name = "sur_name")
    private String surName;

    @Size(max = 60)
    @Column(name = "patronymic")
    private String patronymic;

    @Size(max = 15)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "organization_id", foreignKey = @ForeignKey(name = "fk_organization_id"))
    private Organization organization;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRoles = new HashSet<>();


    // планируемая с добавлениями всех полей для регистрации определенного пользователя
    public User(@Size(max = 20) String username,
                @Size(max = 60) @Email String email,
                @Size(min = 6, max = 120) String password,
                @Size(max = 60) String firstName,
                @Size(max = 60) String patronymic,
                @Size(max = 60) String surName,
                @Size(max = 15) String phoneNumber) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
    }

    // конструктор для регистрации нового пользователя
    public User(String username, String email, Organization organization, String password) {
        this.username = username;
        this.email = email;
        this.organization = organization;
        this.password = password;
    }

    public User() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
