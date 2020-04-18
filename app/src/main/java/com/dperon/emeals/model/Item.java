package com.dperon.emeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("meal_numbers")
    @Expose
    private String mealNumbers;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("alternate_store")
    @Expose
    private Boolean alternateStore;
    @SerializedName("is_staple")
    @Expose
    private Boolean isStaple;
    @SerializedName("sale")
    @Expose
    private Object sale;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("quantity_number")
    @Expose
    private Integer quantityNumber;
    @SerializedName("quantity_fraction")
    @Expose
    private String quantityFraction;
    @SerializedName("parsed_name")
    @Expose
    private String parsedName;
    @SerializedName("size")
    @Expose
    private Object size;
    @SerializedName("size_units")
    @Expose
    private Object sizeUnits;
    @SerializedName("units")
    @Expose
    private Object units;
    @SerializedName("units_friendly")
    @Expose
    private Object unitsFriendly;
    @SerializedName("units_plural")
    @Expose
    private Object unitsPlural;
    @SerializedName("needs")
    @Expose
    private Object needs;
    @SerializedName("store_brand_name")
    @Expose
    private String storeBrandName;
    @SerializedName("sub_items")
    @Expose
    private List<SubItem> subItems = null;
}
