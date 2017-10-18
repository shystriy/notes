package ru.spospekhov.notesapp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shy on 18.10.2017.
 */

@Entity
@Table(name = "NOTES")
public class Note {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATEDDATE")
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Note() {
        this.status = "Не выполнена";
    }
}
