package com.example.manibala.retrofits.pojo;

import com.google.gson.annotations.SerializedName;

public class CreatedUserResponse {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;

}
