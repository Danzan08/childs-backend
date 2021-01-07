package com.danzan.springjwt.models;

import javax.persistence.*;

@Entity
@Table (name = "type_orgs")
public class TypeOrganization {

    @Id
    @GeneratedValue
    @Column(name = "type_org_id")
    private Short id;

    @Column(name = "name")
    private String name;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
