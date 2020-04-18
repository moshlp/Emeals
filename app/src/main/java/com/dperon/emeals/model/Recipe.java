package com.dperon.emeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Recipe {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("side")
    @Expose
    private Side side;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("plan_size")
    @Expose
    private String planSize;
    @SerializedName("plan_style")
    @Expose
    private String planStyle;
    @SerializedName("plan_title")
    @Expose
    private String planTitle;
    @SerializedName("plan_mobile_title")
    @Expose
    private String planMobileTitle;
    @SerializedName("plan_title_without_size")
    @Expose
    private String planTitleWithoutSize;
}
