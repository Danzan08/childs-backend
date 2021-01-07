package com.danzan.springjwt.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table (name = "organizations")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "organization_id")
    private Integer id;

    @Size(min = 1, max = 120)
    @Column(name = "short_name")
    private String shortName;

    @Size(min = 1, max = 120)
    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "type_org_id", foreignKey = @ForeignKey(name = "fk_type_org_id"))
    private TypeOrganization typeOrganization;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeOrganization getTypeOrganization() {
        return typeOrganization;
    }

    public void setTypeOrganization(TypeOrganization typeOrganization) {
        this.typeOrganization = typeOrganization;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
