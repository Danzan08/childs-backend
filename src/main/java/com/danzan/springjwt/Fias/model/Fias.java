package com.danzan.springjwt.Fias.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "addrobj")
public class Fias {

    @Id
    @Column(name = "aoid", columnDefinition = "uuid", updatable = false)
    private UUID aoid;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "formalname")
    private String formalName;

    @Column(name = "aoguid", columnDefinition = "uuid", updatable = false)
    private UUID aoguid;

    @Column(name = "aolevel")
    private Integer aolevel;

    @Column(name = "parentguid", columnDefinition = "uuid", updatable = false)
    private UUID parentguid;

    public Fias() {
    }

    public Fias(UUID aoid, String shortName, String formalName, UUID aoguid, Integer aolevel, UUID parentguid) {
        this.aoid = aoid;
        this.shortName = shortName;
        this.formalName = formalName;
        this.aoguid = aoguid;
        this.aolevel = aolevel;
        this.parentguid = parentguid;
    }

    public UUID getAoid() {

        return aoid;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFormalName() {
        return formalName;
    }

    public UUID getAoguid() {
        return aoguid;
    }

    public Integer getAolevel() {
        return aolevel;
    }

    public UUID getParentguid() {
        return parentguid;
    }
}
