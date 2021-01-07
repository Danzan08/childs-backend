package com.danzan.springjwt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Integer id;

    // 03 свидетельство о рождении
    @Column(name = "type_document")
    private Integer typeDocument;

    @Column(name = "serial")
    private String serial;

    @Column(name = "number")
    private Integer number;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_issue")
    private Date dateIssue;

    @Column(name = "issued")
    private String issued;

    @Temporal(TemporalType.DATE)
    @Column(name = "act_date")
    private Date actDate;

    @Column(name = "act_number")
    private Integer actNumber;

    @Column(name = "zags")
    private String zags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", foreignKey = @ForeignKey(name = "fk_child_id"))
    @JsonIgnore
    private Child child;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public Integer getActNumber() {
        return actNumber;
    }

    public void setActNumber(Integer actNumber) {
        this.actNumber = actNumber;
    }

    public String getZags() {
        return zags;
    }

    public void setZags(String zags) {
        this.zags = zags;
    }

    public Integer getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(Integer typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}
