package com.dperon.emeals.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.Setter;

@Data
@Entity(tableName = "recipes")
public class RecipeEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "instructions")
    public String instructions;

    @ColumnInfo(name = "ingredients")
    public String ingredients;
}
