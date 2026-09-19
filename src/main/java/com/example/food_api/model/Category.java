package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "categories")
public class Category {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private String name;
    private String description;
    private List<String> notes;

    public Category() {
    }

    public Category(Long id, String name, String description, List<String> notes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}