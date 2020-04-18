package com.dperon.emeals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubItem {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("meal_number")
    @Expose
    private Integer mealNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_side")
    @Expose
    private Boolean isSide;
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
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("size_units")
    @Expose
    private String sizeUnits;
    @SerializedName("units")
    @Expose
    private String units;
    @SerializedName("units_friendly")
    @Expose
    private String unitsFriendly;
    @SerializedName("units_plural")
    @Expose
    private String unitsPlural;
    @SerializedName("needs")
    @Expose
    private Object needs;
}
