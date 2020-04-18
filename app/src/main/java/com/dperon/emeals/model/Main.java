package com.dperon.emeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Main {
    @SerializedName("title")
    @Expose
    private String title;
    private Map<String,String> ingredients;
    private Map<String,String> instructions;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("prep_time")
    @Expose
    private Integer prepTime;
    @SerializedName("cook_time")
    @Expose
    private Integer cookTime;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("is_side")
    @Expose
    private Boolean isSide;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("bucket")
    @Expose
    private String bucket;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("primary_picture_path")
    @Expose
    private String primaryPicturePath;
    @SerializedName("primary_picture_url")
    @Expose
    private String primaryPictureUrl;
    @SerializedName("primary_picture_url_medium")
    @Expose
    private String primaryPictureUrlMedium;
}
